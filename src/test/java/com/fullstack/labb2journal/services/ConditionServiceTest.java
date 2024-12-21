package com.fullstack.labb2journal.services;

import com.fullstack.labb2journal.dto.ConditionDTO;
import com.fullstack.labb2journal.entitys.Condition;
import com.fullstack.labb2journal.entitys.Doctor;
import com.fullstack.labb2journal.entitys.Patient;
import com.fullstack.labb2journal.entitys.User;
import com.fullstack.labb2journal.mappers.Mapper;
import com.fullstack.labb2journal.repositories.ConditionRepository;
import com.fullstack.labb2journal.repositories.DoctorRepository;
import com.fullstack.labb2journal.repositories.PatientRepository;
import com.fullstack.labb2journal.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ConditionServiceTest {

    private ConditionService conditionService;
    private ConditionRepository conditionRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private UserRepository userRepository;
    private Mapper<Condition, ConditionDTO> conditionMapper;

    @BeforeEach
    void setUp() {
        conditionRepository = mock(ConditionRepository.class);
        doctorRepository = mock(DoctorRepository.class);
        patientRepository = mock(PatientRepository.class);
        userRepository = mock(UserRepository.class);
        conditionMapper = mock(Mapper.class);

        conditionService = new ConditionService(
                conditionMapper,
                conditionRepository,
                doctorRepository,
                patientRepository,
                userRepository
        );
    }

    @Test
    void testCreateCondition() {
        // Arrange
        ConditionDTO conditionDTO = new ConditionDTO("Condition Name", "Condition Description", 1, 2);
        User doctorUser = createMockUser(2, "Dr. Jane", User.Gender.FEMALE);
        User patientUser = createMockUser(1, "John Doe", User.Gender.MALE);
        Doctor doctor = createMockDoctor(doctorUser);
        Patient patient = createMockPatient(patientUser);

        when(userRepository.findById(2)).thenReturn(Optional.of(doctorUser));
        when(userRepository.findById(1)).thenReturn(Optional.of(patientUser));
        when(doctorRepository.findByUser(doctorUser)).thenReturn(doctor);
        when(patientRepository.findByUser(patientUser)).thenReturn(Optional.of(patient));
        when(conditionRepository.save(any(Condition.class))).thenAnswer(invocation -> {
            Condition condition = invocation.getArgument(0);
            condition.setConditionId(1);
            return condition;
        });
        when(conditionMapper.mapToDTO(any(Condition.class))).thenAnswer(invocation -> {
            Condition condition = invocation.getArgument(0);
            return new ConditionDTO(
                    condition.getConditionName(),
                    condition.getConditionDescription(),
                    condition.getPatient().getPatientId(),
                    condition.getDoctor().getDoctorId()
            );
        });

        // Act
        ConditionDTO result = conditionService.createCondition(conditionDTO);

        // Assert
        assertEquals("Condition Name", result.getConditionName());
        assertEquals(1, result.getPatientId());
        assertEquals(2, result.getDoctorId());
        verify(conditionRepository, times(1)).save(any(Condition.class));
    }

    @Test
    void testGetConditionsByPatientId() {
        // Arrange
        Patient patient = createMockPatient(createMockUser(1, "John Doe", User.Gender.MALE));
        Doctor doctor = createMockDoctor(createMockUser(2, "Dr. Jane", User.Gender.FEMALE));
        List<Condition> conditions = new ArrayList<>();
        conditions.add(createMockCondition(patient, doctor, "Condition 1", "Description 1"));
        conditions.add(createMockCondition(patient, doctor, "Condition 2", "Description 2"));

        when(conditionRepository.findByPatient_PatientId(1)).thenReturn(conditions);

        // Act
        List<ConditionDTO> conditionDTOs = conditionService.getConditionsByPatientId(1);

        // Assert
        assertEquals(2, conditionDTOs.size());
        assertEquals("Condition 1", conditionDTOs.get(0).getConditionName());
        assertEquals("Description 2", conditionDTOs.get(1).getConditionDescription());
        verify(conditionRepository, times(1)).findByPatient_PatientId(1);
    }

    // Helper methods
    private User createMockUser(int userId, String name, User.Gender gender) {
        User user = new User();
        user.setUserId(userId);
        user.setName(name);
        user.setGender(gender);
        return user;
    }

    private Doctor createMockDoctor(User user) {
        Doctor doctor = new Doctor();
        doctor.setUser(user);
        doctor.setDoctorId(user.getUserId());
        return doctor;
    }

    private Patient createMockPatient(User user) {
        Patient patient = new Patient();
        patient.setUser(user);
        patient.setPatientId(user.getUserId());
        return patient;
    }

    private Condition createMockCondition(Patient patient, Doctor doctor, String name, String description) {
        Condition condition = new Condition();
        condition.setPatient(patient);
        condition.setDoctor(doctor);
        condition.setConditionName(name);
        condition.setConditionDescription(description);
        return condition;
    }
}

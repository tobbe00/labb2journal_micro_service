package com.fullstack.labb2journal.services;

import com.fullstack.labb2journal.dto.PatientDTO;
import com.fullstack.labb2journal.dto.UserDTO;
import com.fullstack.labb2journal.entitys.Patient;
import com.fullstack.labb2journal.entitys.User;
import com.fullstack.labb2journal.mappers.Mapper;
import com.fullstack.labb2journal.repositories.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PatientServiceTest {

    private PatientService patientService;
    private PatientRepository patientRepository;
    private Mapper<Patient, PatientDTO> patientMapper;
    private Mapper<User, UserDTO> userMapper;

    @BeforeEach
    void setUp() {
        patientRepository = mock(PatientRepository.class);
        patientMapper = mock(Mapper.class);
        patientService = new PatientService(patientRepository, patientMapper,userMapper);
    }

    @Test
    void testGetAllPatients() {
        // Arrange
        List<Patient> patients = new ArrayList<>();
        patients.add(createMockPatient(1, "John Doe", User.Gender.MALE, 30));
        patients.add(createMockPatient(2, "Jane Smith", User.Gender.FEMALE, 28));

        when(patientRepository.findAll()).thenReturn(patients);
        when(patientMapper.mapToDTO(any(Patient.class)))
                .thenAnswer(invocation -> {
                    Patient patient = invocation.getArgument(0);
                    return new PatientDTO(
                            patient.getUser().getUserId(),
                            patient.getPatientId(),
                            patient.getUser().getName(),
                            patient.getUser().getGender().name(),
                            patient.getAge()
                    );
                });

        // Act
        List<PatientDTO> patientDTOs = patientService.getAllPatients();

        // Assert
        assertEquals(2, patientDTOs.size());
        assertEquals("John Doe", patientDTOs.get(0).getName());
        assertEquals("Jane Smith", patientDTOs.get(1).getName());
        assertEquals("MALE", patientDTOs.get(0).getGender());
        assertEquals("FEMALE", patientDTOs.get(1).getGender());
        verify(patientRepository, times(1)).findAll();
        verify(patientMapper, times(2)).mapToDTO(any(Patient.class));
    }

    @Test
    void testGetPatientById() {
        // Arrange
        Patient patient = createMockPatient(1, "John Doe", User.Gender.MALE, 30);

        when(patientRepository.findById(1)).thenReturn(Optional.of(patient));
        when(patientMapper.mapToDTO(any(Patient.class)))
                .thenAnswer(invocation -> {
                    Patient p = invocation.getArgument(0);
                    return new PatientDTO(
                            p.getUser().getUserId(),
                            p.getPatientId(),
                            p.getUser().getName(),
                            p.getUser().getGender().name(),
                            p.getAge()
                    );
                });

        // Act
        PatientDTO patientDTO = patientService.getPatientById(1L);

        // Assert
        assertEquals(1, patientDTO.getPatientId());
        assertEquals("John Doe", patientDTO.getName());
        assertEquals("MALE", patientDTO.getGender());
        verify(patientRepository, times(1)).findById(1);
        verify(patientMapper, times(1)).mapToDTO(any(Patient.class));
    }

    // Helper method to create a mock Patient object
    private Patient createMockPatient(int userId, String name, User.Gender gender, int age) {
        User user = new User();
        user.setUserId(userId);
        user.setName(name);
        user.setGender(gender);

        Patient patient = new Patient();
        patient.setPatientId(userId);
        patient.setUser(user);
        patient.setAge(age);

        return patient;
    }
}

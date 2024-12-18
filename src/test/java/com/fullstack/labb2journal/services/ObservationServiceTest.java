package com.fullstack.labb2journal.services;

import com.fullstack.labb2journal.dto.ObservationDTO;
import com.fullstack.labb2journal.entitys.Doctor;
import com.fullstack.labb2journal.entitys.Observation;
import com.fullstack.labb2journal.entitys.Patient;
import com.fullstack.labb2journal.entitys.User;
import com.fullstack.labb2journal.mappers.Mapper;
import com.fullstack.labb2journal.repositories.DoctorRepository;
import com.fullstack.labb2journal.repositories.ObservationRepository;
import com.fullstack.labb2journal.repositories.PatientRepository;
import com.fullstack.labb2journal.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ObservationServiceTest {

    private ObservationService observationService;
    private ObservationRepository observationRepository;
    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private UserRepository userRepository;
    private Mapper<Observation, ObservationDTO> observationMapper;

    @BeforeEach
    void setUp() {
        observationRepository = mock(ObservationRepository.class);
        patientRepository = mock(PatientRepository.class);
        doctorRepository = mock(DoctorRepository.class);
        userRepository = mock(UserRepository.class);
        observationMapper = mock(Mapper.class);

        observationService = new ObservationService(
                observationRepository,
                patientRepository,
                doctorRepository,
                userRepository,
                observationMapper
        );
    }

    @Test
    void testCreateObservation() {
        // Arrange
        ObservationDTO observationDTO = new ObservationDTO("Description", null, 1, 2);
        User doctorUser = createMockUser(2, "Dr. Jane", User.Gender.FEMALE);
        User patientUser = createMockUser(1, "John Doe", User.Gender.MALE);
        Doctor doctor = createMockDoctor(doctorUser);
        Patient patient = createMockPatient(patientUser);

        when(userRepository.findById(2)).thenReturn(Optional.of(doctorUser));
        when(userRepository.findById(1)).thenReturn(Optional.of(patientUser));
        when(doctorRepository.findByUser(doctorUser)).thenReturn(doctor);
        when(patientRepository.findByUser(patientUser)).thenReturn(Optional.of(patient));
        when(observationRepository.save(any(Observation.class))).thenAnswer(invocation -> {
            Observation observation = invocation.getArgument(0);
            observation.setObservationId(1L);
            return observation;
        });
        when(observationMapper.mapToDTO(any(Observation.class))).thenAnswer(invocation -> {
            Observation observation = invocation.getArgument(0);
            return new ObservationDTO(
                    observation.getDescription(),
                    observation.getDate(),
                    observation.getPatient().getPatientId(),
                    observation.getDoctor().getDoctorId()
            );
        });

        // Act
        ObservationDTO result = observationService.createObservation(observationDTO);

        // Assert
        assertEquals("Description", result.getDescription());
        assertEquals(1, result.getPatientId());
        assertEquals(2, result.getDoctorId());
        verify(observationRepository, times(1)).save(any(Observation.class));
    }

    @Test
    void testGetObservationsByPatientId() {
        // Arrange
        Patient patient = createMockPatient(createMockUser(1, "John Doe", User.Gender.MALE));
        Doctor doctor = createMockDoctor(createMockUser(2, "Dr. Jane", User.Gender.FEMALE));
        List<Observation> observations = new ArrayList<>();
        observations.add(createMockObservation(patient, doctor, "Obs 1", LocalDate.now()));
        observations.add(createMockObservation(patient, doctor, "Obs 2", LocalDate.now()));

        when(observationRepository.findByPatient_PatientId(1)).thenReturn(observations);

        // Act
        List<ObservationDTO> observationDTOs = observationService.getObservationsByPatientId(1);

        // Assert
        assertEquals(2, observationDTOs.size());
        assertEquals("Obs 1", observationDTOs.get(0).getDescription());
        assertEquals("Obs 2", observationDTOs.get(1).getDescription());
        verify(observationRepository, times(1)).findByPatient_PatientId(1);
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

    private Observation createMockObservation(Patient patient, Doctor doctor, String description, LocalDate date) {
        Observation observation = new Observation();
        observation.setPatient(patient);
        observation.setDoctor(doctor);
        observation.setDescription(description);
        observation.setDate(date);
        return observation;
    }
}

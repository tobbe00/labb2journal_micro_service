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
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ObservationService {

    private final ObservationRepository observationRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final Mapper<Observation, ObservationDTO> observationMapper;

    public ObservationService(ObservationRepository observationRepository,
                              PatientRepository patientRepository,
                              DoctorRepository doctorRepository,
                              UserRepository userRepository,
                              Mapper<Observation, ObservationDTO> observationMapper) {
        this.observationRepository = observationRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
        this.observationMapper = observationMapper;
    }

    public ObservationDTO createObservation(ObservationDTO observationDTO) {
        observationDTO.setDate(LocalDate.now());

        User doctorUser = userRepository.findById(observationDTO.getDoctorId()).orElseThrow();
        User patientUser = userRepository.findById(observationDTO.getPatientId()).orElseThrow();

        Doctor doctor = doctorRepository.findByUser(doctorUser);
        Patient patient = patientRepository.findByUser(patientUser).orElseThrow();

        Observation observation = new Observation();
        observation.setDoctor(doctor);
        observation.setPatient(patient);
        observation.setDate(LocalDate.now());
        observation.setDescription(observationDTO.getDescription());

        return observationMapper.mapToDTO(observationRepository.save(observation));
    }

    public List<ObservationDTO> getObservationsByPatientId(Integer patientId) {
        List<Observation> observations = observationRepository.findByPatient_PatientId(patientId);
        List<ObservationDTO> observationDTOs = new ArrayList<>();

        for (Observation observation : observations) {
            Doctor doctor = observation.getDoctor();
            int doctorId = doctor != null ? doctor.getDoctorId() : 0;

            observationDTOs.add(new ObservationDTO(
                    observation.getDescription(),
                    observation.getDate(),
                    patientId,
                    doctorId
            ));
        }

        return observationDTOs;
    }
}

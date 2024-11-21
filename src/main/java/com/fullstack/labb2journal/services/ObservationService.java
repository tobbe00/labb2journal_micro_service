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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ObservationService {
    @Autowired
    private ObservationRepository observationRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private UserRepository userRepository;


    private final Mapper<Observation, ObservationDTO> observationMapper;

    public ObservationService(Mapper<Observation, ObservationDTO> observationMapper) {
        this.observationMapper = observationMapper;
    }
    public ObservationDTO createObservation(ObservationDTO observationDTO) {
        observationDTO.setDate(LocalDate.now());

        User uDoc=userRepository.findById(observationDTO.getDoctorId()).get();
        User uPatient=userRepository.findById(observationDTO.getPatientId()).get();

        Doctor doctor=doctorRepository.findByUser(uDoc);
        Patient patient=patientRepository.findByUser(uPatient).get();


        //skiter i mappning de funkade ändå inte.
        Observation observation = new Observation();
        observation.setDoctor(doctor);
        observation.setPatient(patient);
        observation.setDate(LocalDate.now());
        observation.setDescription(observationDTO.getDescription());

        return observationMapper.mapToDTO(observationRepository.save(observation));
    }
    public List<ObservationDTO> getObservationsByPatientId(Integer patientId) {
        List<ObservationDTO> observationDTOs = new ArrayList<>();

        // Hämta observationer för den specifika patienten
        List<Observation> observations = observationRepository.findByPatient_PatientId(patientId);

        for (Observation observation : observations) {
            Doctor doctor = observation.getDoctor(); // Hämta läkare om finns
            int doctorId = doctor != null ? doctor.getDoctorId() : 0; // Hantera null-värden

            // Skapa en DTO för observationen
            ObservationDTO observationDTO = new ObservationDTO(
                    observation.getDescription(),
                    observation.getDate(),
                    patientId, // Koppla till patient
                    doctorId   // Koppla till doktor
            );
            observationDTOs.add(observationDTO);
        }

        return observationDTOs;
    }


}

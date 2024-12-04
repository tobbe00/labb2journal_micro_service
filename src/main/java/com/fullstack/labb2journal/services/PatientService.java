package com.fullstack.labb2journal.services;

import com.fullstack.labb2journal.dto.PatientDTO;
import com.fullstack.labb2journal.entitys.Patient;
import com.fullstack.labb2journal.mappers.Mapper;
import com.fullstack.labb2journal.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private final PatientRepository patientRepository;
    private final Mapper<Patient, PatientDTO> patientMapper;

    public PatientService(PatientRepository patientRepository, Mapper<Patient, PatientDTO> patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    public List<PatientDTO> getAllPatients() {
        List<Patient> patient = patientRepository.findAll();
        List<PatientDTO> patientDTOs = new ArrayList<>();
        for (Patient p : patient) {
            PatientDTO patientDTO =patientMapper.mapToDTO(p);
            patientDTOs.add(patientDTO);
        }
        return patientDTOs;
    }

    public PatientDTO getPatientById(Long id) {
        return patientMapper.mapToDTO(patientRepository.findById(Math.toIntExact(id)).orElse(null));
    }

    public PatientDTO savePatient(Patient patient) {

        return patientMapper.mapToDTO(patientRepository.save(patient));
    }
}
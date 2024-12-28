package com.fullstack.labb2journal.services;

import com.fullstack.labb2journal.dto.PatientDTO;
import com.fullstack.labb2journal.dto.UserDTO;
import com.fullstack.labb2journal.entitys.Patient;
import com.fullstack.labb2journal.entitys.User;
import com.fullstack.labb2journal.mappers.Mapper;
import com.fullstack.labb2journal.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PatientService {

    @Autowired
    private final PatientRepository patientRepository;
    private final Mapper<Patient, PatientDTO> patientMapper;
    private final Mapper<User, UserDTO> userMapper;

    public PatientService(PatientRepository patientRepository, Mapper<Patient, PatientDTO> patientMapper, Mapper<User, UserDTO> userMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
        this.userMapper = userMapper;
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
    public PatientDTO getPatientByUser(UserDTO user) {
        // Map UserDTO to User entity
        User userEntity = userMapper.mapToEntity(user);

        // Find the patient by the user and handle the optional
        return patientRepository.findByUser(userEntity)
                .map(patientMapper::mapToDTO) // Map Patient entity to PatientDTO if present
                .orElseThrow(() -> new NoSuchElementException("Patient not found for the given user")); // Throw exception if not found
    }

    /*public PatientDTO savePatient(Patient patient) {
        return patientMapper.mapToDTO(patientRepository.save(patient));
    }*/
}
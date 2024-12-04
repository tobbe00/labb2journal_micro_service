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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConditionService {
    private final Mapper<Condition,ConditionDTO> conditionMapper;
    private final ConditionRepository conditionRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    public ConditionService(Mapper<Condition, ConditionDTO> conditionMapper, ConditionRepository conditionRepository, DoctorRepository doctorRepository, PatientRepository patientRepository, UserRepository userRepository) {
        this.conditionMapper = conditionMapper;
        this.conditionRepository = conditionRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }
    public ConditionDTO createCondition(ConditionDTO conditionDTO) {
        Condition condition = new Condition();
        condition.setConditionDescription(conditionDTO.getConditionDescription());
        condition.setConditionName(conditionDTO.getConditionName());


        User uDoc=userRepository.findById(conditionDTO.getDoctorId()).get();
        User uPatient=userRepository.findById(conditionDTO.getPatientId()).get();

        Doctor doctor=doctorRepository.findByUser(uDoc);
        Patient patient=patientRepository.findByUser(uPatient).get();


        condition.setDoctor(doctor);
        condition.setPatient(patient);
        return conditionMapper.mapToDTO(conditionRepository.save(condition));

    }

    public List<ConditionDTO> getConditionsByPatientId(Integer patientId) {
        List<ConditionDTO> conditionDTOs = new ArrayList<>();
        List<Condition> conditions = conditionRepository.findByPatient_PatientId(patientId);

        for (Condition condition : conditions) {
            Doctor doctor = condition.getDoctor();  // This may be null for workers or cases without a doctor
            // Create ConditionDTO using the new constructor that includes patientId
            ConditionDTO conditionDTO = new ConditionDTO(
                    condition.getConditionName(),
                    condition.getConditionDescription(),
                    patientId,  // Include patientId
                    doctor != null ? doctor.getDoctorId() : 0  // Handle null for doctor
            );
            conditionDTOs.add(conditionDTO);
        }

        return conditionDTOs;
    }



}
package com.fullstack.labb2journal.mappers;

import com.fullstack.labb2journal.dto.PatientDTO;
import com.fullstack.labb2journal.entitys.Patient;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper implements Mapper<Patient, PatientDTO>{
    private ModelMapper modelMapper;


    public PatientMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        // Customize mapping for gender and name from User entity within Patient
        this.modelMapper.typeMap(Patient.class, PatientDTO.class).addMappings(m -> {
            m.map(src -> src.getUser().getName(), PatientDTO::setName);
            m.map(src -> src.getUser().getGender(), PatientDTO::setGender);
            //m.map(src -> src.getUser().getAge(), PatientDTO::setAge);
            // Add other specific mappings if necessary
        });
    }

    @Override
    public PatientDTO mapToDTO(Patient patient) {
        return modelMapper.map(patient, PatientDTO.class);
    }

    @Override
    public Patient mapToEntity(PatientDTO patientDTO) {
        return modelMapper.map(patientDTO, Patient.class);
    }
}


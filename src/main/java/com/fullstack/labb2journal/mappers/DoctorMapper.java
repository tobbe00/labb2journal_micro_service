package com.fullstack.labb2journal.mappers;

import com.fullstack.labb2journal.dto.DoctorDTO;
import com.fullstack.labb2journal.entitys.Doctor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper implements Mapper<Doctor, DoctorDTO> {
    private ModelMapper mapper;
    public DoctorMapper(ModelMapper mapper) {
        this.mapper=mapper;

        // Customize mapping for organization name
        this.mapper.typeMap(Doctor.class, DoctorDTO.class).addMappings(m -> {
            m.map(src -> src.getOrganization().getOrgName(), DoctorDTO::setOrganization);
            m.map(Doctor::getSpeciality, DoctorDTO::setSpeciality);
            m.map(src -> src.getUser().getUserId(), DoctorDTO::setUserId);
            m.map(src->src.getUser().getName(),DoctorDTO::setName);
            m.map(src->src.getUser().getAge(),DoctorDTO::setAge);
            // Add other specific mappings if necessary
        });
    }

    @Override
    public DoctorDTO mapToDTO(Doctor doctor) {
        return mapper.map(doctor, DoctorDTO.class);
    }

    @Override
    public Doctor mapToEntity(DoctorDTO doctorDTO) {
        return mapper.map(doctorDTO, Doctor.class);
    }
}
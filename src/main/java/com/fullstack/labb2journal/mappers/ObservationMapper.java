package com.fullstack.labb2journal.mappers;

import com.fullstack.labb2journal.dto.ObservationDTO;
import com.fullstack.labb2journal.entitys.Observation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ObservationMapper implements Mapper<Observation, ObservationDTO>{
    private ModelMapper mapper;

    public ObservationMapper() {
        mapper = new ModelMapper();

    }

    @Override
    public ObservationDTO mapToDTO(Observation observation) {
        return mapper.map(observation, ObservationDTO.class);
    }

    @Override
    public Observation mapToEntity(ObservationDTO observationDTO) {
        return mapper.map(observationDTO, Observation.class);
    }
}

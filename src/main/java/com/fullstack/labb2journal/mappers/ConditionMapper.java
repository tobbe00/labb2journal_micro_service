package com.fullstack.labb2journal.mappers;

import com.fullstack.labb2journal.dto.ConditionDTO;
import com.fullstack.labb2journal.entitys.Condition;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ConditionMapper implements Mapper<Condition, ConditionDTO> {
    private ModelMapper mapper;
    public ConditionMapper() {
        mapper = new ModelMapper();
    }
    @Override
    public ConditionDTO mapToDTO(Condition condition) {
        return mapper.map(condition, ConditionDTO.class);
    }

    @Override
    public Condition mapToEntity(ConditionDTO conditionDTO) {
        return mapper.map(conditionDTO, Condition.class);
    }
}

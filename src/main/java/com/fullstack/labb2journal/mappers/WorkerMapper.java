package com.fullstack.labb2journal.mappers;

import com.fullstack.labb2journal.dto.WorkerDTO;
import com.fullstack.labb2journal.entitys.Worker;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class WorkerMapper implements Mapper<Worker, WorkerDTO>{
    private ModelMapper mapper;
    public WorkerMapper(ModelMapper mapper){
        this.mapper=mapper;

        // Customize mapping for organization name
        this.mapper.typeMap(Worker.class, WorkerDTO.class).addMappings(m -> {
            m.map(src -> src.getOrganization().getOrgName(), WorkerDTO::setOrganization);
            m.map(Worker::getRole, WorkerDTO::setRole);
            m.map(src -> src.getUser().getUserId(), WorkerDTO::setUserId);
            m.map(src->src.getUser().getName(),WorkerDTO::setName);
            m.map(src->src.getUser().getAge(),WorkerDTO::setAge);
            // Add other specific mappings if necessary
        });
    }

    @Override
    public WorkerDTO mapToDTO(Worker worker) {
        return mapper.map(worker, WorkerDTO.class);
    }

    @Override
    public Worker mapToEntity(WorkerDTO workerDTO) {
        return mapper.map(workerDTO,Worker.class);
    }

}
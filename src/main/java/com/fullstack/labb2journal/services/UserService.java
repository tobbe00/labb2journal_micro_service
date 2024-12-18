package com.fullstack.labb2journal.services;


import com.fullstack.labb2journal.dto.DoctorDTO;
import com.fullstack.labb2journal.dto.EmployeeDTO;
import com.fullstack.labb2journal.dto.UserDTO;
import com.fullstack.labb2journal.dto.WorkerDTO;
import com.fullstack.labb2journal.entitys.*;
import com.fullstack.labb2journal.mappers.Mapper;
import com.fullstack.labb2journal.repositories.DoctorRepository;
import com.fullstack.labb2journal.repositories.PatientRepository;
import com.fullstack.labb2journal.repositories.UserRepository;
import com.fullstack.labb2journal.repositories.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final DoctorRepository doctorRepository;
    private final WorkerRepository workerRepository;
    private final Mapper<Worker, WorkerDTO> workerMapper;
    private final Mapper<Doctor, DoctorDTO> doctorMapper;

    // Konstruktor för att injicera alla beroenden
    public UserService(DoctorRepository doctorRepository,
                       WorkerRepository workerRepository,
                       Mapper<Worker, WorkerDTO> workerMapper,
                       Mapper<Doctor, DoctorDTO> doctorMapper) {
        this.doctorRepository = doctorRepository;
        this.workerRepository = workerRepository;
        this.workerMapper = workerMapper;
        this.doctorMapper = doctorMapper;
    }

    public List<DoctorDTO> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        System.out.println("Fetched doctors: " + doctors); // Debug
        List<DoctorDTO> doctorDTOs = new ArrayList<>();
        for (Doctor doctor : doctors) {
            DoctorDTO doctorDTO = doctorMapper.mapToDTO(doctor);
            System.out.println("Mapped doctorDTO: " + doctorDTO); // Debug
            doctorDTOs.add(doctorDTO);
        }
        return doctorDTOs;
    }


    public List<WorkerDTO> getAllWorkers() {
        List<Worker> workers = workerRepository.findAll();
        List<WorkerDTO> workerDTOs = new ArrayList<>();
        for (Worker worker : workers) {
            workerDTOs.add(workerMapper.mapToDTO(worker));
        }
        return workerDTOs;
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<WorkerDTO> workerDTOs = getAllWorkers();
        List<DoctorDTO> doctorDTOs = getAllDoctors();
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();

        for (WorkerDTO workerDTO : workerDTOs) {
            System.out.println("Mapping workerDTO: " + workerDTO); // Debug
            EmployeeDTO employeeDTO = new EmployeeDTO(
                    workerDTO.getUserId(),
                    workerDTO.getName(),
                    workerDTO.getAge(),
                    false,
                    null,
                    workerDTO.getRole(),
                    workerDTO.getOrganization()
            );
            employeeDTOs.add(employeeDTO);
        }

        for (DoctorDTO doctorDTO : doctorDTOs) {
            System.out.println("Mapping doctorDTO: " + doctorDTO); // Debug
            EmployeeDTO employeeDTO = new EmployeeDTO(
                    doctorDTO.getUserId(),
                    doctorDTO.getName(),
                    doctorDTO.getAge(),
                    true,
                    doctorDTO.getSpeciality(),
                    null,
                    doctorDTO.getOrganization()
            );
            employeeDTOs.add(employeeDTO);
        }

        return employeeDTOs;
    }

}

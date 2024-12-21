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

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private UserRepository userRepository;




    private final Mapper<Worker, WorkerDTO> workerMapper;
    private final Mapper<Doctor, DoctorDTO>doctorMapper;
    private final Mapper<User,UserDTO>userMapper;



    public UserService(Mapper<Worker, WorkerDTO> workerMapper, Mapper<Doctor, DoctorDTO> doctorMapper, Mapper<User, UserDTO> userMapper) {
        this.workerMapper = workerMapper;
        this.doctorMapper = doctorMapper;
        this.userMapper = userMapper;
    }



    public List<DoctorDTO> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorDTO> doctorDTOs = new ArrayList<>();
        for (Doctor doctor : doctors) {
            doctorDTOs.add(doctorMapper.mapToDTO(doctor));
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
    public UserDTO getUserByEmail(String email) {
        return userMapper.mapToDTO(userRepository.findByEmail(email));
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<WorkerDTO> workerDTOs = getAllWorkers();
        List<DoctorDTO> doctorDTOs = getAllDoctors();
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();

        for (WorkerDTO workerDTO : workerDTOs) {
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

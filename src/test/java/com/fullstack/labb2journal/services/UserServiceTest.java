package com.fullstack.labb2journal.services;

import com.fullstack.labb2journal.dto.DoctorDTO;
import com.fullstack.labb2journal.dto.EmployeeDTO;
import com.fullstack.labb2journal.dto.UserDTO;
import com.fullstack.labb2journal.dto.WorkerDTO;
import com.fullstack.labb2journal.entitys.Doctor;
import com.fullstack.labb2journal.entitys.User;
import com.fullstack.labb2journal.entitys.Worker;
import com.fullstack.labb2journal.mappers.Mapper;
import com.fullstack.labb2journal.repositories.DoctorRepository;
import com.fullstack.labb2journal.repositories.WorkerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
/*
class UserServiceTest {

    private UserService userService;
    private DoctorRepository doctorRepository;
    private WorkerRepository workerRepository;
    private Mapper<Doctor, DoctorDTO> doctorMapper;
    private Mapper<Worker, WorkerDTO> workerMapper;
    private Mapper<User, UserDTO>userMapper;

    @BeforeEach
    void setUp() {
        doctorRepository = mock(DoctorRepository.class);
        workerRepository = mock(WorkerRepository.class);
        doctorMapper = mock(Mapper.class);
        workerMapper = mock(Mapper.class);
        userMapper=mock(Mapper.class);

        userService = new UserService(doctorRepository, workerRepository, workerMapper, doctorMapper,userMapper);
    }

    @Test
    void testGetAllDoctors() {
        // Arrange
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor()); // Mocked doctor entity
        doctors.add(new Doctor()); // Mocked doctor entity

        when(doctorRepository.findAll()).thenReturn(doctors);
        when(doctorMapper.mapToDTO(any(Doctor.class)))
                .thenAnswer(invocation -> new DoctorDTO(1, 101, "Dr. John Doe", "Cardiology", "HealthOrg", 45, "MALE"));

        // Act
        List<DoctorDTO> doctorDTOs = userService.getAllDoctors();

        // Assert
        assertEquals(2, doctorDTOs.size());
        verify(doctorRepository, times(1)).findAll();
        verify(doctorMapper, times(2)).mapToDTO(any(Doctor.class));
    }

    @Test
    void testGetAllWorkers() {
        // Arrange
        List<Worker> workers = new ArrayList<>();
        workers.add(new Worker()); // Mocked worker entity
        workers.add(new Worker()); // Mocked worker entity

        when(workerRepository.findAll()).thenReturn(workers);
        when(workerMapper.mapToDTO(any(Worker.class)))
                .thenAnswer(invocation -> new WorkerDTO(1, "WorkerName", 101, "Role", "Organization", 30, "MALE"));

        // Act
        List<WorkerDTO> workerDTOs = userService.getAllWorkers();

        // Assert
        assertEquals(2, workerDTOs.size());
        verify(workerRepository, times(1)).findAll();
        verify(workerMapper, times(2)).mapToDTO(any(Worker.class));
    }

    @Test
    void testGetAllEmployees() {
        // Arrange: Mocka läkare
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor()); // Mocked doctor entity
        doctors.add(new Doctor()); // Mocked doctor entity

        when(doctorRepository.findAll()).thenReturn(doctors);
        when(doctorMapper.mapToDTO(any(Doctor.class)))
                .thenAnswer(invocation -> new DoctorDTO(1, 101, "Dr. John Doe", "Cardiology", "HealthOrg", 45, "MALE"));

        // Arrange: Mocka arbetare
        List<Worker> workers = new ArrayList<>();
        workers.add(new Worker()); // Mocked worker entity
        workers.add(new Worker()); // Mocked worker entity

        when(workerRepository.findAll()).thenReturn(workers);
        when(workerMapper.mapToDTO(any(Worker.class)))
                .thenAnswer(invocation -> new WorkerDTO(2, "WorkerName", 102, "Role", "Organization", 30, "FEMALE"));

        // Act
        List<EmployeeDTO> employeeDTOs = userService.getAllEmployees();

        // Assert
        assertEquals(4, employeeDTOs.size()); // 2 doctors + 2 workers
        verify(doctorRepository, times(1)).findAll();
        verify(workerRepository, times(1)).findAll();
        verify(doctorMapper, times(2)).mapToDTO(any(Doctor.class));
        verify(workerMapper, times(2)).mapToDTO(any(Worker.class));
    }


}
*/


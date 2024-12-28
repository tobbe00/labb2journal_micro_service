package com.fullstack.labb2journal.controllers;

import com.fullstack.labb2journal.dto.*;
import com.fullstack.labb2journal.entitys.Patient;
import com.fullstack.labb2journal.services.ConditionService;
import com.fullstack.labb2journal.services.ObservationService;
import com.fullstack.labb2journal.services.PatientService;
import com.fullstack.labb2journal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.fullstack.labb2journal.entitys.User;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private ObservationService observationService;

    @Autowired
    private ConditionService conditionService;
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('worker', 'doctor')")
    @GetMapping
    public List<PatientDTO> getAllPatients() {
        return patientService.getAllPatients();
    }
    @PreAuthorize("hasAnyRole('worker', 'doctor')")
    @GetMapping("/{id}")
    public PatientDTO getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }
    @PreAuthorize("hasAnyRole('worker', 'doctor')")
    @PostMapping("/makeNote")
    public ObservationDTO addObservation(@RequestBody ObservationDTO observationDTO) {
        return observationService.createObservation(observationDTO);
    }
    @PreAuthorize("hasAnyRole('DOCTOR')")
    @PostMapping("/makeDiagnosis")
    public ConditionDTO addDiagnosis(@RequestBody ConditionDTO conditionDTO) {
        return conditionService.createCondition(conditionDTO);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_rest-api_worker', 'ROLE_rest-api_doctor', 'patient')")
    @GetMapping("/{id}/journal")
    public PatientJournalDTO getPatientJournal(@PathVariable Long id) {
        PatientDTO patient = patientService.getPatientById(id);

        // Hämta tillstånd och observationer för patienten
        List<ConditionDTO> conditions = conditionService.getConditionsByPatientId(id.intValue());
        List<ObservationDTO> observations = observationService.getObservationsByPatientId(id.intValue());
        User.Gender g;
        if (patient.getGender().equals("MALE")){
            g=User.Gender.MALE;
        }else if(patient.getGender().equals("FEMALE")){
            g=User.Gender.FEMALE;
        }else {
            g=User.Gender.OTHER;
        }
        // Skapa och returnera PatientJournalDTO
        PatientJournalDTO patientJournalDTO = new PatientJournalDTO(
                patient.getName(),
                patient.getAge(),
                g,
                conditions,
                observations
        );
        return patientJournalDTO;
    }
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "https://labb2frontend.app.cloud.cbh.kth.se")
    @GetMapping("/patientByEmail")
    public UserDTO getPatientByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }



}

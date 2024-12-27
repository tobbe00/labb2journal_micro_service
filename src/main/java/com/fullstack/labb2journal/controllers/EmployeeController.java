package com.fullstack.labb2journal.controllers;

import com.fullstack.labb2journal.dto.EmployeeDTO;
import com.fullstack.labb2journal.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final UserService userService;

    public EmployeeController(UserService userService) {
        this.userService = userService;
    }
    @PreAuthorize("hasAnyRole('patient')")
    @GetMapping("/getAllEmployees")
    public List<EmployeeDTO> getAllEmplayees() {
        return userService.getAllEmployees();
    }

}

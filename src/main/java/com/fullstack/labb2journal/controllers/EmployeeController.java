package com.fullstack.labb2journal.controllers;

import com.fullstack.labb2journal.dto.EmployeeDTO;
import com.fullstack.labb2journal.services.UserService;
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

    @GetMapping("/getAllEmployees")
    public List<EmployeeDTO> getAllEmplayees() {
        return userService.getAllEmployees();
    }

}

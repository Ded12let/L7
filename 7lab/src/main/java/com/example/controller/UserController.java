package com.example.controller;

import com.example.service.ProfessorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final ProfessorService professorService;

    public UserController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public String userPanel(Model model) {
        model.addAttribute("professors", professorService.findAll());
        return "user-panel";
    }
}

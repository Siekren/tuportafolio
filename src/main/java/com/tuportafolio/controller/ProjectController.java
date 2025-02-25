package com.tuportafolio.controller;

import com.tuportafolio.model.Project;
import com.tuportafolio.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*")  // Permitir solicitudes desde el frontend
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<Project> getProjects() {
        return projectService.getAllProjects();
    }

    @PostMapping
    public Project addProject(@RequestBody Project project) {
        return projectService.saveProject(project);
    }
}

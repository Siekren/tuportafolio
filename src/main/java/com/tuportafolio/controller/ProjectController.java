package com.tuportafolio.controller;

import com.tuportafolio.model.Project;
import com.tuportafolio.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*") // Permite solicitudes de cualquier origen
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
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        System.out.println("Proyecto recibido en el controller: " + project);
        Project savedProject = projectService.saveProject(project);
        System.out.println("Proyecto guardado: " + savedProject);
        return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }
}


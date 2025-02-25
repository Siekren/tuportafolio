package com.tuportafolio.controller;

import com.tuportafolio.model.Project;
import com.tuportafolio.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

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
    public Project createProject(@RequestBody Project project) {
        System.out.println("Proyecto recibido: " + project);
        Project savedProject = projectService.saveProject(project);
        System.out.println("Proyecto guardado: " + savedProject);
        return savedProject;
    }

}

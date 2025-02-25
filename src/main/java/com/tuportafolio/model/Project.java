package com.tuportafolio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String githubLink;
    private String demoLink;
    private String technologies;

    // Constructor vacío necesario para JPA
    public Project() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getGithubLink() { return githubLink; }
    public void setGithubLink(String githubLink) { this.githubLink = githubLink; }

    public String getDemoLink() { return demoLink; }
    public void setDemoLink(String demoLink) { this.demoLink = demoLink; }

    public String getTechnologies() { return technologies; }
    public void setTechnologies(String technologies) { this.technologies = technologies; }
}

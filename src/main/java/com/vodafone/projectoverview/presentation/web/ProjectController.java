package com.vodafone.projectoverview.presentation.web;

import com.vodafone.projectoverview.data.Project;
import com.vodafone.projectoverview.repository.ProjectRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/projects")
public class ProjectController {

    private final ProjectRepository projectRepository;

    public ProjectController(final ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping
    public Iterable<Project> fetchAll() {
        return projectRepository.findAll();
    }

    //@GetMapping
    //public Project fetchProjectByName(@PathVariable String name) {
    //    return projectRepository.getProjectByName(name);
    //}
}

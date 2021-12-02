package com.vodafone.projectoverview.service;

import com.vodafone.projectoverview.common.TypedPage;
import com.vodafone.projectoverview.data.Project;
import com.vodafone.projectoverview.exeption.ResourceNotFoundException;
import com.vodafone.projectoverview.repository.ContentRepository;
import com.vodafone.projectoverview.repository.ProjectRepository;
import com.vodafone.projectoverview.request.CreateRequest;
import com.vodafone.projectoverview.request.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(final ProjectRepository projectRepository, final ContentRepository contentRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createProject(CreateRequest createRequest) {
        Project project = new Project();
        project.setName(createRequest.getName());
        project.setAlias(createRequest.getAlias());
        project.setPoints(createRequest.getPoints());
        project.setWeight(createRequest.getWeight());
        project.setDescription(createRequest.getDescription());
        project.setActive(createRequest.getActive());
        this.projectRepository.save(project);
        return project;
    }

    public Project getProjectById(Long id) {
        return this.projectRepository.findById(id)
            .orElseThrow(()->
                new ResourceNotFoundException(String.format("Project '%s' not found.", id)));
    }

    public List<Project> getProjectByName(String name) {
        return this.projectRepository.findByName(name);
    }

    public List<Project> getAllProject() {
        return this.projectRepository.findAll();
    }

    public TypedPage<Project> fetchProjects(final Integer page, final Integer size) {
        final Page<Project> resultPage =
            this.projectRepository.findAll(
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"))
            );
        return TypedPage.of(resultPage.getContent(), resultPage.getTotalPages(), resultPage.getTotalElements());
    }

    public Project updateProject(UpdateRequest updateRequest) {
        Project project = this.projectRepository.findById(updateRequest.getId()).get();
        project.setName(updateRequest.getName());
        project.setAlias(updateRequest.getAlias());
        project.setPoints(updateRequest.getPoints());
        project.setWeight(updateRequest.getWeight());
        project.setActive(updateRequest.getActive());
        project.setDescription(updateRequest.getDescription());
        this.projectRepository.save(project);
        return project;
    }

    public String deleteProject(Long id) {
        this.projectRepository.deleteById(id);
        return "Project_Node Deleted";
    }
}

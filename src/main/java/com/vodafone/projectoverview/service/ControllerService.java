package com.vodafone.projectoverview.service;

import com.vodafone.projectoverview.common.TypedPage;
import com.vodafone.projectoverview.data.Controller;
import com.vodafone.projectoverview.exeption.ResourceNotFoundException;
import com.vodafone.projectoverview.repository.ControllerRepository;
import com.vodafone.projectoverview.request.CreateRequest;
import com.vodafone.projectoverview.request.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ControllerService {

    private final ControllerRepository controllerRepository;

    @Autowired
    public ControllerService(final ControllerRepository controllerRepository) {
        this.controllerRepository = controllerRepository;
    }

    public Controller createContent(CreateRequest createRequest) {
        Controller controller = new Controller();
        controller.setName(createRequest.getName());
        controller.setAlias(createRequest.getAlias());
        controller.setPoints(createRequest.getPoints());
        controller.setWeight(createRequest.getWeight());
        controller.setDescription(createRequest.getDescription());
        controller.setActive(createRequest.getActive());
        this.controllerRepository.save(controller);
        return controller;
    }

    public Controller getControllerById(Long id) {
        return this.controllerRepository.findById(id)
            .orElseThrow(()->
                new ResourceNotFoundException(String.format("Controller '%s' not found.", id)));
    }

    public List<Controller> getControllerByName(String name) {
        return this.controllerRepository.findByName(name);
    }

    public List<Controller> getAllController() {
        return this.controllerRepository.findAll();
    }

    public TypedPage<Controller> fetchContents(final Integer page, final Integer size) {
        final Page<Controller> resultPage =
             this.controllerRepository.findAll(
                        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"))
                );
        return TypedPage.of(resultPage.getContent(), resultPage.getTotalPages(), resultPage.getTotalElements());
    }

    public Controller updateController(UpdateRequest updateRequest) {
        Controller controller = this.controllerRepository.findById(updateRequest.getId()).get();
        controller.setName(updateRequest.getName());
        controller.setAlias(updateRequest.getAlias());
        controller.setPoints(updateRequest.getPoints());
        controller.setWeight(updateRequest.getWeight());
        controller.setActive(updateRequest.getActive());
        controller.setDescription(updateRequest.getDescription());
        this.controllerRepository.save(controller);
        return controller;
    }

    public String deleteController(Long id) {
        this.controllerRepository.deleteById(id);
        return "Content_Node Deleted";
    }
}

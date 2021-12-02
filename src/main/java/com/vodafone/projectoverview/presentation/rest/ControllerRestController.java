package com.vodafone.projectoverview.presentation.rest;

import com.vodafone.projectoverview.common.TypedPage;
import com.vodafone.projectoverview.data.Controller;
import com.vodafone.projectoverview.request.CreateRequest;
import com.vodafone.projectoverview.request.UpdateRequest;
import com.vodafone.projectoverview.service.ControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/controller")
public class ControllerRestController {

    private final ControllerService controllerService;

    @Autowired
    public ControllerRestController(final ControllerService controllerService) {
        this.controllerService = controllerService;
    }

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public Controller createController(@RequestBody CreateRequest createRequest) {
        return this.controllerService.createContent(createRequest);
    }

    @GetMapping(
        value = "/byId/{id}",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public Controller getControllerById(@PathVariable Long id) {
        return this.controllerService.getControllerById(id);
    }

    @GetMapping(
        value = "/byName/{name}",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public List<Controller> getControllerByName(@PathVariable String name) {
        return this.controllerService.getControllerByName(name);
    }

    @GetMapping(
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public TypedPage<Controller> fetchControllers(
            @RequestParam(value = "page", defaultValue = "0") final Integer page,
            @RequestParam(value = "size", defaultValue = "20") final Integer size) {
        return this.controllerService.fetchContents(page, size);
    }

    @PutMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Controller updateController(@RequestBody UpdateRequest updateRequest) {
        return this.controllerService.updateController(updateRequest);
    }

    @DeleteMapping(
        value = "/{id}",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteContent(@PathVariable Long id) {
        return this.controllerService.deleteController(id);
    }
}

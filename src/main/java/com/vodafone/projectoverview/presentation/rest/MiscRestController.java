package com.vodafone.projectoverview.presentation.rest;

import com.vodafone.projectoverview.common.TypedPage;
import com.vodafone.projectoverview.data.DataNode;
import com.vodafone.projectoverview.data.Misc;
import com.vodafone.projectoverview.request.CreateRequest;
import com.vodafone.projectoverview.request.UpdateRequest;
import com.vodafone.projectoverview.service.MiscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/misc")
public class MiscRestController {

    private final MiscService miscService;

    @Autowired
    public MiscRestController(final MiscService miscService) {
        this.miscService = miscService;
    }

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public Misc createMisc(@RequestBody CreateRequest createRequest) {
        return this.miscService.createMisc(createRequest);
    }

    @GetMapping(
        value = "/byId/{id}",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public Misc getMiscById(@PathVariable Long id) {
        return this.miscService.getMiscById(id);
    }

    @GetMapping(
        value = "/byName/{name}",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public List<Misc> getMiscByName(@PathVariable String name) {
        return this.miscService.getMiscByName(name);
    }

    @GetMapping(
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public TypedPage<Misc> fetchMiscs(
            @RequestParam(value = "page", defaultValue = "0") final Integer page,
            @RequestParam(value = "size", defaultValue = "20") final Integer size) {
        return this.miscService.fetchMiscs(page, size);
    }

    @PutMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Misc updateMisc(@RequestBody UpdateRequest updateRequest) {
        return this.miscService.updateMisc(updateRequest);
    }

    @DeleteMapping(
        value = "/{id}",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteMisc(@PathVariable Long id) {
        return this.miscService.deleteMisc(id);
    }
}

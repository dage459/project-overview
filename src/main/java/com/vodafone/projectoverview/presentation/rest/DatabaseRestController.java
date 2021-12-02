package com.vodafone.projectoverview.presentation.rest;

import com.vodafone.projectoverview.common.TypedPage;
import com.vodafone.projectoverview.data.Database;
import com.vodafone.projectoverview.request.CreateRequest;
import com.vodafone.projectoverview.request.UpdateRequest;
import com.vodafone.projectoverview.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/database")
public class DatabaseRestController {

    private final DatabaseService databaseService;

    @Autowired
    public DatabaseRestController(final DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public Database createDatabase(@RequestBody CreateRequest createRequest) {
        return this.databaseService.createDatabase(createRequest);
    }

    @GetMapping(
        value = "/byId/{id}",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public Database getDatabaseById(@PathVariable Long id) {
        return this.databaseService.getDatabaseById(id);
    }

    @GetMapping(
        value = "/byName/{name}",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public List<Database> getDatabaseByName(@PathVariable String name) {
        return this.databaseService.getDatabaseByName(name);
    }

    @GetMapping(
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public TypedPage<Database> fetchDatabases(
            @RequestParam(value = "page", defaultValue = "0") final Integer page,
            @RequestParam(value = "size", defaultValue = "20") final Integer size) {
        return this.databaseService.fetchDatabases(page, size);
    }

    @PutMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Database updateDatabase(@RequestBody UpdateRequest updateRequest) {
        return this.databaseService.updateDatabase(updateRequest);
    }

    @DeleteMapping(
        value = "/{id}",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteDatabase(@PathVariable Long id) {
        return this.databaseService.deleteDatabase(id);
    }
}

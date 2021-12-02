package com.vodafone.projectoverview.presentation.rest;

import com.vodafone.projectoverview.common.TypedPage;
import com.vodafone.projectoverview.data.DataNode;
import com.vodafone.projectoverview.request.CreateRequest;
import com.vodafone.projectoverview.request.UpdateRequest;
import com.vodafone.projectoverview.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/data")
public class DataRestController {

    private final DataService dataService;

    @Autowired
    public DataRestController(final DataService dataService) {
        super();
        this.dataService = dataService;
    }

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public DataNode createData(@RequestBody CreateRequest createRequest) {
        return this.dataService.createData(createRequest);
    }

    @GetMapping(
        value = "/byId/{id}",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public DataNode getDataById(@PathVariable Long id) {
        return this.dataService.getDataById(id);
    }

    @GetMapping(
        value = "/byName/{name}",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public List<DataNode> getDataByName(@PathVariable String name) {
        return this.dataService.getDataByName(name);
    }

    @GetMapping(
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public TypedPage<DataNode> fetchDatas(
        @RequestParam(value = "page", defaultValue = "0") final Integer page,
        @RequestParam(value = "size", defaultValue = "20") final Integer size) {
      return this.dataService.fetchDatas(page, size);
    }

    @PutMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.ACCEPTED)
    public DataNode updateData(@RequestBody UpdateRequest updateRequest) {
        return this.dataService.updateData(updateRequest);
    }

    @DeleteMapping(
        value = "/{id}",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteData(@PathVariable Long id) {
        return this.dataService.deleteData(id);
    }
}

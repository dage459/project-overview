package com.vodafone.projectoverview.presentation.rest;

import com.vodafone.projectoverview.common.TypedPage;
import com.vodafone.projectoverview.data.Content;
import com.vodafone.projectoverview.request.CreateRequest;
import com.vodafone.projectoverview.request.UpdateRequest;
import com.vodafone.projectoverview.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/content")
public class ContentRestController {

    private final ContentService contentService;

    @Autowired
    public ContentRestController(final ContentService contentService) {
        this.contentService = contentService;
    }

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public Content createContent(@RequestBody CreateRequest createRequest) {
        return this.contentService.createContent(createRequest);
    }

    @GetMapping(
        value = "/byId/{id}",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public Content getContentById(@PathVariable Long id) {
        return this.contentService.getContentById(id);
    }

    @GetMapping(
        value = "/byName/{name}",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public List<Content> getContentByName(@PathVariable String name) {
        return this.contentService.getContentByName(name);
    }

    @GetMapping(
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public TypedPage<Content> fetchDatas(
            @RequestParam(value = "page", defaultValue = "0") final Integer page,
            @RequestParam(value = "size", defaultValue = "20") final Integer size) {
        return this.contentService.fetchContents(page, size);
    }

    @PutMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Content updateContent(@RequestBody UpdateRequest updateRequest) {
        return this.contentService.updateContent(updateRequest);
    }

    @DeleteMapping(
        value = "/{id}",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteContent(@PathVariable Long id) {
        return this.contentService.deleteContent(id);
    }
}

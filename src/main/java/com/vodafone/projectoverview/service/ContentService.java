package com.vodafone.projectoverview.service;

import com.vodafone.projectoverview.common.TypedPage;
import com.vodafone.projectoverview.data.Content;
import com.vodafone.projectoverview.exeption.ResourceNotFoundException;
import com.vodafone.projectoverview.repository.ContentRepository;
import com.vodafone.projectoverview.request.CreateRequest;
import com.vodafone.projectoverview.request.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {

    private final ContentRepository contentRepository;

    @Autowired
    public ContentService(final ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public Content createContent(CreateRequest createRequest) {
        Content content = new Content();
        content.setName(createRequest.getName());
        content.setAlias(createRequest.getAlias());
        content.setPoints(createRequest.getPoints());
        content.setWeight(createRequest.getWeight());
        content.setDescription(createRequest.getDescription());
        content.setActive(createRequest.getActive());
        this.contentRepository.save(content);
        return content;
    }

    public Content getContentById(Long id) {
        return this.contentRepository.findById(id)
            .orElseThrow(()->
                new ResourceNotFoundException(String.format("Content '%s' not found.", id)));
    }

    public List<Content> getContentByName(String name) {

        return this.contentRepository.findByName(name);
    }

    public List<Content> getAllContent() {

        return this.contentRepository.findAll();
    }

    public TypedPage<Content> fetchContents(final Integer page, final Integer size) {
        final Page<Content> resultPage =
            this.contentRepository.findAll(
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"))
            );
        return TypedPage.of(resultPage.getContent(), resultPage.getTotalPages(), resultPage.getTotalElements());
    }

    public Content updateContent(UpdateRequest updateRequest) {
        Content content = this.contentRepository.findById(updateRequest.getId()).get();
        content.setName(updateRequest.getName());
        content.setAlias(updateRequest.getAlias());
        content.setPoints(updateRequest.getPoints());
        content.setWeight(updateRequest.getWeight());
        content.setActive(updateRequest.getActive());
        content.setDescription(updateRequest.getDescription());
        this.contentRepository.save(content);
        return content;
    }

    public String deleteContent(Long id) {
        this.contentRepository.deleteById(id);
        return "Content_Node Deleted";
    }
}

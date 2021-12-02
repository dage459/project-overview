package com.vodafone.projectoverview.service;

import com.vodafone.projectoverview.common.TypedPage;
import com.vodafone.projectoverview.data.Edge;
import com.vodafone.projectoverview.exeption.ResourceNotFoundException;
import com.vodafone.projectoverview.repository.EdgeRepository;
import com.vodafone.projectoverview.request.CreateRequest;
import com.vodafone.projectoverview.request.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EdgeService {

    private final EdgeRepository edgeRepository;

    @Autowired
    public EdgeService(final EdgeRepository edgeRepository) {
        this.edgeRepository = edgeRepository;
    }

    public Edge createEdge(CreateRequest createRequest) {
        Edge edge = new Edge();
        edge.setName(createRequest.getName());
        edge.setAlias(createRequest.getAlias());
        edge.setPoints(createRequest.getPoints());
        edge.setWeight(createRequest.getWeight());
        edge.setDescription(createRequest.getDescription());
        edge.setActive(createRequest.getActive());
        this.edgeRepository.save(edge);
        return edge;
    }

    public Edge getEdgeById(Long id) {
        return this.edgeRepository.findById(id)
            .orElseThrow(() ->
                new ResourceNotFoundException(String.format("Edge '%s' not found.", id)));
    }

    public List<Edge> getEdgeByName(String name) {
        return this.edgeRepository.findByName(name);
    }

    public TypedPage<Edge> fetchEdges(final Integer page, final Integer size) {
        final Page<Edge> resultPage =
            this.edgeRepository.findAll(
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"))
            );
        return TypedPage.of(resultPage.getContent(), resultPage.getTotalPages(), resultPage.getTotalElements());
    }

    public Edge updateEdge(UpdateRequest updateRequest) {
        Edge edge = this.edgeRepository.findById(updateRequest.getId()).get();
        edge.setName(updateRequest.getName());
        edge.setAlias(updateRequest.getAlias());
        edge.setPoints(updateRequest.getPoints());
        edge.setWeight(updateRequest.getWeight());
        edge.setActive(updateRequest.getActive());
        edge.setDescription(updateRequest.getDescription());
        this.edgeRepository.save(edge);
        return edge;
    }

    public String deleteEdge(Long id) {
        this.edgeRepository.deleteById(id);
        return "Edge_Node Deleted";
    }
}

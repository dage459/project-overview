package com.vodafone.projectoverview.service;

import com.vodafone.projectoverview.common.TypedPage;
import com.vodafone.projectoverview.data.Infrastructure;
import com.vodafone.projectoverview.exeption.ResourceNotFoundException;
import com.vodafone.projectoverview.repository.InfrastructureRepository;
import com.vodafone.projectoverview.request.CreateRequest;
import com.vodafone.projectoverview.request.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfrastructureService {

    private final InfrastructureRepository infrastructureRepository;

    @Autowired
    public InfrastructureService(final InfrastructureRepository infrastructureRepository) {
        this.infrastructureRepository = infrastructureRepository;
    }

    public Infrastructure createInfrastructure(CreateRequest createRequest) {
        Infrastructure infrastructure = new Infrastructure();
        infrastructure.setName(createRequest.getName());
        infrastructure.setAlias(createRequest.getAlias());
        infrastructure.setPoints(createRequest.getPoints());
        infrastructure.setWeight(createRequest.getWeight());
        infrastructure.setDescription(createRequest.getDescription());
        infrastructure.setActive(createRequest.getActive());
        this.infrastructureRepository.save(infrastructure);
        return infrastructure;
    }

    public Infrastructure getInfrastructureById(Long id) {
        return this.infrastructureRepository.findById(id)
            .orElseThrow(()->
                new ResourceNotFoundException(String.format("Infrastructure '%s' not found.", id)));
    }

    public List<Infrastructure> getInfrastructureByName(String name) {
        return this.infrastructureRepository.findByName(name);
    }

    public List<Infrastructure> getAllInfrastructure() {
        return this.infrastructureRepository.findAll();
    }

    public TypedPage<Infrastructure> fetchInfrastrutures(final Integer page, final Integer size) {
        final Page<Infrastructure> resultPage =
            this.infrastructureRepository.findAll(
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"))
            );
        return TypedPage.of(resultPage.getContent(), resultPage.getTotalPages(), resultPage.getTotalElements());
    }

    public Infrastructure updateInfrastructure(UpdateRequest updateRequest) {
        Infrastructure infrastructure = this.infrastructureRepository.findById(updateRequest.getId()).get();
        infrastructure.setName(updateRequest.getName());
        infrastructure.setAlias(updateRequest.getAlias());
        infrastructure.setPoints(updateRequest.getPoints());
        infrastructure.setWeight(updateRequest.getWeight());
        infrastructure.setActive(updateRequest.getActive());
        infrastructure.setDescription(updateRequest.getDescription());
        this.infrastructureRepository.save(infrastructure);
        return infrastructure;
    }

    public String deleteInfrastructure(Long id) {
        this.infrastructureRepository.deleteById(id);
        return "Infrastructure_Node Deleted";
    }
}

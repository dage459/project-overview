package com.vodafone.projectoverview.service;

import com.vodafone.projectoverview.common.TypedPage;
import com.vodafone.projectoverview.data.Misc;
import com.vodafone.projectoverview.exeption.ResourceNotFoundException;
import com.vodafone.projectoverview.repository.MiscRepository;
import com.vodafone.projectoverview.request.CreateRequest;
import com.vodafone.projectoverview.request.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MiscService {

    private final MiscRepository miscRepository;

    @Autowired
    public MiscService(final MiscRepository miscRepository) {
        this.miscRepository = miscRepository;
    }

    public Misc createMisc(CreateRequest createRequest) {
        Misc misc = new Misc();
        misc.setName(createRequest.getName());
        misc.setAlias(createRequest.getAlias());
        misc.setPoints(createRequest.getPoints());
        misc.setWeight(createRequest.getWeight());
        misc.setDescription(createRequest.getDescription());
        misc.setActive(createRequest.getActive());
        this.miscRepository.save(misc);
        return misc;
    }

    public Misc getMiscById(Long id) {
        return this.miscRepository.findById(id)
            .orElseThrow(()->
                new ResourceNotFoundException(String.format("Misc '%s' not found.", id)));
    }

    public List<Misc> getMiscByName(String name) {
        return this.miscRepository.findByName(name);
    }

    public List<Misc> getAllMisc() {
        return this.miscRepository.findAll();
    }

    public TypedPage<Misc> fetchMiscs(final Integer page, final Integer size) {
        final Page<Misc> resultPage =
            this.miscRepository.findAll(
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"))
            );
        return TypedPage.of(resultPage.getContent(), resultPage.getTotalPages(), resultPage.getTotalElements());
    }

    public Misc updateMisc(UpdateRequest updateRequest) {
        Misc misc = this.miscRepository.findById(updateRequest.getId()).get();
        misc.setName(updateRequest.getName());
        misc.setAlias(updateRequest.getAlias());
        misc.setPoints(updateRequest.getPoints());
        misc.setWeight(updateRequest.getWeight());
        misc.setActive(updateRequest.getActive());
        misc.setDescription(updateRequest.getDescription());
        this.miscRepository.save(misc);
        return misc;
    }

    public String deleteMisc(Long id) {
        this.miscRepository.deleteById(id);
        return "Misc_Node Deleted";
    }
}

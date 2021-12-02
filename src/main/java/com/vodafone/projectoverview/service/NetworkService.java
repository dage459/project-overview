package com.vodafone.projectoverview.service;

import com.vodafone.projectoverview.common.TypedPage;
import com.vodafone.projectoverview.data.Network;
import com.vodafone.projectoverview.exeption.ResourceNotFoundException;
import com.vodafone.projectoverview.repository.NetworkRepository;
import com.vodafone.projectoverview.request.CreateRequest;
import com.vodafone.projectoverview.request.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NetworkService {

    private final NetworkRepository networkRepository;

    @Autowired
    public NetworkService(final NetworkRepository networkRepository) {
        this.networkRepository = networkRepository;
    }

    public Network createNetwork(CreateRequest createRequest) {
        Network network = new Network();
        network.setName(createRequest.getName());
        network.setAlias(createRequest.getAlias());
        network.setPoints(createRequest.getPoints());
        network.setWeight(createRequest.getWeight());
        network.setDescription(createRequest.getDescription());
        network.setActive(createRequest.getActive());
        this.networkRepository.save(network);
        return network;
    }

    public Network getNetworkById(Long id) {
        return this.networkRepository.findById(id)
            .orElseThrow(()->
                new ResourceNotFoundException(String.format("Network '%s' not found.", id)));
    }

    public List<Network> getNetworkByName(String name) {
        return this.networkRepository.findByName(name);
    }

    public List<Network> getAllNetwork() {
        return this.networkRepository.findAll();
    }

    public TypedPage<Network> fetchNetworks(final Integer page, final Integer size) {
        final Page<Network> resultPage =
            this.networkRepository.findAll(
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"))
            );
        return TypedPage.of(resultPage.getContent(), resultPage.getTotalPages(), resultPage.getTotalElements());
    }

    public Network updateNetwork(UpdateRequest updateRequest) {
        Network network = this.networkRepository.findById(updateRequest.getId()).get();
        network.setName(updateRequest.getName());
        network.setAlias(updateRequest.getAlias());
        network.setPoints(updateRequest.getPoints());
        network.setWeight(updateRequest.getWeight());
        network.setActive(updateRequest.getActive());
        network.setDescription(updateRequest.getDescription());
        this.networkRepository.save(network);
        return network;
    }

    public String deleteNetwork(Long id) {
        this.networkRepository.deleteById(id);
        return "Network_Node Deleted";
    }
}

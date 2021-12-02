package com.vodafone.projectoverview.service;

import com.vodafone.projectoverview.common.TypedPage;
import com.vodafone.projectoverview.data.DataNode;
import com.vodafone.projectoverview.exeption.ResourceNotFoundException;
import com.vodafone.projectoverview.repository.DataRepository;
import com.vodafone.projectoverview.request.CreateRequest;
import com.vodafone.projectoverview.request.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {

    private final DataRepository dataRepository;

    @Autowired
    public DataService(final DataRepository dataRepository) {
        super();
        this.dataRepository = dataRepository;
    }

    public DataNode createData(CreateRequest createRequest) {
        DataNode dataNode = new DataNode();
        dataNode.setName(createRequest.getName());
        dataNode.setAlias(createRequest.getAlias());
        dataNode.setPoints(createRequest.getPoints());
        dataNode.setWeight(createRequest.getWeight());
        dataNode.setDescription(createRequest.getDescription());
        dataNode.setActive(createRequest.getActive());
        dataRepository.save(dataNode);
        return dataNode;
    }

    public DataNode getDataById(Long id) {
        return this.dataRepository.findById(id)
            .orElseThrow(()->
                new ResourceNotFoundException(String.format("Data '%s' not found.", id)));
    }

    public List<DataNode> getDataByName(String name) {
        return this.dataRepository.findByName(name);
    }

    public TypedPage<DataNode> fetchDatas(final Integer page, final Integer size) {
        final Page<DataNode> resultPage =
            this.dataRepository.findAll(
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"))
            );
        return TypedPage.of(resultPage.getContent(), resultPage.getTotalPages(), resultPage.getTotalElements());
    }

    public DataNode updateData(UpdateRequest updateRequest) {
        DataNode dataNode = this.dataRepository.findById(updateRequest.getId()).get();
        dataNode.setName(updateRequest.getName());
        dataNode.setAlias(updateRequest.getAlias());
        dataNode.setPoints(updateRequest.getPoints());
        dataNode.setWeight(updateRequest.getWeight());
        dataNode.setActive(updateRequest.getActive());
        dataNode.setDescription(updateRequest.getDescription());
        this.dataRepository.save(dataNode);
        return dataNode;
    }

    public String deleteData(Long id) {
        this.dataRepository.deleteById(id);
        return "Data_Node Deleted";
    }
}

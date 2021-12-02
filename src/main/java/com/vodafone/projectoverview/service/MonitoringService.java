package com.vodafone.projectoverview.service;

import com.vodafone.projectoverview.common.TypedPage;
import com.vodafone.projectoverview.data.Monitoring;
import com.vodafone.projectoverview.exeption.ResourceNotFoundException;
import com.vodafone.projectoverview.repository.MonitoringRepository;
import com.vodafone.projectoverview.request.CreateRequest;
import com.vodafone.projectoverview.request.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitoringService {

    private final MonitoringRepository monitoringRepository;

    @Autowired
    public MonitoringService(final MonitoringRepository monitoringRepository) {
        this.monitoringRepository = monitoringRepository;
    }

    public Monitoring createMonitoring(CreateRequest createRequest) {
        Monitoring monitoring = new Monitoring();
        monitoring.setName(createRequest.getName());
        monitoring.setAlias(createRequest.getAlias());
        monitoring.setPoints(createRequest.getPoints());
        monitoring.setWeight(createRequest.getWeight());
        monitoring.setDescription(createRequest.getDescription());
        monitoring.setActive(createRequest.getActive());
        this.monitoringRepository.save(monitoring);
        return monitoring;
    }

    public Monitoring getMonitoringById(Long id) {
        return this.monitoringRepository.findById(id)
            .orElseThrow(()->
                new ResourceNotFoundException(String.format("Monitoring '%s' not found.", id)));
    }

    public List<Monitoring> getMonitoringByName(String name) {
        return this.monitoringRepository.findByName(name);
    }

    public List<Monitoring> getAllMonitoring() {
        return this.monitoringRepository.findAll();
    }

    public TypedPage<Monitoring> fetchMonitorings(final Integer page, final Integer size) {
        final Page<Monitoring> resultPage =
            this.monitoringRepository.findAll(
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"))
            );
        return TypedPage.of(resultPage.getContent(), resultPage.getTotalPages(), resultPage.getTotalElements());
    }

    public Monitoring updateMonitoring(UpdateRequest updateRequest) {
        Monitoring monitoring = this.monitoringRepository.findById(updateRequest.getId()).get();
        monitoring.setName(updateRequest.getName());
        monitoring.setAlias(updateRequest.getAlias());
        monitoring.setPoints(updateRequest.getPoints());
        monitoring.setWeight(updateRequest.getWeight());
        monitoring.setActive(updateRequest.getActive());
        monitoring.setDescription(updateRequest.getDescription());
        this.monitoringRepository.save(monitoring);
        return monitoring;
    }

    public String deleteMonitoring(Long id) {
        this.monitoringRepository.deleteById(id);
        return "Monitoring_Node Deleted";
    }
}

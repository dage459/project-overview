package com.vodafone.projectoverview.presentation.rest;

import com.vodafone.projectoverview.data.Monitoring;
import com.vodafone.projectoverview.request.CreateRequest;
import com.vodafone.projectoverview.request.UpdateRequest;
import com.vodafone.projectoverview.service.MonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/monitoring")
public class MonitoringRestController {

    private final MonitoringService monitoringService;

    @Autowired
    public MonitoringRestController(final MonitoringService miscService) {
        this.monitoringService = miscService;
    }

    @PostMapping("/create")
    public Monitoring createMonitoring(@RequestBody CreateRequest createRequest) {
        return monitoringService.createMonitoring(createRequest);
    }

    @GetMapping("/getMonitoringById/{id}")
    public Monitoring getMonitoringById(@PathVariable Long id) {
        return monitoringService.getMonitoringById(id);
    }

    @GetMapping("/getMonitoringByName/{name}")
    public List<Monitoring> getMonitoringByName(String name) {
        return monitoringService.getMonitoringByName(name);
    }

    @GetMapping("/getAllMonitoring")
    public List<Monitoring> getAllMonitoring() {
        return monitoringService.getAllMonitoring();
    }

    @PutMapping("/update")
    public Monitoring updateMonitoring(@RequestBody UpdateRequest updateRequest) {
        return monitoringService.updateMonitoring(updateRequest);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteMonitoring(@PathVariable Long id) {
        return monitoringService.deleteMonitoring(id);
    }
}

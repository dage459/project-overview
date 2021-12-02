package com.vodafone.projectoverview.repository;

import com.vodafone.projectoverview.data.Monitoring;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitoringRepository extends Neo4jRepository<Monitoring, Long> {

    List<Monitoring> findByName(String name);
}

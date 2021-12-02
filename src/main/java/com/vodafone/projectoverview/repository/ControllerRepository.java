package com.vodafone.projectoverview.repository;

import com.vodafone.projectoverview.data.Controller;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ControllerRepository extends Neo4jRepository<Controller, Long> {

    List<Controller> findByName(String name);
}

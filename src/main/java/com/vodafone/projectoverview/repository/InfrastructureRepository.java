package com.vodafone.projectoverview.repository;

import com.vodafone.projectoverview.data.Infrastructure;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfrastructureRepository extends Neo4jRepository<Infrastructure, Long> {

    List<Infrastructure> findByName(String name);
}

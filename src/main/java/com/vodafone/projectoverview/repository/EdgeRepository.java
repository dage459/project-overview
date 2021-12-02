package com.vodafone.projectoverview.repository;

import com.vodafone.projectoverview.data.Edge;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EdgeRepository extends Neo4jRepository<Edge, Long> {

    List<Edge> findByName(String name);
}

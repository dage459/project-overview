package com.vodafone.projectoverview.repository;

import com.vodafone.projectoverview.data.Network;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NetworkRepository extends Neo4jRepository<Network, Long> {

    List<Network> findByName(String name);
}

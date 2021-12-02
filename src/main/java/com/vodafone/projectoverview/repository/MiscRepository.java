package com.vodafone.projectoverview.repository;

import com.vodafone.projectoverview.data.Misc;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MiscRepository extends Neo4jRepository<Misc, Long> {

    List<Misc> findByName(String name);
}

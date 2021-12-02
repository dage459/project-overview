package com.vodafone.projectoverview.repository;

import com.vodafone.projectoverview.data.Database;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatabaseRepository extends Neo4jRepository<Database, Long> {

    List<Database> findByName(String name);
}

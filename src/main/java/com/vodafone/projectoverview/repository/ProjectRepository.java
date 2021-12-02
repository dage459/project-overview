package com.vodafone.projectoverview.repository;

import com.vodafone.projectoverview.data.Project;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends Neo4jRepository<Project, Long> {

    List<Project> findByName(String name);
}

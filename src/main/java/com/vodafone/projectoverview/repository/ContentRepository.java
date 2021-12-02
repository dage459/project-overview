package com.vodafone.projectoverview.repository;

import com.vodafone.projectoverview.data.Content;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends Neo4jRepository<Content, Long> {

    List<Content> findByName(String name);
}

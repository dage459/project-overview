package com.vodafone.projectoverview.repository;

import com.vodafone.projectoverview.data.DataNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataRepository extends Neo4jRepository<DataNode, Long> {

    List<DataNode> findByName(String name);
}

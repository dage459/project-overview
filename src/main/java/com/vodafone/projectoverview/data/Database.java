package com.vodafone.projectoverview.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Node(labels = {"database_node"})
public class Database {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String alias;
    private Integer points;
    private Integer weight;
    private String description;
    private Boolean active;

    @Relationship(type = "EXIST_IN", direction = Relationship.Direction.OUTGOING)
    private List<Controller> controller;

    @Relationship(type = "PART_OF", direction = Relationship.Direction.OUTGOING)
    private List<Database> database;

    @Relationship(type = "DEPEND_ON", direction = Relationship.Direction.OUTGOING)
    private List<DataNode> dataNode;
}

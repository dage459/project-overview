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
@Node(labels = {"content_node"})
public class Content {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String alias;
    private Integer points;
    private Integer weight;
    private String description;
    private Boolean active;

    @Relationship(type = "HOSTED_ON", direction = Relationship.Direction.OUTGOING)
    private List<Infrastructure> infrastructure;

    @Relationship(type = "DEPEND_ON", direction = Relationship.Direction.OUTGOING)
    private List<Content> content;

    @Relationship(type = "MONITORED_ON", direction = Relationship.Direction.OUTGOING)
    private List<Monitoring> monitoring;

    @Relationship(type = "REPORTS_TO", direction = Relationship.Direction.OUTGOING)
    private List<Controller> controller;

}

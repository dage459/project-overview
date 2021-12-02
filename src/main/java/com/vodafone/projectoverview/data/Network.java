package com.vodafone.projectoverview.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Node(labels = {"network_node"})
public class Network {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String alias;
    private Integer points;
    private Integer weight;
    private String description;
    private Boolean active;
}

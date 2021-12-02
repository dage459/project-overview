package com.vodafone.projectoverview.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Node(labels = {"misc_node"})
public class Misc {

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

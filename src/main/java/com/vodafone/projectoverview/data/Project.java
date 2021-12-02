package com.vodafone.projectoverview.data;

import com.vodafone.projectoverview.data.relationship.ProjectNeedContent;
import com.vodafone.projectoverview.data.relationship.ProjectNeedController;
import com.vodafone.projectoverview.data.relationship.ProjectNeedDatabase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Node(labels = {"project_node"})
public class Project {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String alias;
    private Integer points;
    private Integer weight;
    private String description;
    private Boolean active;

    @Relationship(type = "DEPEND_ON", direction = Relationship.Direction.OUTGOING)
    private List<ProjectNeedContent> projectNeedContentList;

    @Relationship(type = "DEPEND_ON", direction = Relationship.Direction.OUTGOING)
    private List<ProjectNeedController> projectNeedControllerList;

    @Relationship(type = "DEPEND_ON", direction = Relationship.Direction.OUTGOING)
    private List<ProjectNeedDatabase> projectNeedDatabaseList;

    //@Relationship(type = "DEPEND_ON", direction = Relationship.Direction.OUTGOING)
    //private List<Content> content;
    //@Relationship(type = "DEPEND_ON", direction = Relationship.Direction.OUTGOING)
    //private List<Controller> controller;
    //@Relationship(type = "DEPEND_ON", direction = Relationship.Direction.OUTGOING)
    //private List<DataNode> dataNode;
    //@Relationship(type = "DEPEND_ON", direction = Relationship.Direction.OUTGOING)
    //private List<Database> database;
    //@Relationship(type = "DEPEND_ON", direction = Relationship.Direction.OUTGOING)
    //private List<Edge> edge;
    //@Relationship(type = "DEPEND_ON", direction = Relationship.Direction.OUTGOING)
    //private List<Infrastructure> infrastructure;
    //@Relationship(type = "DEPEND_ON", direction = Relationship.Direction.OUTGOING)
    //private List<Misc> misc;
    //@Relationship(type = "DEPEND_ON", direction = Relationship.Direction.OUTGOING)
    //private List<Monitoring> monitoring;
    //@Relationship(type = "DEPEND_ON", direction = Relationship.Direction.OUTGOING)
    //private List<Network> network;
}

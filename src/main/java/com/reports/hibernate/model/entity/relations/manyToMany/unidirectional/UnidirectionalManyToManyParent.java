package com.reports.hibernate.model.entity.relations.manyToMany.unidirectional;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class UnidirectionalManyToManyParent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String parentName;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "parent_child_bridge",
            joinColumns = @JoinColumn(
                    name = "parentId_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "childId_id", referencedColumnName = "id"
            )
    )
    private List<UnidirectionalManyToManyChild> children;
}

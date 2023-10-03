package com.reports.hibernate.model.entity.relations.manyToOne.unidirectional;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UnidirectionalManyToOneParent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String parentName;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "child_id")
    private UnidirectionalManyToOneChild child;
}

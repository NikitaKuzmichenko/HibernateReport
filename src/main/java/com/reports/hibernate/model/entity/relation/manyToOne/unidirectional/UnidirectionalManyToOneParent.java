package com.reports.hibernate.model.entity.relation.manyToOne.unidirectional;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class UnidirectionalManyToOneParent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String parentName;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "child_id")
    private UnidirectionalManyToOneChild child;
}

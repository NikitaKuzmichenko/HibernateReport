package com.reports.hibernate.model.entity.relations.oneToOne.unidirectional;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UnidirectionalOneToOneParent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String parentName;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "child_id")
    private UnidirectionalOneToOneChild child;
}

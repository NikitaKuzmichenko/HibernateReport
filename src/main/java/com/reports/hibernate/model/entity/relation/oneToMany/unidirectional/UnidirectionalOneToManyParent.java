package com.reports.hibernate.model.entity.relation.oneToMany.unidirectional;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class UnidirectionalOneToManyParent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String parentName;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "parent_id")
    private List<UnidirectionalOneToManyChild> children;
}

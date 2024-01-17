package com.reports.hibernate.model.entity.relation.manyToOne.unidirectional;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class UnidirectionalManyToOnePet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "owner_id")
    private UnidirectionalManyToOneOwner owner;
}

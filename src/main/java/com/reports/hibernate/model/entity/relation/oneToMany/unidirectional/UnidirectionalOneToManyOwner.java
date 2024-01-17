package com.reports.hibernate.model.entity.relation.oneToMany.unidirectional;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class UnidirectionalOneToManyOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "owner_id")
    private List<UnidirectionalOneToManyPet> pets;
}

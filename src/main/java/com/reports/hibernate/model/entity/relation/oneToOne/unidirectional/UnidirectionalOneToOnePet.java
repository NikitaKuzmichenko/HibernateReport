package com.reports.hibernate.model.entity.relation.oneToOne.unidirectional;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class UnidirectionalOneToOnePet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "owner_id")
    private UnidirectionalOneToOneOwner owner;
}

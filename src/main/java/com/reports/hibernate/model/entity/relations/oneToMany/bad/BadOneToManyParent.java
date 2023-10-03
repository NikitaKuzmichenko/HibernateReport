package com.reports.hibernate.model.entity.relations.oneToMany.bad;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class BadOneToManyParent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String parentName;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<BadOneToManyChild> children;
}

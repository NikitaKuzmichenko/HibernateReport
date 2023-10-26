package com.reports.hibernate.model.entity.relation.oneToMany.bad;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class BadOneToManyParent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String parentName;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<BadOneToManyChild> children;
}

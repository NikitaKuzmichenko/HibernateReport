package com.reports.hibernate.model.entity.operation;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class OperationParentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "parentId")
    private Set<OperationSecondChildEntity> secondChildEntities;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "parentId")
    private Set<OperationFirstChildEntity> firstChildEntities;
}

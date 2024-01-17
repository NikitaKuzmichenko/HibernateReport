package com.reports.hibernate.model.entity.operation;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class OperationOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private Set<OperationDog> dogs;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private Set<OperationCat> cats;
}

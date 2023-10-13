package com.reports.hibernate.model.entity.inheritance.embedded;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ChildEmbeddedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Embedded
    private ParentEmbeddedEntity parent;

}

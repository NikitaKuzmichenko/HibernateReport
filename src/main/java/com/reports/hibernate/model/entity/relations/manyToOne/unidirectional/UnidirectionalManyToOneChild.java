package com.reports.hibernate.model.entity.relations.manyToOne.unidirectional;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UnidirectionalManyToOneChild {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String childName;

}

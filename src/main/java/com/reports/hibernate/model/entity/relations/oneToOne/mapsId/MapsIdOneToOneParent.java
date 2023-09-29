package com.reports.hibernate.model.entity.relations.oneToOne.mapsId;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MapsIdOneToOneParent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String parentName;

    @OneToOne(cascade = CascadeType.PERSIST)
    private MapsIdOneToOneChild child;
}

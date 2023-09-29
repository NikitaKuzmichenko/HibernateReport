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

    @OneToOne
    @MapsId
    @JoinColumn(name = "child_id")
    private MapsIdOneToOneChild child;
}

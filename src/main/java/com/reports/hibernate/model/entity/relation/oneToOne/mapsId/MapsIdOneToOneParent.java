package com.reports.hibernate.model.entity.relation.oneToOne.mapsId;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
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

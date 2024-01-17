package com.reports.hibernate.model.entity.relation.oneToOne.mapsId;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class MapsIdOneToOnePet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToOne
    @MapsId
    @JoinColumn(name = "owner_id")
    private MapsIdOneToOneOwner owner;
}

package com.reports.hibernate.model.entity.relation.oneToOne.mapsId;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class MapsIdOneToOneOwner {

    @Id
    private long id;

    private String name;

    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private MapsIdOneToOnePet pet;

}

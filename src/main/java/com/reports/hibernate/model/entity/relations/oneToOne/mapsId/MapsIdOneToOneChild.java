package com.reports.hibernate.model.entity.relations.oneToOne.mapsId;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MapsIdOneToOneChild {

    @Id
    private long id;

    private String childName;

    @OneToOne(mappedBy = "child", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private MapsIdOneToOneParent parent;

}

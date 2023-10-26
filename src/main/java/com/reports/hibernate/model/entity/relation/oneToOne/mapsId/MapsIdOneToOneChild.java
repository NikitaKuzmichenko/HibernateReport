package com.reports.hibernate.model.entity.relation.oneToOne.mapsId;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class MapsIdOneToOneChild {

    @Id
    private long id;

    private String childName;

    @OneToOne(mappedBy = "child", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private MapsIdOneToOneParent parent;

}

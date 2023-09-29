package com.reports.hibernate.model.entity.relations.oneToOne.bidirectional;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BidirectionalOneToOneChild {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String childName;

    @OneToOne(mappedBy = "child")
    private BidirectionalOneToOneParent parent;
}

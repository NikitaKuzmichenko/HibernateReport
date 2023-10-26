package com.reports.hibernate.model.entity.relation.manyToOne.bidirectional;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class BidirectionalManyToOneParent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String parentName;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="child_id")
    private BidirectionalManyToOneChild child;
}

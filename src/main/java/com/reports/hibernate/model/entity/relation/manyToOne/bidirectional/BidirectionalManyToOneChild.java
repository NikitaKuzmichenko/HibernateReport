package com.reports.hibernate.model.entity.relation.manyToOne.bidirectional;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class BidirectionalManyToOneChild {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String childName;

    @OneToMany(mappedBy="child")
    private List<BidirectionalManyToOneParent> parents;
}

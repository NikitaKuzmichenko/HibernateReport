package com.reports.hibernate.model.entity.relations.manyToMany.bidirectional;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class BidirectionalManyToManyChild {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String childName;

    @ManyToMany(mappedBy = "children")
    private List<BidirectionalManyToManyParent> parents;
}

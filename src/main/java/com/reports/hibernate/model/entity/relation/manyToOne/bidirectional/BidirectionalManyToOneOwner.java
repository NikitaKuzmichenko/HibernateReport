package com.reports.hibernate.model.entity.relation.manyToOne.bidirectional;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class BidirectionalManyToOneOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(mappedBy="owner")
    private List<BidirectionalManyToOnePet> pets;
}

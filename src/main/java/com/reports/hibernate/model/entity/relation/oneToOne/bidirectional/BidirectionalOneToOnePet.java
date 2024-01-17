package com.reports.hibernate.model.entity.relation.oneToOne.bidirectional;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class BidirectionalOneToOnePet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToOne(mappedBy = "pet")
    private BidirectionalOneToOneOwner owner;

    public void addOwner(BidirectionalOneToOneOwner owner){
        if(owner != null){
            owner.setPet(this);
        }
        this.owner = owner;
    }
}

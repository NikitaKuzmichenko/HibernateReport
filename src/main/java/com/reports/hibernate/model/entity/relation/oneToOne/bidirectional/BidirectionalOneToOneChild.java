package com.reports.hibernate.model.entity.relation.oneToOne.bidirectional;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class BidirectionalOneToOneChild {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String childName;

    @OneToOne(mappedBy = "child")
    private BidirectionalOneToOneParent parent;

    public void addParent(BidirectionalOneToOneParent parent){
        if(parent != null){
            parent.setChild(this);
        }
        this.parent = parent;
    }
}

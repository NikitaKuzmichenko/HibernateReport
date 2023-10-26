package com.reports.hibernate.model.entity.relation.oneToOne.bidirectional;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class BidirectionalOneToOneParent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String parentName;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "child_id")
    private BidirectionalOneToOneChild child;

    public void addParent(BidirectionalOneToOneChild child){
        if(child != null){
            child.setParent(this);
        }
        this.child = child;
    }
}

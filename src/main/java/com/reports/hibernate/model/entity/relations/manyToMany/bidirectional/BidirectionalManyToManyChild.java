package com.reports.hibernate.model.entity.relations.manyToMany.bidirectional;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class BidirectionalManyToManyChild {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String childName;

    @ManyToMany(mappedBy = "children", cascade = CascadeType.PERSIST)
    private Set<BidirectionalManyToManyParent> parents = new HashSet<>();

    public void addParents(Set<BidirectionalManyToManyParent> parents){
        for(BidirectionalManyToManyParent parent : parents){
            if(parent != null){
                parent.getChildren().add(this);
            }
            this.parents.add(parent);
        }
    }
}

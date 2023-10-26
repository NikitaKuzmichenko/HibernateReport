package com.reports.hibernate.model.entity.relation.manyToMany.bidirectional;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BidirectionalManyToManyChild {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String childName;

    @ManyToMany(mappedBy = "children")
    private Set<BidirectionalManyToManyParent> parents;

    public void setParentsConsistently(Set<BidirectionalManyToManyParent> parents) {
        for (BidirectionalManyToManyParent parent : parents) {
            if (parent != null) {
                if (parent.getChildren() == null) {
                    parent.setChildren(new HashSet<>());
                }
                parent.getChildren().add(this);
            }
            this.parents = parents;
        }
    }
}

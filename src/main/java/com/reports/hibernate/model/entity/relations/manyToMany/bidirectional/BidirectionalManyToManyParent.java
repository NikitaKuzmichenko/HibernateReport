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
public class BidirectionalManyToManyParent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String parentName;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "parent_child_bridge",
            joinColumns = @JoinColumn(
                    name = "parentId_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "childId_id", referencedColumnName = "id"
            )
    )
    private Set<BidirectionalManyToManyChild> children;

    public void setChildrenConsistently(Set<BidirectionalManyToManyChild> children) {
        for (BidirectionalManyToManyChild child : children) {
            if (child != null) {
                if (child.getParents() == null) {
                    child.setParents(new HashSet<>());
                }
                child.getParents().add(this);
            }
            this.children = children;
        }
    }

}

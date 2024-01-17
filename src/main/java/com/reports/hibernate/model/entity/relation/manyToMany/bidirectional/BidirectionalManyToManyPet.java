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
public class BidirectionalManyToManyPet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToMany(mappedBy = "pets")
    private Set<BidirectionalManyToManyOwner> owners;

    public void setParentsConsistently(Set<BidirectionalManyToManyOwner> owners) {
        for (BidirectionalManyToManyOwner parent : owners) {
            if (parent != null) {
                if (parent.getPets() == null) {
                    parent.setPets(new HashSet<>());
                }
                parent.getPets().add(this);
            }
            this.owners = owners;
        }
    }
}

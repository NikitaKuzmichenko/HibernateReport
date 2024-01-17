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
public class BidirectionalManyToManyOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "owner_pet_bridge",
            joinColumns = @JoinColumn(
                    name = "owner_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "pet_id", referencedColumnName = "id"
            )
    )
    private Set<BidirectionalManyToManyPet> pets;

    public void setChildrenConsistently(Set<BidirectionalManyToManyPet> pets) {
        for (BidirectionalManyToManyPet child : pets) {
            if (child != null) {
                if (child.getOwners() == null) {
                    child.setOwners(new HashSet<>());
                }
                child.getOwners().add(this);
            }
            this.pets = pets;
        }
    }

}

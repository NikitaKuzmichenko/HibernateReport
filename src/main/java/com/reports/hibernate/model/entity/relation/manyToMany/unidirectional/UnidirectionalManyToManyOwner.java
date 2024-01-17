package com.reports.hibernate.model.entity.relation.manyToMany.unidirectional;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UnidirectionalManyToManyOwner {

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
    private Set<UnidirectionalManyToManyPet> pets;
}

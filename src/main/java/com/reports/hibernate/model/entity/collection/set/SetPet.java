package com.reports.hibernate.model.entity.collection.set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class SetPet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private long id;

    private String name;

    public SetPet(String name, SetOwner owner) {
        this.name = name;
        this.owner = owner;
    }

    @ManyToOne()
    @JoinColumn(name = "ownerId", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private SetOwner owner;

}

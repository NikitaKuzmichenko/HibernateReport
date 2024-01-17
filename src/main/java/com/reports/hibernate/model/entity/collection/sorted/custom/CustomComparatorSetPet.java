package com.reports.hibernate.model.entity.collection.sorted.custom;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
public class CustomComparatorSetPet implements Comparable<CustomComparatorSetPet>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    public CustomComparatorSetPet(String name, CustomComparatorSetOwner owner) {
        this.owner = owner;
        this.name = name;
    }

    @ManyToOne()
    @JoinColumn(name = "ownerId", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private CustomComparatorSetOwner owner;


    @Override
    public int compareTo(CustomComparatorSetPet o) {
        return this.getName().compareTo(o.getName());
    }
}

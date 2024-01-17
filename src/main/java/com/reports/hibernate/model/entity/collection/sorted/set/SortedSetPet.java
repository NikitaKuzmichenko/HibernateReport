package com.reports.hibernate.model.entity.collection.sorted.set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class SortedSetPet implements Comparable<SortedSetPet> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private long id;

    private String name;

    public SortedSetPet(String name, SortedSetOwner owner) {
        this.name = name;
        this.owner = owner;
    }

    @ManyToOne()
    @JoinColumn(name = "ownerId", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private SortedSetOwner owner;

    @Override
    public int compareTo(SortedSetPet o) {
        if (name == null && o.getName() == null) {
            return 0;
        }
        if (name == null) {
            return 1;
        }
        return -name.compareTo(o.getName());

    }

}

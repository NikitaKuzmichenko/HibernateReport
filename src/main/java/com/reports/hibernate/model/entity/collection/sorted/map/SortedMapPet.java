package com.reports.hibernate.model.entity.collection.sorted.map;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class SortedMapPet implements Comparable<SortedMapPet>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne()
    @JoinColumn(name = "ownerId", nullable = false)
    @EqualsAndHashCode.Exclude
    private SortedMapOwner owner;

    public SortedMapPet(String name, SortedMapOwner owner) {
        this.name = name;
        this.owner = owner;
    }

    @Override
    public int compareTo(SortedMapPet o) {
        if(name == null && o.getName() == null){
            return 0;
        }
        if(name == null){
            return 1;
        }
        return -name.compareTo(o.getName());
    }

}

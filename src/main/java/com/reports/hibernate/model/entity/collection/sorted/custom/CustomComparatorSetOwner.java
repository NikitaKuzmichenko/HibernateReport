package com.reports.hibernate.model.entity.collection.sorted.custom;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SortComparator;

import java.util.Set;

@Data
@Entity
public class CustomComparatorSetOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @SortComparator(CustomComparator.class)
    private Set<CustomComparatorSetPet> pets;

}

package com.reports.hibernate.model.entity.collection.sorted.set;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.SortedSet;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SortedSetOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private SortedSet<SortedSetPet> pets;

}

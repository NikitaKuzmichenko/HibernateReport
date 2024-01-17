package com.reports.hibernate.model.entity.collection.list;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class ListOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<ListPet> pets;

}

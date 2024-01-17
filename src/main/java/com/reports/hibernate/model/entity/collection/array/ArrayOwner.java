package com.reports.hibernate.model.entity.collection.array;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ArrayOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private ArrayPet[] pets;

}

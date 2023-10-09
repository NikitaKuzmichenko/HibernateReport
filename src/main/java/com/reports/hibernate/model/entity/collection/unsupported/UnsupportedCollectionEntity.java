package com.reports.hibernate.model.entity.collection.unsupported;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Queue;

@Entity
@Data
public class UnsupportedCollectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "reference_id")
    private Queue<UnsupportedReferencedEntity> referencedEntities;

}

package com.reports.hibernate.model.entity.loading.lazy.entitygraph;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@NamedEntityGraph(
    name = "graph.lazyEntityGraphOwner.firstPets",
    attributeNodes = {
            @NamedAttributeNode("cats"),
    }
)
@Data
@Entity
public class LazyEntityGraphOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<LazyEntityGraphCat> cats;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<LazyEntityGraphDog> dogs;

}

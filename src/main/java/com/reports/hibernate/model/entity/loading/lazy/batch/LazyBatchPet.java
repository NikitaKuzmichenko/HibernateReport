package com.reports.hibernate.model.entity.loading.lazy.batch;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
//@BatchSize(size = 5) also works, but globally
public class LazyBatchPet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne()
    @JoinColumn(name = "ownerId", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private LazyBatchOwner owner;

}

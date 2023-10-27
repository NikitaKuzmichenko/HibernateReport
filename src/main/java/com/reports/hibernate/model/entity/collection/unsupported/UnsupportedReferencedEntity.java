package com.reports.hibernate.model.entity.collection.unsupported;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class UnsupportedReferencedEntity implements Comparable<UnsupportedReferencedEntity> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Override
    public int compareTo(UnsupportedReferencedEntity o) {
        return 0;
    }

}

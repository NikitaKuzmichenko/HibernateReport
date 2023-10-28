package com.reports.hibernate.model.entity.creation.id.embedded;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;


@Data
@Entity
public class EmbeddedIdEntity {

    @EmbeddedId
    private EntityId id;

    private String address;

}

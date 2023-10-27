package com.reports.hibernate.model.entity.creation.id.embedded;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;


@Data
@Entity
public class EmbeddedIdUser {

    @EmbeddedId
    private UserId id;

    private String address;

}

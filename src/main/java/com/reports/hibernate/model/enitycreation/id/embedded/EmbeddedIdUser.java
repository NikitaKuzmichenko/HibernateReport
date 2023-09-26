package com.reports.hibernate.model.enitycreation.id.embedded;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;


@Entity
@Data
public class EmbeddedIdUser {

    @EmbeddedId
    private UserId id;

    private String address;

}

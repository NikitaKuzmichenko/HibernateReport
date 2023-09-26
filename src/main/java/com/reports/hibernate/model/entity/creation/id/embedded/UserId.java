package com.reports.hibernate.model.entity.creation.id.embedded;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class UserId implements Serializable {

    private String firstName;

    private String middleName;

    private String lastName;

}

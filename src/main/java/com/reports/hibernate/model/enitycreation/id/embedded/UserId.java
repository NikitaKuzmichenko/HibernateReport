package com.reports.hibernate.model.enitycreation.id.embedded;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
public class UserId implements Serializable {

    private String firstName;

    private String middleName;

    private String lastName;

}

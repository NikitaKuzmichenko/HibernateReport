package com.reports.hibernate.model.entity.state;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ManualIdGenerationUser {

    @Id
    private Long id;

    private String firstName;

    private String middleName;

    private String lastName;

}

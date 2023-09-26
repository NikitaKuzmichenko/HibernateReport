package com.reports.hibernate.model.entity.creation.minimalsettings;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class MinimalSettingsUser {
    @Id
    private Long id;

    private String firstName;

    private String middleName;

    private String lastName;
}

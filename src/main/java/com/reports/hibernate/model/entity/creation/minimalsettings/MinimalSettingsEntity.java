package com.reports.hibernate.model.entity.creation.minimalsettings;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class MinimalSettingsEntity {
    @Id
    private Long id;

    private String firstName;

    private String middleName;

    private String lastName;
}

package com.reports.hibernate.model.enitycreation.minimalsettings;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class MinimalSettingsUser {
    @Id
    private Long id;

    private String firstName;

    private String middleName;

    private String lastName;
}

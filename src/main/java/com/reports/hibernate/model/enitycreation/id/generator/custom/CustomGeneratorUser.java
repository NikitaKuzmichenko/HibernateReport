package com.reports.hibernate.model.enitycreation.id.generator.custom;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Data
public class CustomGeneratorUser {

    @Id
    @GeneratedValue(generator = "custom-generator")
    @GenericGenerator(name = "custom-generator",
            parameters = @Parameter(name = "prefix", value = "Random"),
            strategy = "com.reports.hibernate.model.enitycreation.id.generator.custom.generator.CustomIdGenerator")
    private String id;

    private String firstName;

    private String middleName;

    private String lastName;
}

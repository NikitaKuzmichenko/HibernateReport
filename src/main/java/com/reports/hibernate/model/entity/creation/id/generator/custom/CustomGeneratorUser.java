package com.reports.hibernate.model.entity.creation.id.generator.custom;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Data
@Entity
public class CustomGeneratorUser {

    @Id
    @GeneratedValue(generator = "custom-generator")
    @GenericGenerator(name = "custom-generator",
            parameters = @Parameter(name = "prefix", value = "Random"),
            strategy = "com.reports.hibernate.model.entity.creation.id.generator.custom.generator.CustomIdGenerator")
    private String id;

    private String firstName;

    private String middleName;

    private String lastName;
}

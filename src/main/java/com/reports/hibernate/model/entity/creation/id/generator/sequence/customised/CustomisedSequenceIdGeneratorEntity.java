package com.reports.hibernate.model.entity.creation.id.generator.sequence.customised;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
public class CustomisedSequenceIdGeneratorEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "post_sequence"
    )
    @SequenceGenerator(
            name = "post_sequence",
            allocationSize = 5,
            initialValue = 2
    )
    private Long id;

    private String firstName;

    private String middleName;

    private String lastName;
}

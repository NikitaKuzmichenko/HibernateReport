package com.reports.hibernate.model.entity.inheritance.table.single;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="child_type", discriminatorType = DiscriminatorType.INTEGER)
public class SingleTablePet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

}

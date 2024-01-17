package com.reports.hibernate.model.entity.inheritance.mapped;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class MappedDog extends MappedPet {

    private int barksPerDay;

}

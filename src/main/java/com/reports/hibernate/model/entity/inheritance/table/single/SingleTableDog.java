package com.reports.hibernate.model.entity.inheritance.table.single;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("1")
public class SingleTableDog extends SingleTablePet {

    private int barksPerDay;

}

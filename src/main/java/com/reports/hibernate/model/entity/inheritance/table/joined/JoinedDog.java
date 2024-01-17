package com.reports.hibernate.model.entity.inheritance.table.joined;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class JoinedDog extends JoinedPet {

    private int barksPerDay;

}

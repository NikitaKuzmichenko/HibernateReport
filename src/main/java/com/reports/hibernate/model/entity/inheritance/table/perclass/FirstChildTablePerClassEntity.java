package com.reports.hibernate.model.entity.inheritance.table.perclass;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class FirstChildTablePerClassEntity extends ParentTablePerClassEntity {

    private String name;

}

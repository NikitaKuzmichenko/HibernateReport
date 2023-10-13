package com.reports.hibernate.model.entity.inheritance.table.joined;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;

@Data
@Entity
@PrimaryKeyJoinColumn(name = "child_id")
public class FirstChildJoinedTableEntity extends ParentJoinedTableEntity {

    private String name;

}

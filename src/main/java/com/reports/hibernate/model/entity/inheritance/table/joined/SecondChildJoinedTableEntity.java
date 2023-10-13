package com.reports.hibernate.model.entity.inheritance.table.joined;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;

@Data
@Entity
public class SecondChildJoinedTableEntity extends ParentJoinedTableEntity {

    private String name;

}

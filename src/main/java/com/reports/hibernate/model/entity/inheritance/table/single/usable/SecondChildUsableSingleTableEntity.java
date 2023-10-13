package com.reports.hibernate.model.entity.inheritance.table.single.usable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("1")
public class SecondChildUsableSingleTableEntity extends ParentUsableSingleTableEntity {

    private String secondChildName;

}

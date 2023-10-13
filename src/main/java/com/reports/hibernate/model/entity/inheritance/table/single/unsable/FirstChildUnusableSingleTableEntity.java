package com.reports.hibernate.model.entity.inheritance.table.single.unsable;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("2")
public class FirstChildUnusableSingleTableEntity extends ParentUnusableSingleTableEntity {

    @Column(unique = true)
    private String firstChildName;

}

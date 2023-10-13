package com.reports.hibernate.model.entity.inheritance.mapped;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class SecondChildMappedEntity extends ParentMappedEntity{

    private String name;

}

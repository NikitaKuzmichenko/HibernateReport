package com.reports.hibernate.model.entity.inheritance.mapped;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class FirstChildMappedEntity extends ParentMappedEntity{

    private String name;

}

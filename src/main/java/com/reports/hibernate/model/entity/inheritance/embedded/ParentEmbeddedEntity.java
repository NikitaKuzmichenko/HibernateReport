package com.reports.hibernate.model.entity.inheritance.embedded;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class ParentEmbeddedEntity {
    private String parentName;
}

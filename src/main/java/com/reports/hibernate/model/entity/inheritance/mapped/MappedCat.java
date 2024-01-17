package com.reports.hibernate.model.entity.inheritance.mapped;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class MappedCat extends MappedPet {

    private int livesAmount;

}

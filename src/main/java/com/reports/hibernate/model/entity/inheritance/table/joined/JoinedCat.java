package com.reports.hibernate.model.entity.inheritance.table.joined;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;

@Data
@Entity
@PrimaryKeyJoinColumn(name = "pet_id")
public class JoinedCat extends JoinedPet {

    private int livesAmount;

}

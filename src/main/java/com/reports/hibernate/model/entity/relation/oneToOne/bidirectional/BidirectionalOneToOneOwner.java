package com.reports.hibernate.model.entity.relation.oneToOne.bidirectional;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class BidirectionalOneToOneOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pet_id")
    private BidirectionalOneToOnePet pet;

    public void addPet(BidirectionalOneToOnePet pet){
        if(pet != null){
            pet.setOwner(this);
        }
        this.pet = pet;
    }
}

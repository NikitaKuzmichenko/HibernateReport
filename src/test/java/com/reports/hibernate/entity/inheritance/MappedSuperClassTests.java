package com.reports.hibernate.entity.inheritance;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.inheritance.mapped.MappedCat;
import com.reports.hibernate.model.entity.inheritance.mapped.MappedPet;
import com.reports.hibernate.model.entity.inheritance.mapped.MappedDog;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.reports.hibernate.model.entity.inheritance.mapped") // scan only required entities
@DisplayName("MappedSuperClass inheritance")
class MappedSuperClassTests extends BaseTest {

    @Test
    @DisplayName("Insert parent entity")
    void insertParentEntity() {
        MappedPet pet = new MappedPet();
        pet.setName("Some Pet");
        assertThrows(org.hibernate.UnknownEntityTypeException.class, () -> {
            session.save(pet);
            session.flush();
        });
    }

    @Test
    @DisplayName("Insert child entities")
    void insertChildEntities() {
        MappedCat cat = new MappedCat();
        cat.setName("Cat");
        cat.setLivesAmount(9);
        MappedDog dog = new MappedDog();
        dog.setName("Dog");
        dog.setBarksPerDay(100);
        session.save(dog);
        session.save(cat);
        session.flush();
        AssertQueryCount.assertInsertCount(2);
    }

    @Test
    @DisplayName("Polymorphic select of all entities")
    void polymorphicSelect() {
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            session.createQuery("SELECT e FROM MappedPet e", MappedPet.class).list();
        });
    }
}

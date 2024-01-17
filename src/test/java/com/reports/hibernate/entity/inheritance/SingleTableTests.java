package com.reports.hibernate.entity.inheritance;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.inheritance.table.single.SingleTableCat;
import com.reports.hibernate.model.entity.inheritance.table.single.SingleTablePet;
import com.reports.hibernate.model.entity.inheritance.table.single.SingleTableDog;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;

@EntityScan("com.reports.hibernate.model.entity.inheritance.table.single") // scan only required entities
@DisplayName("UsableSingleTable inheritance")
class SingleTableTests extends BaseTest {

    @Test
    @DisplayName("Insert parent entity")
    void insertpet() {
        SingleTablePet pet = new SingleTablePet();
        pet.setName("Some Pet");
        session.save(pet);
        session.flush();
        AssertQueryCount.assertInsertCount(1);
    }

    @Test
    @DisplayName("Insert child entities")
    void insertChildEntities() {
        SingleTableCat cat = new SingleTableCat();
        cat.setName("Cat");
        cat.setLivesAmount(9);
        SingleTableDog dog = new SingleTableDog();
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
        SingleTablePet pet = new SingleTablePet();
        pet.setName("Some Pet");
        SingleTableCat cat = new SingleTableCat();
        cat.setName("Cat");
        cat.setLivesAmount(9);
        SingleTableDog dog = new SingleTableDog();
        dog.setName("Dog");
        dog.setBarksPerDay(100);
        session.save(pet);
        session.save(dog);
        session.save(cat);
        flushAndClear();
        List<SingleTablePet> list = session
                .createQuery("SELECT e FROM SingleTablePet e", SingleTablePet.class)
                .list();
        assertAll(
                () -> AssertQueryCount.assertSelectCount(1),
                () -> assertTrue(list.containsAll(List.of(pet, cat, dog)))
        );
    }
}

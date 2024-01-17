package com.reports.hibernate.entity.inheritance;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.inheritance.table.joined.JoinedCat;
import com.reports.hibernate.model.entity.inheritance.table.joined.JoinedPet;
import com.reports.hibernate.model.entity.inheritance.table.joined.JoinedDog;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;

@EntityScan("com.reports.hibernate.model.entity.inheritance.table.joined") // scan only required entities
@DisplayName("JoinedTable inheritance")
class JoinedTableTests extends BaseTest {

    @Test
    @DisplayName("Insert parent entity")
    void insertParentEntity() {
        JoinedPet pet = new JoinedPet();
        pet.setName("Some Pet");
        session.save(pet);
        session.flush();
        AssertQueryCount.assertInsertCount(1);
    }

    @Test
    @DisplayName("Insert child entities")
    void insertChildEntities() {
        JoinedCat cat = new JoinedCat();
        cat.setName("Cat");
        cat.setLivesAmount(9);
        JoinedDog dog = new JoinedDog();
        dog.setName("Dog");
        dog.setBarksPerDay(100);
        session.save(dog);
        session.save(cat);
        session.flush();
        AssertQueryCount.assertInsertCount(4);
    }

    @Test
    @DisplayName("Polymorphic select of all entities")
    void polymorphicSelect() {
        JoinedPet pet = new JoinedPet();
        pet.setName("Some Pet");
        JoinedCat cat = new JoinedCat();
        cat.setLivesAmount(9);
        cat.setName("Cat");
        JoinedDog dog = new JoinedDog();
        dog.setName("Dog");
        dog.setBarksPerDay(100);
        session.save(pet);
        session.save(dog);
        session.save(cat);
        flushAndClear();
        List<JoinedPet> list = session
                .createQuery("SELECT e FROM JoinedPet e", JoinedPet.class)
                .list();
        assertAll(
                () -> AssertQueryCount.assertSelectCount(1),
                () -> assertTrue(list.containsAll(List.of(pet, cat, dog)))
        );
    }
}

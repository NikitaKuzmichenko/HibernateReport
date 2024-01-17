package com.reports.hibernate.entity.inheritance;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.inheritance.table.perclass.TablePerClassDog;
import com.reports.hibernate.model.entity.inheritance.table.perclass.TablePerClassPet;
import com.reports.hibernate.model.entity.inheritance.table.perclass.TablePerClassCat;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;

@EntityScan("com.reports.hibernate.model.entity.inheritance.table.perclass") // scan only required entities
@DisplayName("TablePerClass inheritance")
class TablePerClassTests extends BaseTest {

    @Test
    @DisplayName("Insert parent entity")
    void insertpet() {
        TablePerClassPet pet = new TablePerClassPet();
        pet.setName("Some Pet");
        session.save(pet);
        session.flush();
        AssertQueryCount.assertInsertCount(1);
    }

    @Test
    @DisplayName("Insert child entities")
    void insertChildEntities() {
        TablePerClassDog dog = new TablePerClassDog();
        dog.setName("Dog");
        dog.setBarksPerDay(100);
        TablePerClassCat cat = new TablePerClassCat();
        cat.setName("Cat");
        cat.setLivesAmount(9);
        session.save(cat);
        session.save(dog);
        session.flush();
        AssertQueryCount.assertInsertCount(2);
    }

    @Test
    @DisplayName("Polymorphic select of all entities")
    void polymorphicSelect() {
        TablePerClassPet pet = new TablePerClassPet();
        pet.setName("Some pet");
        TablePerClassDog dog = new TablePerClassDog();
        dog.setName("Dog");
        dog.setBarksPerDay(100);
        TablePerClassCat cat = new TablePerClassCat();
        cat.setName("Cat");
        cat.setLivesAmount(9);
        session.save(pet);
        session.save(cat);
        session.save(dog);
        flushAndClear();
        List<TablePerClassPet> list = session
                .createQuery("SELECT e FROM TablePerClassPet e", TablePerClassPet.class)
                .list();
        assertAll(
                () -> AssertQueryCount.assertSelectCount(1),// count only one statement per query
                () -> assertTrue(list.containsAll(List.of(pet, dog, cat)))
        );
    }
}

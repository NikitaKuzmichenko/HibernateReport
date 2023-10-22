package com.reports.hibernate.entity.inheritance;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.inheritance.table.perclass.FirstChildTablePerClassEntity;
import com.reports.hibernate.model.entity.inheritance.table.perclass.ParentTablePerClassEntity;
import com.reports.hibernate.model.entity.inheritance.table.perclass.SecondChildTablePerClassEntity;
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
    void insertParentEntity() {
        ParentTablePerClassEntity parentEntity = new ParentTablePerClassEntity();
        parentEntity.setParentName("Parent");
        session.save(parentEntity);
        session.flush();
        AssertQueryCount.assertInsertCount(1);
    }

    @Test
    @DisplayName("Insert child entities")
    void insertChildEntities() {
        FirstChildTablePerClassEntity child1 = new FirstChildTablePerClassEntity();
        child1.setName("First child");
        SecondChildTablePerClassEntity child2 = new SecondChildTablePerClassEntity();
        child2.setName("Second child");
        session.save(child2);
        session.save(child1);
        session.flush();
        AssertQueryCount.assertInsertCount(2);
    }

    @Test
    @DisplayName("Polymorphic select of all entities")
    void polymorphicSelect() {
        ParentTablePerClassEntity parentEntity = new ParentTablePerClassEntity();
        parentEntity.setParentName("Parent");
        FirstChildTablePerClassEntity child1 = new FirstChildTablePerClassEntity();
        child1.setName("First child");
        SecondChildTablePerClassEntity child2 = new SecondChildTablePerClassEntity();
        child2.setName("Second child");
        session.save(parentEntity);
        session.save(child2);
        session.save(child1);
        flushAndClear();
        List<ParentTablePerClassEntity> list = session
                .createQuery("SELECT e FROM ParentTablePerClassEntity e", ParentTablePerClassEntity.class)
                .list();
        assertAll(
                () -> AssertQueryCount.assertSelectCount(1),// count only one statement per query
                () -> assertTrue(list.containsAll(List.of(parentEntity, child1, child2)))
        );
    }
}

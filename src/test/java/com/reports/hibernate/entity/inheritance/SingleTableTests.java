package com.reports.hibernate.entity.inheritance;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.inheritance.table.single.FirstChildSingleTableEntity;
import com.reports.hibernate.model.entity.inheritance.table.single.ParentSingleTableEntity;
import com.reports.hibernate.model.entity.inheritance.table.single.SecondChildSingleTableEntity;
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
    void insertParentEntity() {
        ParentSingleTableEntity parentEntity = new ParentSingleTableEntity();
        parentEntity.setParentName("Parent");
        session.save(parentEntity);
        session.flush();
        AssertQueryCount.assertInsertCount(1);
    }

    @Test
    @DisplayName("Insert child entities")
    void insertChildEntities() {
        FirstChildSingleTableEntity child1 = new FirstChildSingleTableEntity();
        child1.setFirstChildName("First child");
        SecondChildSingleTableEntity child2 = new SecondChildSingleTableEntity();
        child2.setSecondChildName("Second child");
        session.save(child2);
        session.save(child1);
        session.flush();
        AssertQueryCount.assertInsertCount(2);
    }

    @Test
    @DisplayName("Polymorphic select of all entities")
    void polymorphicSelect() {
        ParentSingleTableEntity parentEntity = new ParentSingleTableEntity();
        parentEntity.setParentName("Parent");
        FirstChildSingleTableEntity child1 = new FirstChildSingleTableEntity();
        child1.setFirstChildName("First child");
        SecondChildSingleTableEntity child2 = new SecondChildSingleTableEntity();
        child2.setSecondChildName("Second child");
        session.save(parentEntity);
        session.save(child2);
        session.save(child1);
        flushAndClear();
        List<ParentSingleTableEntity> list = session
                .createQuery("SELECT e FROM ParentSingleTableEntity e", ParentSingleTableEntity.class)
                .list();
        assertAll(
                () -> AssertQueryCount.assertSelectCount(1),
                () -> assertTrue(list.containsAll(List.of(parentEntity, child1, child2)))
        );
    }
}

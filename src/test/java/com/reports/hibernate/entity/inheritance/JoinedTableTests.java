package com.reports.hibernate.entity.inheritance;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.inheritance.table.joined.FirstChildJoinedTableEntity;
import com.reports.hibernate.model.entity.inheritance.table.joined.ParentJoinedTableEntity;
import com.reports.hibernate.model.entity.inheritance.table.joined.SecondChildJoinedTableEntity;
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
        ParentJoinedTableEntity parentEntity = new ParentJoinedTableEntity();
        parentEntity.setParentName("Parent");
        session.save(parentEntity);
        session.flush();
        AssertQueryCount.assertInsertCount(1);
    }

    @Test
    @DisplayName("Insert child entities")
    void insertChildEntities() {
        FirstChildJoinedTableEntity child1 = new FirstChildJoinedTableEntity();
        child1.setName("First child");
        SecondChildJoinedTableEntity child2 = new SecondChildJoinedTableEntity();
        child2.setName("Second child");
        session.save(child2);
        session.save(child1);
        session.flush();
        AssertQueryCount.assertInsertCount(4);
    }

    @Test
    @DisplayName("Polymorphic select of all entities")
    void polymorphicSelect() {
        ParentJoinedTableEntity parentEntity = new ParentJoinedTableEntity();
        parentEntity.setParentName("Parent");
        FirstChildJoinedTableEntity child1 = new FirstChildJoinedTableEntity();
        child1.setName("First child");
        SecondChildJoinedTableEntity child2 = new SecondChildJoinedTableEntity();
        child2.setName("Second child");
        session.save(parentEntity);
        session.save(child2);
        session.save(child1);
        flushAndClear();
        List<ParentJoinedTableEntity> list = session
                .createQuery("SELECT e FROM ParentJoinedTableEntity e", ParentJoinedTableEntity.class)
                .list();
        assertAll(
                () -> AssertQueryCount.assertSelectCount(1),
                () -> assertTrue(list.containsAll(List.of(parentEntity, child1, child2)))
        );
    }
}

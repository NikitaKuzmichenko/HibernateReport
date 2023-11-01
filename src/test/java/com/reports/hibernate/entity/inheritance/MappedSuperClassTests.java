package com.reports.hibernate.entity.inheritance;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.inheritance.mapped.FirstChildMappedEntity;
import com.reports.hibernate.model.entity.inheritance.mapped.ParentMappedEntity;
import com.reports.hibernate.model.entity.inheritance.mapped.SecondChildMappedEntity;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;

@EntityScan("com.reports.hibernate.model.entity.inheritance.mapped") // scan only required entities
@DisplayName("MappedSuperClass inheritance")
class MappedSuperClassTests extends BaseTest {

    @Test
    @DisplayName("Insert parent entity")
    void insertParentEntity() {
        ParentMappedEntity parentEntity = new ParentMappedEntity();
        parentEntity.setParentName("Parent");
        assertThrows(org.hibernate.UnknownEntityTypeException.class, () -> {
            session.save(parentEntity);
            session.flush();
        });
    }

    @Test
    @DisplayName("Insert child entities")
    void insertChildEntities() {
        FirstChildMappedEntity child1 = new FirstChildMappedEntity();
        child1.setName("First child");
        SecondChildMappedEntity child2 = new SecondChildMappedEntity();
        child2.setName("Second child");
        session.save(child2);
        session.save(child1);
        session.flush();
        AssertQueryCount.assertInsertCount(2);
    }

    @Test
    @DisplayName("Polymorphic select of all entities")
    void polymorphicSelect() {
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            session.createQuery("SELECT e FROM ParentMappedEntity e", ParentMappedEntity.class).list();
        });
    }
}

package com.reports.hibernate.entity.relations.oneToOne;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.relations.oneToOne.unidirectional.UnidirectionalOneToOneChild;
import com.reports.hibernate.model.entity.relations.oneToOne.unidirectional.UnidirectionalOneToOneParent;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.reports.hibernate.model.entity.relations.oneToOne.unidirectional") // scan only required entities
@DisplayName("Unidirectional OneToOne relationship")
class UnidirectionalOneToOneTests extends BaseTest {

    @Test
    @DisplayName("One sided saving of parents")
    void oneSidedSaveOfParent() {
        UnidirectionalOneToOneChild child = new UnidirectionalOneToOneChild();
        child.setChildName("Child");
        UnidirectionalOneToOneParent parent = new UnidirectionalOneToOneParent();
        parent.setParentName("Parent");
        parent.setChild(child);
        session.persist(parent);
        flushAndClear();
        assertAll(
                () -> AssertQueryCount.assertInsertCount(2)
        );
    }
}

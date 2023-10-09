package com.reports.hibernate.entity.relation.oneToOne;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.relation.oneToOne.unidirectional.UnidirectionalOneToOneChild;
import com.reports.hibernate.model.entity.relation.oneToOne.unidirectional.UnidirectionalOneToOneParent;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.reports.hibernate.model.entity.relation.oneToOne.unidirectional") // scan only required entities
@DisplayName("Unidirectional OneToOne relationhip")
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
                () -> AssertQueryCount.assertInsertCount(2),
                () -> AssertQueryCount.assertUpdateCount(0)
        );
    }
}

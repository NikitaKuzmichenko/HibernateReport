package com.reports.hibernate.entity.relations.oneToOne;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.relations.oneToOne.bidirectional.BidirectionalOneToOneChild;
import com.reports.hibernate.model.entity.relations.oneToOne.bidirectional.BidirectionalOneToOneParent;
import com.reports.hibernate.model.entity.relations.oneToOne.unidirectional.UnidirectionalOneToOneChild;
import com.reports.hibernate.model.entity.relations.oneToOne.unidirectional.UnidirectionalOneToOneParent;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.reports.hibernate.model.entity.relations.oneToOne.bidirectional") // scan only required entities
@DisplayName("Bidirectional OneToOne relationship")
class BidirectionalOneToOneTests extends BaseTest {

    @Test
    @DisplayName("One sided saving of parent")
    void oneSidedSaveOfParent() {
        BidirectionalOneToOneChild child = new BidirectionalOneToOneChild();
        child.setChildName("Child");
        BidirectionalOneToOneParent parent = new BidirectionalOneToOneParent();
        parent.setParentName("Parent");
        parent.setChild(child);
        session.persist(parent);
        flushAndClear();
        assertAll(
                () -> AssertQueryCount.assertInsertCount(2)
        );
    }

    @Test
    @DisplayName("One sided saving of child. Reference from child to parent not set")
    void oneSidedSaveOfChild() {
        BidirectionalOneToOneChild child = new BidirectionalOneToOneChild();
        child.setChildName("Child");
        BidirectionalOneToOneParent parent = new BidirectionalOneToOneParent();
        parent.setParentName("Parent");
        parent.setChild(child);
        session.persist(child);
        flushAndClear();
        assertAll(
                () -> AssertQueryCount.assertInsertCount(1)
        );
    }

    @Test
    @DisplayName("One sided saving of child. Using of consistent setters")
    void oneSidedConsistentSaveOfChild() {
        BidirectionalOneToOneChild child = new BidirectionalOneToOneChild();
        child.setChildName("Child");
        BidirectionalOneToOneParent parent = new BidirectionalOneToOneParent();
        parent.setParentName("Parent");
        child.addParent(parent);
        session.persist(child);
        flushAndClear();
        assertAll(
                () -> AssertQueryCount.assertInsertCount(2)
        );
    }
}

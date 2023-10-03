package com.reports.hibernate.entity.relations.oneToOne;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.relations.oneToOne.bidirectional.BidirectionalOneToOneChild;
import com.reports.hibernate.model.entity.relations.oneToOne.bidirectional.BidirectionalOneToOneParent;
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
                () -> assertNull(child.getParent()),
                () -> AssertQueryCount.assertInsertCount(2)
        );
    }
}

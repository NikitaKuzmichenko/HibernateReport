package com.reports.hibernate.entity.relations.manyToOne;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.relations.manyToOne.bidirectional.BidirectionalManyToOneChild;
import com.reports.hibernate.model.entity.relations.manyToOne.bidirectional.BidirectionalManyToOneParent;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.reports.hibernate.model.entity.relations.manyToOne.bidirectional;") // scan only required entities
@DisplayName("Bidirectional ManyToOne relationship")
public class BidirectionalManyToOneTest extends BaseTest {

    @Test
    @DisplayName("One sided saving of parents")
    void oneSidedSaveOfParent() {
        BidirectionalManyToOneChild child = new BidirectionalManyToOneChild();
        child.setChildName("Child");
        BidirectionalManyToOneParent parent = new BidirectionalManyToOneParent();
        parent.setParentName("Parent");
        parent.setChild(child);
        session.persist(parent);
        flushAndClear();
        assertAll(
                () -> assertNull(child.getParents()),
                () -> AssertQueryCount.assertInsertCount(2)
        );
    }

}

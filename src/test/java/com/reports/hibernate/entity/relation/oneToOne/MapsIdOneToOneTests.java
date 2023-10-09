package com.reports.hibernate.entity.relation.oneToOne;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.relation.oneToOne.mapsId.MapsIdOneToOneChild;
import com.reports.hibernate.model.entity.relation.oneToOne.mapsId.MapsIdOneToOneParent;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.reports.hibernate.model.entity.relation.oneToOne.mapsId") // scan only required entities
@DisplayName("OneToOne relationhip with same PK")
class MapsIdOneToOneTests extends BaseTest {

    @Test
    @DisplayName("One sided saving of parents")
    void oneSidedSaveOfParent() {
        MapsIdOneToOneChild child = new MapsIdOneToOneChild();
        child.setChildName("Child");
        MapsIdOneToOneParent parent = new MapsIdOneToOneParent();
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

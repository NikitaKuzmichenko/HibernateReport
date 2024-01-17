package com.reports.hibernate.entity.relation.oneToOne;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.relation.oneToOne.bidirectional.BidirectionalOneToOnePet;
import com.reports.hibernate.model.entity.relation.oneToOne.bidirectional.BidirectionalOneToOneOwner;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.reports.hibernate.model.entity.relation.oneToOne.bidirectional") // scan only required entities
@DisplayName("Bidirectional OneToOne relationhip")
class BidirectionalOneToOneTests extends BaseTest {

    @Test
    @DisplayName("One sided saving of parent")
    void oneSidedSaveOfParent() {
        BidirectionalOneToOnePet pet = new BidirectionalOneToOnePet();
        pet.setName("Pet");
        BidirectionalOneToOneOwner owner = new BidirectionalOneToOneOwner();
        owner.setName("Owner");
        owner.setPet(pet);
        session.persist(owner);
        flushAndClear();
        assertAll(
                () -> assertNull(pet.getOwner()),
                () -> AssertQueryCount.assertInsertCount(2),
                () -> AssertQueryCount.assertUpdateCount(0)
        );
    }
}

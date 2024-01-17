package com.reports.hibernate.entity.relation.manyToOne;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.relation.manyToOne.bidirectional.BidirectionalManyToOneOwner;
import com.reports.hibernate.model.entity.relation.manyToOne.bidirectional.BidirectionalManyToOnePet;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.reports.hibernate.model.entity.relation.manyToOne.bidirectional;") // scan only required entities
@DisplayName("Bidirectional ManyToOne relationhip")
public class BidirectionalManyToOneTest extends BaseTest {

    @Test
    @DisplayName("One sided saving of parents")
    void oneSidedSaveOfParent() {
        BidirectionalManyToOneOwner owner = new BidirectionalManyToOneOwner();
        owner.setName("Owner");
        BidirectionalManyToOnePet pet = new BidirectionalManyToOnePet();
        pet.setName("Pet");
        pet.setOwner(owner);
        session.persist(pet);
        flushAndClear();
        assertAll(
                () -> assertNull(owner.getPets()),
                () -> AssertQueryCount.assertInsertCount(2),
                () -> AssertQueryCount.assertUpdateCount(0)
        );
    }

}

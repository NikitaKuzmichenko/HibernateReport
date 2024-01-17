package com.reports.hibernate.entity.relation.oneToOne;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.relation.oneToOne.mapsId.MapsIdOneToOneOwner;
import com.reports.hibernate.model.entity.relation.oneToOne.mapsId.MapsIdOneToOnePet;
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
        MapsIdOneToOneOwner pet = new MapsIdOneToOneOwner();
        pet.setName("Pet");
        MapsIdOneToOnePet owner = new MapsIdOneToOnePet();
        owner.setName("Owner");
        owner.setOwner(pet);
        session.persist(owner);
        flushAndClear();
        assertAll(
                () -> AssertQueryCount.assertInsertCount(2),
                () -> AssertQueryCount.assertUpdateCount(0)
        );
    }
}

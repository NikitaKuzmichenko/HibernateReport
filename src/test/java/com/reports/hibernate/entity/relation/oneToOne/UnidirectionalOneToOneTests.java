package com.reports.hibernate.entity.relation.oneToOne;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.relation.oneToOne.unidirectional.UnidirectionalOneToOneOwner;
import com.reports.hibernate.model.entity.relation.oneToOne.unidirectional.UnidirectionalOneToOnePet;
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
        UnidirectionalOneToOneOwner pet = new UnidirectionalOneToOneOwner();
        pet.setName("Pet");
        UnidirectionalOneToOnePet owner = new UnidirectionalOneToOnePet();
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

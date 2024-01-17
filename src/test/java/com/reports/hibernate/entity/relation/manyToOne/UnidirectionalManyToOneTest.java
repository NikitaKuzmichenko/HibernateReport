package com.reports.hibernate.entity.relation.manyToOne;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.relation.manyToOne.unidirectional.UnidirectionalManyToOneOwner;
import com.reports.hibernate.model.entity.relation.manyToOne.unidirectional.UnidirectionalManyToOnePet;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.reports.hibernate.model.entity.relation.manyToOne.unidirectional;") // scan only required entities
@DisplayName("Unidirectional ManyToOne relationhip")
public class UnidirectionalManyToOneTest extends BaseTest {

    @Test
    @DisplayName("One sided saving of parents")
    void oneSidedSaveOfParent() {
        UnidirectionalManyToOneOwner owner = new UnidirectionalManyToOneOwner();
        owner.setName("Owner");
        UnidirectionalManyToOnePet pet = new UnidirectionalManyToOnePet();
        pet.setName("Pet");
        pet.setOwner(owner);
        session.persist(pet);
        flushAndClear();
        assertAll(
                () -> AssertQueryCount.assertInsertCount(2),
                () -> AssertQueryCount.assertUpdateCount(0)
        );
    }

}

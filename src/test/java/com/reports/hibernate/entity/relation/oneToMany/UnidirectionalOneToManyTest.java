package com.reports.hibernate.entity.relation.oneToMany;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.relation.oneToMany.unidirectional.UnidirectionalOneToManyPet;
import com.reports.hibernate.model.entity.relation.oneToMany.unidirectional.UnidirectionalOneToManyOwner;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;

@EntityScan("com.reports.hibernate.model.entity.relation.oneToMany.unidirectional;") // scan only required entities
@DisplayName("Unidirectional OneToMany relationhip")
public class UnidirectionalOneToManyTest extends BaseTest {

    @Test
    @DisplayName("One sided saving of parents")
    void oneSidedSaveOfParent() {
        UnidirectionalOneToManyPet pet = new UnidirectionalOneToManyPet();
        pet.setName("Pet");
        UnidirectionalOneToManyOwner owner = new UnidirectionalOneToManyOwner();
        owner.setName("Owner");
        owner.setPets(List.of(pet));
        session.persist(owner);
        flushAndClear();
        assertAll(
                () -> AssertQueryCount.assertInsertCount(2),
                () -> AssertQueryCount.assertUpdateCount(1)
        );
    }

}

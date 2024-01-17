package com.reports.hibernate.entity.relation.oneToMany;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.relation.oneToMany.bad.BadOneToManyPet;
import com.reports.hibernate.model.entity.relation.oneToMany.bad.BadOneToManyOwner;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;

@EntityScan("com.reports.hibernate.model.entity.relation.oneToMany.bad;") // scan only required entities
@DisplayName("Bad unidirectional OneToMany relationhip")
public class BadOneToManyTest extends BaseTest {

    @Test
    @DisplayName("One sided saving of parents")
    void oneSidedSaveOfParent() {
        BadOneToManyPet pet = new BadOneToManyPet();
        pet.setName("Pet");
        BadOneToManyOwner owner = new BadOneToManyOwner();
        owner.setName("Owner");
        owner.setPets(List.of(pet));
        session.persist(owner);
        flushAndClear();
        assertAll(
                () -> AssertQueryCount.assertInsertCount(3)
        );
    }

}

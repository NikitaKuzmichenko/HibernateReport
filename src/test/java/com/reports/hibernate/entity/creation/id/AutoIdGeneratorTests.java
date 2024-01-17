package com.reports.hibernate.entity.creation.id;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.creation.id.generator.auto.AutoIdGeneratorOwner;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.reports.hibernate.model.entity.creation.id.generator.auto") // scan only required entities
@DisplayName("Entity with auto id generator")
class AutoIdGeneratorTests extends BaseTest {

    @Test
    @DisplayName("Create and get entity")
    void createAndGetEntity() {
        AutoIdGeneratorOwner user = new AutoIdGeneratorOwner();
        user.setFirstName("First One");
        user.setMiddleName("Middle One");
        user.setLastName("Last One");
        long id = (long) session.save(user);
        flushAndClear();
        AutoIdGeneratorOwner fetchedUser = session.get(AutoIdGeneratorOwner.class, id);
        assertAll(
                () -> AssertQueryCount.assertNextValCount(1),
                () -> AssertQueryCount.assertInsertCount(1),
                () -> AssertQueryCount.assertSelectCount(1),
                () -> assertEquals(user, fetchedUser),
                () -> assertNotSame(user, fetchedUser)
        );
    }

}

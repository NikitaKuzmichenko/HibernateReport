package com.reports.hibernate.entitycreation.id.generator.custom;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.enitycreation.id.generator.auto.AutoIdGeneratorUser;
import com.reports.hibernate.model.enitycreation.id.generator.custom.CustomGeneratorUser;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.reports.hibernate.model.enitycreation.id.generator.custom") // scan only required entities
@DisplayName("Entity with custom id generator")
class CustomIdGeneratorTests extends BaseTest {

    @Test
    @DisplayName("Create and get entity")
    void entityWithAllFields() {
        CustomGeneratorUser user = new CustomGeneratorUser();
        user.setFirstName("First One");
        user.setMiddleName("Middle One");
        user.setLastName("Last One");
        String id = (String) session.save(user);
        flushAndClear();

        CustomGeneratorUser user2 = new CustomGeneratorUser();
        user2.setFirstName("First Second");
        user2.setMiddleName("Middle Second");
        user2.setLastName("Last Second");
        String id2 = (String) session.save(user2);
        flushAndClear();

        CustomGeneratorUser fetchedUser1 = session.get(CustomGeneratorUser.class, id);
        CustomGeneratorUser fetchedUser2 = session.get(CustomGeneratorUser.class, id2);
        assertAll(
                () -> AssertQueryCount.assertInsertCount(2),
                () -> AssertQueryCount.assertSelectCount(2),
                () -> assertEquals(user, fetchedUser1),
                () -> assertNotSame(user, fetchedUser1),
                () -> assertEquals(user2, fetchedUser2),
                () -> assertNotSame(user2, fetchedUser2),
                () -> assertNotEquals(user.getId(), user2.getId())
        );
    }

}

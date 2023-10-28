package com.reports.hibernate.entity.creation.id;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.creation.id.generator.custom.CustomGeneratorEntity;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.reports.hibernate.model.entity.creation.id.generator.custom") // scan only required entities
@DisplayName("Entity with custom id generator")
class CustomIdGeneratorTests extends BaseTest {

    @Test
    @DisplayName("Create and get entity")
    void createAndGetEntity() {
        CustomGeneratorEntity user = new CustomGeneratorEntity();
        user.setFirstName("First One");
        user.setMiddleName("Middle One");
        user.setLastName("Last One");
        String id = (String) session.save(user);
        flushAndClear();

        CustomGeneratorEntity user2 = new CustomGeneratorEntity();
        user2.setFirstName("First Second");
        user2.setMiddleName("Middle Second");
        user2.setLastName("Last Second");
        String id2 = (String) session.save(user2);
        flushAndClear();

        CustomGeneratorEntity fetchedUser1 = session.get(CustomGeneratorEntity.class, id);
        CustomGeneratorEntity fetchedUser2 = session.get(CustomGeneratorEntity.class, id2);
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

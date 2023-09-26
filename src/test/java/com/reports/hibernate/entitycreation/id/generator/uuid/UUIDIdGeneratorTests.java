package com.reports.hibernate.entitycreation.id.generator.uuid;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.enitycreation.id.generator.uuid.UUIDGeneratorUser;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.UUID;

@EntityScan("com.reports.hibernate.model.enitycreation.id.generator.uuid") // scan only required entities
@DisplayName("Entity with uuid id generator")
class UUIDIdGeneratorTests extends BaseTest {

    @Test
    @DisplayName("Create and get entity")
    void createAndGetEntity() {
        UUIDGeneratorUser user = new UUIDGeneratorUser();
        user.setFirstName("First One");
        user.setMiddleName("Middle One");
        user.setLastName("Last One");
        UUID id = (UUID) session.save(user);
        flushAndClear();
        UUIDGeneratorUser fetchedUser = session.get(UUIDGeneratorUser.class, id);
        assertAll(
                () -> AssertQueryCount.assertInsertCount(1),
                () -> AssertQueryCount.assertSelectCount(1),
                () -> assertEquals(user, fetchedUser),
                () -> assertNotSame(user, fetchedUser)
        );
    }

}

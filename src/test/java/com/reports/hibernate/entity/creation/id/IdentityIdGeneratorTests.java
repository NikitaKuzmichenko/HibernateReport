package com.reports.hibernate.entity.creation.id;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.creation.id.generator.identity.IdentityIdGeneratorEntity;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.reports.hibernate.model.entity.creation.id.generator.identity") // scan only required entities
@DisplayName("Entity with identity id generator")
class IdentityIdGeneratorTests extends BaseTest {

    @Test
    @DisplayName("Create and get entity")
    void createAndGetEntity() {
        IdentityIdGeneratorEntity user = new IdentityIdGeneratorEntity();
        user.setId(1L);
        user.setFirstName("First One");
        user.setMiddleName("Middle One");
        user.setLastName("Last One");
        session.save(user);
        flushAndClear();
        IdentityIdGeneratorEntity fetchedUser = session.get(IdentityIdGeneratorEntity.class, 1L);
        assertAll(
                () -> AssertQueryCount.assertInsertCount(1),
                () -> AssertQueryCount.assertSelectCount(1),
                () -> assertEquals(user, fetchedUser),
                () -> assertNotSame(user, fetchedUser)
        );
    }


}

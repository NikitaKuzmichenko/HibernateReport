package com.reports.hibernate.entitycreation.id.generator.identity;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.enitycreation.id.generator.identity.IdentityIdGeneratorUser;
import com.reports.hibernate.model.enitycreation.minimalsettings.MinimalSettingsUser;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.reports.hibernate.model.enitycreation.id.generator.identity") // scan only required entities
@DisplayName("Entity with identity id generator")
class IdentityIdGeneratorTests extends BaseTest {

    @Test
    @DisplayName("Create and get entity")
    void entityWithAllFields() {
        IdentityIdGeneratorUser user = new IdentityIdGeneratorUser();
        user.setId(1L);
        user.setFirstName("First One");
        user.setMiddleName("Middle One");
        user.setLastName("Last One");
        session.save(user);
        flushAndClear();
        IdentityIdGeneratorUser fetchedUser = session.get(IdentityIdGeneratorUser.class, 1L);
        assertAll(
                () -> AssertQueryCount.assertInsertCount(1),
                () -> AssertQueryCount.assertSelectCount(1),
                () -> assertEquals(user, fetchedUser),
                () -> assertNotSame(user, fetchedUser)
        );
    }


}

package com.reports.hibernate.entitycreation.minimalsettings;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.enitycreation.minimalsettings.MinimalSettingsUser;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.reports.hibernate.model.enitycreation.minimalsettings") // scan only required entities
@DisplayName("Entity with minimal settings")
class MinimalSettingsTests extends BaseTest {

    @Test
    @DisplayName("Entity saving with all fields set")
    void createAndGetEntityWithAllFields() {
        MinimalSettingsUser user = new MinimalSettingsUser();
        user.setId(1L);
        user.setFirstName("First One");
        user.setMiddleName("Middle One");
        user.setLastName("Last One");
        session.save(user);
        flushAndClear();
        MinimalSettingsUser fetchedUser = session.get(MinimalSettingsUser.class, 1L);
        assertAll(
                () -> AssertQueryCount.assertInsertCount(1),
                () -> AssertQueryCount.assertSelectCount(1),
                () -> assertEquals(user, fetchedUser)
        );
    }

    @Test
    @DisplayName("Entity saving with only required fields set")
    void createAndGetEntityWithRequiredFields() {
        MinimalSettingsUser user = new MinimalSettingsUser();
        user.setId(1L);
        session.save(user);
        flushAndClear();
        MinimalSettingsUser fetchedUser = session.get(MinimalSettingsUser.class, 1L);
        assertAll(
                () -> AssertQueryCount.assertInsertCount(1),
                () -> AssertQueryCount.assertSelectCount(1),
                () -> assertEquals(user, fetchedUser),
                () -> assertNotSame(user, fetchedUser)
        );
    }

}

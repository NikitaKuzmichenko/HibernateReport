package com.reports.hibernate.entitycreation.id.generator.sequence;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.enitycreation.id.generator.auto.AutoIdGeneratorUser;
import com.reports.hibernate.model.enitycreation.id.generator.sequence.SequenceIdGeneratorUser;
import com.reports.hibernate.model.enitycreation.minimalsettings.MinimalSettingsUser;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.reports.hibernate.model.enitycreation.id.generator.sequence") // scan only required entities
@DisplayName("Entity with sequence id generator")
class SequenceIdGeneratorTests extends BaseTest {

    @Test
    @DisplayName("Create and get entity")
    void entityWithAllFields() {
        SequenceIdGeneratorUser user = new SequenceIdGeneratorUser();
        user.setFirstName("First One");
        user.setMiddleName("Middle One");
        user.setLastName("Last One");
        long id = (long) session.save(user);
        flushAndClear();
        SequenceIdGeneratorUser fetchedUser = session.get(SequenceIdGeneratorUser.class, id);
        assertAll(
                () -> AssertQueryCount.assertNextValCount(1),
                () -> AssertQueryCount.assertInsertCount(1),
                () -> AssertQueryCount.assertSelectCount(1),
                () -> assertEquals(user, fetchedUser),
                () -> assertNotSame(user, fetchedUser)
        );
    }

}

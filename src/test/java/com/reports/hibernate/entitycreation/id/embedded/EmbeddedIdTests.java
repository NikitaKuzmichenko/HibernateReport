package com.reports.hibernate.entitycreation.id.embedded;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.enitycreation.id.embedded.EmbeddedIdUser;
import com.reports.hibernate.model.enitycreation.id.embedded.UserId;
import com.reports.hibernate.model.enitycreation.id.generator.auto.AutoIdGeneratorUser;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.reports.hibernate.model.enitycreation.id.embedded") // scan only required entities
@DisplayName("Entity with embedded id")
class EmbeddedIdTests extends BaseTest {

    @Test
    @DisplayName("Create and get entity")
    void entityWithAllFields() {
        UserId id = new UserId();
        id.setFirstName("First One");
        id.setMiddleName("Middle One");
        id.setLastName("Last One");
        EmbeddedIdUser user = new EmbeddedIdUser();
        user.setId(id);
        user.setAddress("Address One");
        session.save(user);
        flushAndClear();
        EmbeddedIdUser fetchedUser = session.get(EmbeddedIdUser.class, id);
        assertAll(
                () -> AssertQueryCount.assertInsertCount(1),
                () -> AssertQueryCount.assertSelectCount(1),
                () -> assertEquals(user, fetchedUser),
                () -> assertNotSame(user, fetchedUser)
        );
    }

}
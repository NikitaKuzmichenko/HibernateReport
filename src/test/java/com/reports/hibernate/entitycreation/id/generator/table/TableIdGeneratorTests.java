package com.reports.hibernate.entitycreation.id.generator.table;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.enitycreation.id.generator.table.TableIdGeneratorUser;
import com.reports.hibernate.model.enitycreation.minimalsettings.MinimalSettingsUser;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.reports.hibernate.model.enitycreation.id.generator.table") // scan only required entities
@DisplayName("Entity with table id generator")
class TableIdGeneratorTests extends BaseTest {

    @Test
    @DisplayName("Create and get entity")
    void createAndGetEntity() {
        TableIdGeneratorUser user = new TableIdGeneratorUser();
        user.setFirstName("First One");
        user.setMiddleName("Middle One");
        user.setLastName("Last One");
        long id = (long) session.save(user);
        flushAndClear();
        TableIdGeneratorUser fetchedUser = session.get(TableIdGeneratorUser.class, id);
        // additional select from generator-table and update of it generated be prepared statement
        // and doesn't use hibernate StatementInspector
        // u can see org.hibernate.id.enhanced.TableGenerator for your self
        // and org.hibernate.id.enhanced.SequenceStructure, its works there.
        assertAll(
                () -> AssertQueryCount.assertInsertCount(1),
                () -> AssertQueryCount.assertSelectCount(1),
                () -> assertEquals(user, fetchedUser),
                () -> assertNotSame(user, fetchedUser)
        );
    }

}

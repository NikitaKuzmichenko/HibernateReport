package com.reports.hibernate.entitycreation.id.generator.sequence;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.enitycreation.id.generator.sequence.customised.CustomisedSequenceIdGeneratorUser;
import com.reports.hibernate.model.enitycreation.id.generator.sequence.base.SequenceIdGeneratorUser;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.ArrayList;
import java.util.List;

@EntityScan("com.reports.hibernate.model.enitycreation.id.generator.sequence.customised") // scan only required entities
@DisplayName("Entity with pooled sequence id generator")
class CustomisedSequenceIdGeneratorTests extends BaseTest {

    @Test
    @DisplayName("Create and get one entity")
    void createAndGetEntity() {
        CustomisedSequenceIdGeneratorUser user = new CustomisedSequenceIdGeneratorUser();
        user.setFirstName("First One");
        user.setMiddleName("Middle One");
        user.setLastName("Last One");
        long id = (long) session.save(user);
        flushAndClear();
        CustomisedSequenceIdGeneratorUser fetchedUser = session.get(CustomisedSequenceIdGeneratorUser.class, id);
        assertAll(
                () -> AssertQueryCount.assertNextValCount(1),
                () -> AssertQueryCount.assertInsertCount(1),
                () -> AssertQueryCount.assertSelectCount(1),
                () -> assertEquals(user, fetchedUser),
                () -> assertNotSame(user, fetchedUser)
        );
    }

    @Test
    @DisplayName("Create and get multiple entities")
    void createAndGetMultipleEntities() {
        int entitiesCount = 10;
        List<CustomisedSequenceIdGeneratorUser> users = new ArrayList<>();
        List<Long> userIds = new ArrayList<>();
        for (int i = 0; i < entitiesCount; i++) {
            CustomisedSequenceIdGeneratorUser user = new CustomisedSequenceIdGeneratorUser();
            user.setFirstName("First " + i);
            user.setMiddleName("Middle " + i);
            user.setLastName("Last " + i);
            users.add(user);
            userIds.add((Long) session.save(user));
        }
        flushAndClear();
        List<CustomisedSequenceIdGeneratorUser> fetchedUsers = new ArrayList<>();
        for (long id : userIds) {
            fetchedUsers.add(session.get(CustomisedSequenceIdGeneratorUser.class, id));
        }
        // increment size = 5 and default initial value = 2
        // next val №1 = selected [2]
        // next val №2 = selected [3, 4, 5, 6, 7]
        // next val №3 = selected [8, 9, 10, 11, 12]
        assertAll(
                () -> AssertQueryCount.assertNextValCount(3),
                () -> AssertQueryCount.assertInsertCount(entitiesCount),
                () -> AssertQueryCount.assertSelectCount(entitiesCount),
                () -> assertEquals(users, fetchedUsers)
        );
    }

}
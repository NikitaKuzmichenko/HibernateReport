package com.reports.hibernate.entity.creation.id;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.creation.id.generator.sequence.base.SequenceIdGeneratorEntity;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.ArrayList;
import java.util.List;

@EntityScan("com.reports.hibernate.model.entity.creation.id.generator.sequence.base") // scan only required entities
@DisplayName("Entity with sequence id generator")
class SequenceIdGeneratorTests extends BaseTest {

    @Test
    @DisplayName("Create and get multiple entities")
    void createAndGetMultipleEntities() {
        int entitiesCount = 5;
        List<SequenceIdGeneratorEntity> users = new ArrayList<>();
        List<Long> userIds = new ArrayList<>();
        for (int i = 0; i < entitiesCount; i++) {
            SequenceIdGeneratorEntity user = new SequenceIdGeneratorEntity();
            user.setFirstName("First " + i);
            user.setMiddleName("Middle " + i);
            user.setLastName("Last " + i);
            users.add(user);
            userIds.add((Long) session.save(user));
        }
        flushAndClear();
        List<SequenceIdGeneratorEntity> fetchedUsers = new ArrayList<>();
        for(long id : userIds){
            fetchedUsers.add(session.get(SequenceIdGeneratorEntity.class, id));
        }
        // Default increment size = 50 and default initial value = 1
        // Hibernate executes one select to get the ID from the sequence
        // If the selected value is equal to the sequence initial value,
        // the Hibernate selects the next ID from the sequence as a high value,
        // setting the initial value as a range low value. (so hibernate fetches form 1 to 51)
        assertAll(
                () -> AssertQueryCount.assertNextValCount(2),
                () -> AssertQueryCount.assertInsertCount(entitiesCount),
                () -> AssertQueryCount.assertSelectCount(entitiesCount),
                () -> assertEquals(users, fetchedUsers)
        );
    }
}

package com.reports.hibernate.entity.relations.oneToMany;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.relations.oneToMany.bad.BadOneToManyChild;
import com.reports.hibernate.model.entity.relations.oneToMany.bad.BadOneToManyParent;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;

@EntityScan("com.reports.hibernate.model.entity.relations.oneToMany.bad;") // scan only required entities
@DisplayName("Bad unidirectional OneToMany relationship")
public class BadOneToManyTest extends BaseTest {

    @Test
    @DisplayName("One sided saving of parents")
    void oneSidedSaveOfParent() {
        BadOneToManyChild child = new BadOneToManyChild();
        child.setChildName("Child");
        BadOneToManyParent parent = new BadOneToManyParent();
        parent.setParentName("Parent");
        parent.setChildren(List.of(child));
        session.persist(parent);
        flushAndClear();
        assertAll(
                () -> AssertQueryCount.assertInsertCount(3)
        );
    }

}

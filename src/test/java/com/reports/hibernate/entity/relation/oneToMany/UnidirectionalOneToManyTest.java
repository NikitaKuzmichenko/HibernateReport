package com.reports.hibernate.entity.relation.oneToMany;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.relation.oneToMany.unidirectional.UnidirectionalOneToManyChild;
import com.reports.hibernate.model.entity.relation.oneToMany.unidirectional.UnidirectionalOneToManyParent;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;

@EntityScan("com.reports.hibernate.model.entity.relation.oneToMany.unidirectional;") // scan only required entities
@DisplayName("Unidirectional OneToMany relationhip")
public class UnidirectionalOneToManyTest extends BaseTest {

    @Test
    @DisplayName("One sided saving of parents")
    void oneSidedSaveOfParent() {
        UnidirectionalOneToManyChild child = new UnidirectionalOneToManyChild();
        child.setChildName("Child");
        UnidirectionalOneToManyParent parent = new UnidirectionalOneToManyParent();
        parent.setParentName("Parent");
        parent.setChildren(List.of(child));
        session.persist(parent);
        flushAndClear();
        assertAll(
                () -> AssertQueryCount.assertInsertCount(2),
                () -> AssertQueryCount.assertUpdateCount(1)
        );
    }

}

package com.reports.hibernate.entity.relations.manyToOne;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.relations.manyToOne.unidirectional.UnidirectionalManyToOneChild;
import com.reports.hibernate.model.entity.relations.manyToOne.unidirectional.UnidirectionalManyToOneParent;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.reports.hibernate.model.entity.relations.manyToOne.unidirectional;") // scan only required entities
@DisplayName("Unidirectional ManyToOne relationship")
public class UnidirectionalManyToOneTest extends BaseTest {

    @Test
    @DisplayName("One sided saving of parents")
    void oneSidedSaveOfParent() {
        UnidirectionalManyToOneChild child = new UnidirectionalManyToOneChild();
        child.setChildName("Child");
        UnidirectionalManyToOneParent parent = new UnidirectionalManyToOneParent();
        parent.setParentName("Parent");
        parent.setChild(child);
        session.persist(parent);
        flushAndClear();
        assertAll(
                () -> AssertQueryCount.assertInsertCount(2)
        );
    }

}

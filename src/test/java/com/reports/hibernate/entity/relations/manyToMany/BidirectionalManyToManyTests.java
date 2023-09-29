package com.reports.hibernate.entity.relations.manyToMany;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.relations.manyToMany.bidirectional.BidirectionalManyToManyChild;
import com.reports.hibernate.model.entity.relations.manyToMany.bidirectional.BidirectionalManyToManyParent;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EntityScan("com.reports.hibernate.model.entity.relations.manyToMany.bidirectional") // scan only required entities
@DisplayName("ManyToMany relationship")
class BidirectionalManyToManyTests extends BaseTest {

    @Test
    @DisplayName("One sided saving of parents")
    void oneSidedSaveOfParent() {
        int parentsAmount = 3;
        int childrenAmount = 3;
        Set<BidirectionalManyToManyChild> children = generateChildren(parentsAmount);
        Set<BidirectionalManyToManyParent> parents = generateParents(childrenAmount);

        for (BidirectionalManyToManyParent parent : parents) {
            parent.setChildren(children);
            session.persist(parent);
            session.flush();
        }

        assertAll(
                () -> AssertQueryCount.assertInsertCount(
                        childrenAmount + parentsAmount + childrenAmount * childrenAmount)
        );
    }

    @Test
    @DisplayName("One sided saving of children")
    void oneSidedSaveOfChild() {
        int parentsAmount = 3;
        int childrenAmount = 3;
        Set<BidirectionalManyToManyChild> children = generateChildren(parentsAmount);
        Set<BidirectionalManyToManyParent> parents = generateParents(childrenAmount);

        for (BidirectionalManyToManyChild child : children) {
            child.setParents(parents);
            session.persist(child);
            session.flush();
        }

        assertAll(
                () -> AssertQueryCount.assertInsertCount(childrenAmount + parentsAmount)
                // no associations will be saved
        );
    }

    @Test
    @DisplayName("One sided saving of children. Using of consistent setters")
    void oneSidedConsistentSaveOfChild() {
        int parentsAmount = 3;
        int childrenAmount = 3;
        Set<BidirectionalManyToManyChild> children = generateChildren(parentsAmount);
        Set<BidirectionalManyToManyParent> parents = generateParents(childrenAmount);

        for (BidirectionalManyToManyChild child : children) {
            child.addParents(parents);
            session.persist(child);
            session.flush();
        }

        assertAll(
                () -> AssertQueryCount.assertInsertCount(
                        childrenAmount + parentsAmount + childrenAmount * childrenAmount)
        );
    }


    private Set<BidirectionalManyToManyChild> generateChildren(int amount) {
        Set<BidirectionalManyToManyChild> result = new HashSet<>();
        for (int i = 0; i < amount; i++) {
            BidirectionalManyToManyChild child = new BidirectionalManyToManyChild();
            // id autogenerated
            child.setChildName("Child № " + i);
            result.add(child);
        }
        return result;
    }

    private Set<BidirectionalManyToManyParent> generateParents(int amount) {
        Set<BidirectionalManyToManyParent> result = new HashSet<>();
        for (int i = 0; i < amount; i++) {
            BidirectionalManyToManyParent child = new BidirectionalManyToManyParent();
            // id autogenerated
            child.setParentName("Parent № " + i);
            result.add(child);
        }
        return result;
    }

}

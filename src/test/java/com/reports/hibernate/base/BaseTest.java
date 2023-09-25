package com.reports.hibernate.base;

import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public abstract class BaseTest extends Assertions {

    private static final String DB_UNIT_SET_UP = """
            ****************************************************************
            ******************* TEST EXECUTION STARTS HER ******************
            ****************************************************************
            """;

    @PersistenceContext
    protected EntityManager em;

    protected Session session;

    @BeforeEach
    public void beforeEach() {
        System.out.println(DB_UNIT_SET_UP);
        AssertQueryCount.resetCount();
        session = em.unwrap(Session.class);
    }

    protected void flushAndClear() {
        session.flush();
        session.clear();
    }

}

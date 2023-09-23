package com.reports.hibernate;

import com.reports.hibernate.model.TestCase;
import com.reports.hibernate.service.TestService;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HibernateApplicationTests {

    @Autowired
    private TestService service;

    @Test
    void contextLoads() {
        TestCase testCase = new TestCase();
        testCase.setCaseName("First One");
        long caseId = service.save(testCase);
        AssertQueryCount.assertInsertCount(2);
        Assertions.assertEquals(testCase, service.get(caseId));
    }

}

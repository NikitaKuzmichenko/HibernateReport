package com.reports.hibernate;

import com.reports.hibernate.model.TestCase;
import com.reports.hibernate.service.TestService;
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
        Assertions.assertEquals(testCase, service.get(caseId));
    }

}

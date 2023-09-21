package com.reports.hibernate.service;

import com.reports.hibernate.model.TestCase;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TestService {

    @Autowired
    private EntityManager entityManager;

    public long save(TestCase testCase){
        return (long) entityManager.unwrap(Session.class).save(testCase);
    }

    public TestCase get(long id){
        return entityManager.find(TestCase.class, id);
    }
}

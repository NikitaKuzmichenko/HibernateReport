package com.reports.hibernate.model.entity.creation.id.generator.custom.generator;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.util.Properties;
import java.util.Random;

public class CustomIdGenerator implements IdentifierGenerator {

    private static final Random rand = new Random();

    private String prefix = "";

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return prefix + " custom generated id " + rand.nextFloat();
    }

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        IdentifierGenerator.super.configure(type, params, serviceRegistry);
        prefix = params.getProperty("prefix");
    }

}

package com.reports.hibernate.sql.query.processor.impl;

import com.reports.hibernate.sql.query.processor.QueryProcessor;

public class CountingQueryProcessor implements QueryProcessor {

    @Override
    public void process(String query) {
        // в каждом потоке должен быт свой.
    }

}

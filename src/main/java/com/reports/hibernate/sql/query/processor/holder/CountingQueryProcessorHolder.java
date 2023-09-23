package com.reports.hibernate.sql.query.processor.holder;

import com.reports.hibernate.sql.query.processor.QueryProcessor;
import com.reports.hibernate.sql.query.processor.impl.CountingQueryProcessor;

public class CountingQueryProcessorHolder {
    private static final QueryProcessor processor = new CountingQueryProcessor();

    private CountingQueryProcessorHolder(){}

    public static QueryProcessor getQueryProcessor(){
        return processor;
    }
}

package com.reports.hibernate.sql.query.processor.holder;

import com.reports.hibernate.sql.query.processor.QueryProcessor;
import com.reports.hibernate.sql.query.processor.impl.CountingQueryProcessor;
import org.apache.logging.log4j.util.Supplier;

import java.util.HashMap;
import java.util.Map;

public class QueryProcessorsHolder {
    private final Map<String,Supplier<QueryProcessor>> processors = new HashMap<>();

    public QueryProcessorsHolder(){
        processors.put("counting", CountingQueryProcessor::new);
    }

}

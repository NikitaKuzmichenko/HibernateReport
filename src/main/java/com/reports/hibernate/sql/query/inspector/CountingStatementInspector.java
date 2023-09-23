package com.reports.hibernate.sql.query.inspector;

import com.reports.hibernate.sql.query.processor.QueryProcessor;
import com.reports.hibernate.sql.query.processor.holder.CountingQueryProcessorHolder;
import org.hibernate.resource.jdbc.spi.StatementInspector;


public class CountingStatementInspector implements StatementInspector {
    private static final QueryProcessor QUERY_PROCESSOR = CountingQueryProcessorHolder.getQueryProcessor();

    @Override
    public String inspect(String sql) {
        QUERY_PROCESSOR.process(sql);
        return sql;
    }
}

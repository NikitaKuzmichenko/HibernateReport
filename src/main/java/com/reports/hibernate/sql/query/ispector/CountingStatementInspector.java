package com.reports.hibernate.sql.query.ispector;

import com.reports.hibernate.sql.query.processor.QueryProcessor;
import com.reports.hibernate.sql.query.processor.impl.CountingQueryProcessor;
import org.hibernate.resource.jdbc.spi.StatementInspector;

public class CountingStatementInspector implements StatementInspector {
    private static final QueryProcessor QUERY_PROCESSOR = new CountingQueryProcessor();

    @Override
    public String inspect(String sql) {
        QUERY_PROCESSOR.process(sql);
        return sql;
    }

}

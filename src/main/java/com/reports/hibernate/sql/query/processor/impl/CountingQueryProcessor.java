package com.reports.hibernate.sql.query.processor.impl;

import com.reports.hibernate.sql.query.info.QueryCounterInfo;
import com.reports.hibernate.sql.query.info.holder.QueryCounterInfoHolder;
import com.reports.hibernate.sql.query.processor.QueryProcessor;

public class CountingQueryProcessor implements QueryProcessor {

    private final QueryCounterInfo counterInfo = QueryCounterInfoHolder.getInfo();

    @Override
    public void process(String query) {
        String trimmedQuery = removeRedundantSymbols(query.toLowerCase());
        if (trimmedQuery.isEmpty()) {
            return;
        }
        if (trimmedQuery.startsWith("select nextval")) {
            counterInfo.incrementNextvalCount();
            return;
        }
        char firstChar = trimmedQuery.charAt(0);
        switch (firstChar) {
            case 's' -> counterInfo.incrementSelectCount(); // select from ...
            case 'i' -> counterInfo.incrementInsertCount(); // insert into ...
            case 'u' -> counterInfo.incrementUpdateCount(); // update table ...
            case 'd' -> counterInfo.incrementDeleteCount(); // delete from ...
            case 'c' -> counterInfo.incrementCallCount();   // call procedureName(...)
            default -> throw new IllegalArgumentException("Unknown query type: " + query);
        }

    }

    private String removeRedundantSymbols(String query) {
        return query.replace("--.*\n", "") // remove separate sql comments
                .replace("/\\*.*\\*/", "")  // remove inline sql comments
                .replace("\n", "") // remove line breaks
                .trim();
    }
}

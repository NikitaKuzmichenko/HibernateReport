package com.reports.hibernate.sql.query.info.holder;

import com.reports.hibernate.sql.query.info.QueryCounterInfo;

public class QueryCounterInfoHolder {
    private static final QueryCounterInfo info = new QueryCounterInfo();

    private QueryCounterInfoHolder() {
    }

    public static QueryCounterInfo getInfo() {
        return info;
    }
}

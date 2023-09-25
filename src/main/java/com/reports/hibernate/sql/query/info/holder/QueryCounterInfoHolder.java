package com.reports.hibernate.sql.query.info.holder;

import com.reports.hibernate.sql.query.info.QueryCounterInfo;

public class QueryCounterInfoHolder {
    private static final ThreadLocal<QueryCounterInfo> info = ThreadLocal.withInitial(QueryCounterInfo::new);

    private QueryCounterInfoHolder() {
    }

    public static QueryCounterInfo getInfo() {
        return info.get();
    }
}

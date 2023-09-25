package com.reports.hibernate.sql.query.assertion;

import com.reports.hibernate.sql.query.assertion.exception.QueryCountMismatchException;
import com.reports.hibernate.sql.query.info.QueryCounterInfo;
import com.reports.hibernate.sql.query.info.holder.QueryCounterInfoHolder;

public class AssertQueryCount {

    private AssertQueryCount() {
    }

    private static final QueryCounterInfo counterInfo = QueryCounterInfoHolder.getInfo();

    public static void resetCount() {
        counterInfo.clear();
    }

    public static void assertAllCount(int expected) {
        assertCount(expected, counterInfo.getAllCount(), " ");
    }

    public static void assertSelectCount(int expected) {
        assertCount(expected, counterInfo.getSelectCount(), "SELECT");
    }

    public static void assertNextValCount(int expected) {
        assertCount(expected, counterInfo.getNextvalCount(), "NEXT VAL");
    }

    public static void assertUpdateCount(int expected) {
        assertCount(expected, counterInfo.getUpdateCount(), "UPDATE");
    }

    public static void assertInsertCount(int expected) {
        assertCount(expected, counterInfo.getInsertCount(), "INSERT");
    }

    public static void assertDeleteCount(int expected) {
        assertCount(expected, counterInfo.getDeleteCount(), "DELETE");
    }

    public static void assertCallCount(int expected) {
        assertCount(expected, counterInfo.getCallCount(), "CALL");
    }


    private static void assertCount(int expected, int actual, String queryType) {
        if (expected != actual) {
            throw new QueryCountMismatchException(expected, actual, queryType);
        }
    }
}

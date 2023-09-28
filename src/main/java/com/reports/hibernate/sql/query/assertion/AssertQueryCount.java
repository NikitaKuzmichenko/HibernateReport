package com.reports.hibernate.sql.query.assertion;

import com.reports.hibernate.sql.query.assertion.exception.QueryCountMismatchException;
import com.reports.hibernate.sql.query.info.QueryCounterInfo;
import com.reports.hibernate.sql.query.info.holder.QueryCounterInfoHolder;

/**
 * Class for doing asserts for queries, executed by hibernate.
 * Uses org.hibernate.resource.jdbc.spi.StatementInspector for query counting.
 */
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

    public static void assertAllCount(int expected, String msg) {
        assertCount(expected, counterInfo.getAllCount(), " ", msg);
    }

    public static void assertSelectCount(int expected) {
        assertCount(expected, counterInfo.getSelectCount(), "SELECT");
    }

    public static void assertSelectCount(int expected, String msg) {
        assertCount(expected, counterInfo.getSelectCount(), "SELECT", msg);
    }

    public static void assertNextValCount(int expected) {
        assertCount(expected, counterInfo.getNextvalCount(), "NEXT VAL");
    }

    public static void assertNextValCount(int expected, String msg) {
        assertCount(expected, counterInfo.getNextvalCount(), "NEXT VAL", msg);
    }

    public static void assertUpdateCount(int expected) {
        assertCount(expected, counterInfo.getUpdateCount(), "UPDATE");
    }

    public static void assertUpdateCount(int expected, String msg) {
        assertCount(expected, counterInfo.getUpdateCount(), "UPDATE", msg);
    }

    public static void assertInsertCount(int expected) {
        assertCount(expected, counterInfo.getInsertCount(), "INSERT");
    }

    public static void assertInsertCount(int expected, String msg) {
        assertCount(expected, counterInfo.getInsertCount(), "INSERT", msg);
    }

    public static void assertDeleteCount(int expected) {
        assertCount(expected, counterInfo.getDeleteCount(), "DELETE");
    }

    public static void assertDeleteCount(int expected, String msg) {
        assertCount(expected, counterInfo.getDeleteCount(), "DELETE", msg);
    }

    public static void assertCallCount(int expected) {
        assertCount(expected, counterInfo.getCallCount(), "CALL");
    }

    public static void assertCallCount(int expected, String msg) {
        assertCount(expected, counterInfo.getCallCount(), "CALL", msg);
    }

    private static void assertCount(int expected, int actual, String queryType) {
        if (expected != actual) {
            throw new QueryCountMismatchException(expected, actual, queryType);
        }
    }

    private static void assertCount(int expected, int actual, String queryType, String msg) {
        if (expected != actual) {
            throw new QueryCountMismatchException(expected, actual, queryType, msg);
        }
    }

}

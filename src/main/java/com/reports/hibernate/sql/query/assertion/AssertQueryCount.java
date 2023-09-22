package com.reports.hibernate.sql.query.assertion;

import com.reports.hibernate.sql.query.assertion.exception.QueryCountMismatchException;

public class AssertQueryCount {

    public static void reset(){

    }

    public static void assertAllCount(){

    }

    public static void assertSelectCount(){

    }

    public static void assertNextValCount(){

    }

    public static void assertUpdateCount(){

    }

    public static void assertInsertCount(){

    }

    public static void assertDeleteCount(){

    }

    private static void assertCount(int expected, int actual, String queryType){
        if(expected != actual){
            throw new QueryCountMismatchException(expected, actual, queryType);
        }
    }
}

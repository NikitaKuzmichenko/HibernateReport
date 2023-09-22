package com.reports.hibernate.sql.query.assertion.exception;

public class QueryCountMismatchException extends RuntimeException {

    public QueryCountMismatchException(int expected, int actual, String queryType){
        super("Expected " + queryType + " queries amount: " + expected + " , actual: " + actual);
    }
}

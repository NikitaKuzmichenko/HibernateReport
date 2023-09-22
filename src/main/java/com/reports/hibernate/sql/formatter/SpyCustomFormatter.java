package com.reports.hibernate.sql.formatter;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.hibernate.engine.jdbc.internal.BasicFormatterImpl;
import org.hibernate.engine.jdbc.internal.Formatter;

public class SpyCustomFormatter implements MessageFormattingStrategy {
    private static final Formatter HIBERNATE_SQL_FORMATTER = new BasicFormatterImpl();
    private static final String FORMAT = """
            Category: %s\s
            ElapsedTime: %s ms\s
            Hibernate: %s\s
            ==============================================================""";

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared,
            String sql, String url) {
        if (sql.isEmpty()) {
            return "";
        }
        return String.format(FORMAT, category, elapsed, HIBERNATE_SQL_FORMATTER.format(sql));
    }

}

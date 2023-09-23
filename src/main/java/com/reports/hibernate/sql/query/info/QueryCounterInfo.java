package com.reports.hibernate.sql.query.info;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QueryCounterInfo {

    private int selectCount;
    private int nextvalCount;
    private int insertCount;
    private int updateCount;
    private int deleteCount;
    private int callCount;

    public void clear() {
        selectCount = 0;
        nextvalCount = 0;
        insertCount = 0;
        updateCount = 0;
        deleteCount = 0;
        callCount = 0;
    }

    public int getAllCount() {
        return selectCount + nextvalCount + insertCount + updateCount + deleteCount + callCount;
    }

    public void incrementSelectCount() {
        this.selectCount++;
    }

    public void incrementNextvalCount() {
        this.nextvalCount++;
    }

    public void incrementInsertCount() {
        this.insertCount++;
    }

    public void incrementUpdateCount() {
        this.updateCount++;
    }

    public void incrementDeleteCount() {
        this.deleteCount++;
    }

    public void incrementCallCount() {
        this.callCount++;
    }
}

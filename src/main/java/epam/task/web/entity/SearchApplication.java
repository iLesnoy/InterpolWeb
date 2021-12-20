package epam.task.web.entity;

import java.sql.Date;


public class SearchApplication {

    private long searchApplicationId;
    private Date leadTime;
    private long userId;
    ApplicationStatus status;

    public enum ApplicationStatus {
        CONFIRMED,REJECTED,PROCESSED,CLOSED
    }

    public SearchApplication(long searchApplicationId, Date leadTime, long userId, ApplicationStatus status) {
        this.searchApplicationId = searchApplicationId;
        this.leadTime = leadTime;
        this.userId = userId;
        this.status = status;
    }

    public SearchApplication(Date leadTime, long userId, ApplicationStatus status) {
        this.leadTime = leadTime;
        this.userId = userId;
        this.status = status;
    }

    public long getSearchApplicationId() {
        return searchApplicationId;
    }

    public void setSearchApplicationId(long searchApplicationId) {
        this.searchApplicationId = searchApplicationId;
    }

    public Date getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(Date leadTime) {
        this.leadTime = leadTime;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
}

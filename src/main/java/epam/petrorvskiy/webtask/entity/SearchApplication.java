package epam.petrorvskiy.webtask.entity;

import java.sql.Date;


public class SearchApplication {

    private long searchApplicationId;
    private Date leadTime;
    private long userId;
    ApplicationStatus status;

    public enum ApplicationStatus {
        CONFIRMED, REJECTED, PROCESSED, CLOSED
    }

    public SearchApplication(long searchApplicationId, Date leadTime, long userId, ApplicationStatus status) {
        this.searchApplicationId = searchApplicationId;
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


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SearchApplication application = (SearchApplication) o;
        if (searchApplicationId != application.searchApplicationId) {
            return false;
        }
        if (leadTime != null ? leadTime.equals(application.leadTime) : application.leadTime == null) {
            return false;
        }
        if (userId != application.userId) {
            return false;
        }
        if (status != null ? status.equals(application.status) : application.status == null) {
            return false;
        }
        return status != application.status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (searchApplicationId - (searchApplicationId >>> 32));
        result = result * 31 + (leadTime != null ? leadTime.hashCode() : 0);
        result = prime * result + (int) (userId - (userId >>> 32));
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SearchApplication{ ");
        builder.append("searchApplicationId=").append(searchApplicationId);
        builder.append(", leadTime=").append(leadTime);
        builder.append(", userId=").append(userId);
        builder.append(", status=").append(status);
        builder.append("}");

        return builder.toString();

    }
}

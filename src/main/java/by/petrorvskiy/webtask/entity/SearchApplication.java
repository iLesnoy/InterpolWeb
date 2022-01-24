package by.petrorvskiy.webtask.entity;


import java.time.LocalDate;


public class SearchApplication {

    private long searchApplicationId;
    private LocalDate leadTime;
    private long userId;
    private ApplicationStatus status;


    public enum ApplicationStatus {
        ACTIVE, REJECTED, EXPIRED, CLOSED, PROCESS
    }

    public SearchApplication() {
    }


    public long getSearchApplicationId() {
        return searchApplicationId;
    }

    public void setSearchApplicationId(long searchApplicationId) {
        this.searchApplicationId = searchApplicationId;
    }

    public LocalDate getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(LocalDate leadTime) {
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

    public static class ApplicationBuilder {
        private final SearchApplication searchApplication;

        public ApplicationBuilder() {
            searchApplication = new SearchApplication();
        }

        public SearchApplication.ApplicationBuilder setApplicationId(long applicationId) {
            searchApplication.setSearchApplicationId(applicationId);
            return this;
        }

        public SearchApplication.ApplicationBuilder setLeadTime(LocalDate leadTime) {
            searchApplication.setLeadTime(leadTime);
            return this;
        }

        public SearchApplication.ApplicationBuilder setStatus(SearchApplication.ApplicationStatus status) {
            searchApplication.setStatus(status);
            return this;
        }

        public SearchApplication.ApplicationBuilder setUserId(long userId) {
            searchApplication.setUserId(userId);
            return this;
        }

        public SearchApplication build(){
            return searchApplication;
        }

    }
}

package epam.task.web.entity;

public class WantedCriminalsApplication{

    private long searchApplicationId;
    private long guiltyId;

    public WantedCriminalsApplication(long searchApplicationId, long guiltyId) {
        this.searchApplicationId = searchApplicationId;
        this.guiltyId = guiltyId;
    }

    public long getSearchApplicationId() {
        return searchApplicationId;
    }

    public void setSearchApplicationId(long searchApplicationId) {
        this.searchApplicationId = searchApplicationId;
    }

    public long getGuiltyId() {
        return guiltyId;
    }

    public void setGuiltyId(long guiltyId) {
        this.guiltyId = guiltyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WantedCriminalsApplication that = (WantedCriminalsApplication) o;
        return searchApplicationId == that.searchApplicationId && guiltyId == that.guiltyId;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = result * 31 + Long.hashCode(searchApplicationId);
        result = result * 31 + Long.hashCode(guiltyId);
        return result;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("WantedCriminalsApplication{");
        sb.append("searchApplicationId=").append(searchApplicationId);
        sb.append(", guiltyId=").append(guiltyId);
        return sb.toString();
    }
}

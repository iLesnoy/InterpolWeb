package by.petrorvskiy.webtask.entity;

public class WantedCriminalApplication {

    private long searchApplicationId;
    private long guiltyId;

    public WantedCriminalApplication(long searchApplicationId, long guiltyId) {
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
        WantedCriminalApplication that = (WantedCriminalApplication) o;
        return searchApplicationId == that.searchApplicationId && guiltyId == that.guiltyId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result  + (int)(searchApplicationId - (searchApplicationId >>> 32));
        result = prime * result + (int)(guiltyId - (guiltyId >>>32));
        return result;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("WantedCriminalApplication{");
        sb.append("searchApplicationId=").append(searchApplicationId);
        sb.append(", guiltyId=").append(guiltyId);
        return sb.toString();
    }
}

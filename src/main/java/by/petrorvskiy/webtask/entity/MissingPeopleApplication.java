package by.petrorvskiy.webtask.entity;

public class MissingPeopleApplication {

    private long searchApplicationId;
    private long missingPeopleId;

    public MissingPeopleApplication(long searchApplicationId, long missingPeopleId) {
        this.searchApplicationId = searchApplicationId;
        this.missingPeopleId = missingPeopleId;
    }

    public long getSearchApplicationId() {
        return searchApplicationId;
    }

    public void setSearchApplicationId(long searchApplicationId) {
        this.searchApplicationId = searchApplicationId;
    }

    public long getMissingPeopleId() {
        return missingPeopleId;
    }

    public void setMissingPeopleId(long missingPeopleId) {
        this.missingPeopleId = missingPeopleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MissingPeopleApplication that = (MissingPeopleApplication) o;
        return searchApplicationId == that.searchApplicationId && missingPeopleId == that.missingPeopleId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result  = prime * result + (int) (searchApplicationId -(searchApplicationId >>> 32));
        result = prime * result + (int) (missingPeopleId - (missingPeopleId >>>32));
        return result;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("WantedCriminalApplication{");
        sb.append("searchApplicationId=").append(searchApplicationId);
        sb.append(", missingPeopleId=").append(missingPeopleId);
        return sb.toString();
    }
}

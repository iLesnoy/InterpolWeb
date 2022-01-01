package epam.petrorvskiy.webtask.entity;

public class MissingPeopleApplication {

    private long searchApplicationId;
    private long guiltyId;

    public MissingPeopleApplication(long searchApplicationId, long guiltyId) {
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
}

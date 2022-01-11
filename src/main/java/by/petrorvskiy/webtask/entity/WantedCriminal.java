package by.petrorvskiy.webtask.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class WantedCriminal {
    private long guiltyId;
    private String firstName;
    private String lastName;
    private String crimCity;
    private String crimAddress;
    private LocalDate crimDOB;
    private BigDecimal reward;
    private CrimType crimeType;


    public WantedCriminal() {
    }


    public enum CrimType {
        Murder,Burglary,Robbery
    }


    public long getGuiltyId() {
        return guiltyId;
    }

    public void setGuiltyId(long guiltyId) {
        this.guiltyId = guiltyId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCrimCity() {
        return crimCity;
    }

    public void setCrimCity(String crimCity) {
        this.crimCity = crimCity;
    }

    public String getCrimAddress() {
        return crimAddress;
    }

    public void setCrimAddress(String crimAddress) {
        this.crimAddress = crimAddress;
    }

    public LocalDate getCrimDOB() {
        return crimDOB;
    }

    public void setCrimDOB(LocalDate crimDOB) {
        this.crimDOB = crimDOB;
    }

    public BigDecimal getReward() {
        return reward;
    }

    public void setReward(BigDecimal reward) {
        this.reward = reward;
    }

    public CrimType getCrimeType() {
        return crimeType;
    }

    public void setCrimeType(CrimType crimeType) {
        this.crimeType = crimeType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WantedCriminal criminal = (WantedCriminal) o;
        if(guiltyId != criminal.guiltyId)
            return false;
        if(firstName != criminal.firstName)
            return false;
        if(lastName != criminal.lastName)
                return false;
        if(crimCity != criminal.crimCity)
            return false;
        if(crimAddress != criminal.crimAddress)
            return false;
        if (crimDOB != null ? !crimDOB.equals(criminal.crimDOB) : criminal.crimDOB != null)
            return false;
        if(reward != null ? reward.equals(criminal.reward) : criminal.reward != null)
            return false;
        return crimeType != null ? crimeType.equals(criminal.crimeType) : criminal.crimeType == null;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = result * 31 + Long.hashCode(guiltyId);
        result = result * 31 + (firstName != null ? firstName.hashCode() : 0);
        result = result * 31 + (lastName != null ? lastName.hashCode() : 0);
        result = result * 31 + (crimCity != null ? crimCity.hashCode() : 0);
        result = result * 31 + (crimAddress != null ? crimAddress.hashCode() : 0);
        result = result * 31 + crimDOB.hashCode();
        result = result * 31 + (reward != null ? reward.hashCode() : 0);
        result = result * 31 + (crimeType != null ? crimeType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("WantedCriminal{");
        sb.append("guiltyId=").append(guiltyId);
        sb.append(", firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append(", crimCity=").append(crimCity);
        sb.append(", crimAdress=").append(crimAddress);
        sb.append(", crimDOB=").append(crimDOB);
        sb.append(", reward=").append(reward);
        sb.append(", crimeType=").append(crimeType).append("}");
        return sb.toString();
    }

    public static class WantedCriminalBuilder {

        private final WantedCriminal wantedCriminal;

        public WantedCriminalBuilder() {
            wantedCriminal = new WantedCriminal();
        }

        public WantedCriminalBuilder setGuiltyId(Long id) {
            wantedCriminal.setGuiltyId(id);
            return this;
        }

        public WantedCriminalBuilder setFirstName(String firstName) {
            wantedCriminal.setFirstName(firstName);
            return this;
        }

        public WantedCriminalBuilder setLastName(String lastName) {
            wantedCriminal.setLastName(lastName);
            return this;
        }

        public WantedCriminalBuilder setCrimCity(String crimCity) {
            wantedCriminal.setCrimCity(crimCity);
            return this;
        }

        public WantedCriminalBuilder setCrimAdress(String crimAdress) {
            wantedCriminal.setCrimAddress(crimAdress);
            return this;
        }

        public WantedCriminalBuilder setDOB(LocalDate date) {
            wantedCriminal.setCrimDOB(date);
            return this;
        }

        public WantedCriminalBuilder setReward(BigDecimal reward) {
            wantedCriminal.setReward(reward);
            return this;
        }

        public WantedCriminalBuilder setCrimType(CrimType type) {
            wantedCriminal.setCrimeType(type);
            return this;
        }

        public WantedCriminal build(){
            return wantedCriminal;
        }
    }


}
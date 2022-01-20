package by.petrorvskiy.webtask.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class WantedCriminal {
    private long guiltyId;
    private String firstName;
    private String lastName;
    private String crimeCity;
    private String crimeAddress;
    private LocalDate crimeDOB;
    private BigDecimal reward;
    private CrimType crimeType;
    private String photo;


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

    public String getCrimeCity() {
        return crimeCity;
    }

    public void setCrimeCity(String crimeCity) {
        this.crimeCity = crimeCity;
    }

    public String getCrimeAddress() {
        return crimeAddress;
    }

    public void setCrimeAddress(String crimeAddress) {
        this.crimeAddress = crimeAddress;
    }

    public LocalDate getCrimeDOB() {
        return crimeDOB;
    }

    public void setCrimeDOB(LocalDate crimeDOB) {
        this.crimeDOB = crimeDOB;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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
        if(crimeCity != criminal.crimeCity)
            return false;
        if(crimeAddress != criminal.crimeAddress)
            return false;
        if (crimeDOB != null ? !crimeDOB.equals(criminal.crimeDOB) : criminal.crimeDOB != null)
            return false;
        if(reward != null ? reward.equals(criminal.reward) : criminal.reward != null)
            return false;
        if(crimeType != null ? crimeType.equals(criminal.crimeType) : criminal.crimeType == null){
            return false;
        }
        return photo != null ? photo.equals(criminal.photo) : criminal.photo == null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = result * prime+ Long.hashCode(guiltyId);
        result = result * prime+ (firstName != null ? firstName.hashCode() : 0);
        result = result * prime + (lastName != null ? lastName.hashCode() : 0);
        result = result * prime + (crimeCity != null ? crimeCity.hashCode() : 0);
        result = result * prime + (crimeAddress != null ? crimeAddress.hashCode() : 0);
        result = result * prime + crimeDOB.hashCode();
        result = result * prime + (reward != null ? reward.hashCode() : 0);
        result = result * prime + (crimeType != null ? crimeType.hashCode() : 0);
        result = result * prime + ((photo == null) ? 0 : photo.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("wantedCriminal{");
        sb.append("guiltyId=").append(guiltyId);
        sb.append(", firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append(", crimeCity=").append(crimeCity);
        sb.append(", crimeAddress=").append(crimeAddress);
        sb.append(", crimeDOB=").append(crimeDOB);
        sb.append(", reward=").append(reward);
        sb.append(", crimeType=").append(crimeType).append("}");
        /*sb.append(", photo=").append(photo).append("}");*/
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
            wantedCriminal.setCrimeCity(crimCity);
            return this;
        }

        public WantedCriminalBuilder setCrimAdress(String crimAdress) {
            wantedCriminal.setCrimeAddress(crimAdress);
            return this;
        }

        public WantedCriminalBuilder setDOB(LocalDate date) {
            wantedCriminal.setCrimeDOB(date);
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

        public WantedCriminalBuilder setPhoto(String photo) {
            wantedCriminal.setPhoto(photo);
            return this;
        }

        public WantedCriminal build(){
            return wantedCriminal;
        }
    }


}
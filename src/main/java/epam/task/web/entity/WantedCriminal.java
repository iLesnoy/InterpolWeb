package epam.task.web.entity;

import java.math.BigDecimal;
import java.util.Date;

public class WantedCriminal {
    private long guiltyId;
    private String firstName;
    private String lastName;
    private String crimCity;
    private String crimAdress;
    private Date crimDOB;
    private BigDecimal reward;
    private CrimType crimeType;

    public enum CrimType {
        Murder,Burglary,Robbery
    }

    public WantedCriminal(String firstName, String lastName, String crimCity,
                          String crimAdress, Date crimDOB, BigDecimal reward) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.crimCity = crimCity;
        this.crimAdress = crimAdress;
        this.crimDOB = crimDOB;
        this.reward = reward;
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

    public String getCrimAdress() {
        return crimAdress;
    }

    public void setCrimAdress(String crimAdress) {
        this.crimAdress = crimAdress;
    }

    public Date getCrimDOB() {
        return crimDOB;
    }

    public void setCrimDOB(Date crimDOB) {
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
        if(crimAdress != criminal.crimAdress)
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
        result = result * 31 + (crimAdress != null ? crimAdress.hashCode() : 0);
        result = result * 31 + crimDOB.hashCode();
        result = result * 31 + (reward != null ? reward.hashCode() : 0);
        result = result * 31 + (crimeType != null ? crimeType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BankCards{");
        sb.append("guiltyId=").append(guiltyId);
        sb.append(", firstName=").append(firstName);
        sb.append(", lastName='").append(lastName);
        sb.append("', crimCity=").append(crimCity);
        sb.append(", crimAdress=").append(crimAdress);
        sb.append(", crimDOB=").append(crimDOB);
        sb.append(", reward=").append(reward);
        sb.append(", crimeType=").append(crimeType).append("}");
        return sb.toString();
    }
}
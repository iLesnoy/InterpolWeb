package epam.task.web.entity;

import java.math.BigDecimal;
import java.util.Date;

public class WantedCriminals {
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

    public WantedCriminals(String firstName, String lastName, String crimCity,
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
}
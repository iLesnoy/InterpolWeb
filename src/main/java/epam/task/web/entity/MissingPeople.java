package epam.task.web.entity;

import java.sql.Date;

public class MissingPeople {
    private long missingPeopleId;
    private String name;
    private String surname;
    private Date disappearanceDate;

    public MissingPeople(long missingPeopleId, String name, String surname, Date disappearanceDate) {
        this.missingPeopleId = missingPeopleId;
        this.name = name;
        this.surname = surname;
        this.disappearanceDate = disappearanceDate;
    }

    public MissingPeople(String name, String surname, Date disappearanceDate) {
        this.name = name;
        this.surname = surname;
        this.disappearanceDate = disappearanceDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDisappearanceDate() {
        return disappearanceDate;
    }

    public void setDisappearanceDate(Date disappearance_date) {
        this.disappearanceDate = disappearance_date;
    }

    public long getMissingPeopleId() {
        return missingPeopleId;
    }

    public void setMissingPeopleId(long missingPeopleId) {
        this.missingPeopleId = missingPeopleId;
    }

    @Override
    public String toString() {
        return "MissingPeople{" +
                "missingPeopleId=" + missingPeopleId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", disappearanceDate=" + disappearanceDate +
                '}';
    }
}

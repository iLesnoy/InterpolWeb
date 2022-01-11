package epam.petrorvskiy.webtask.entity;

import java.sql.Date;

public class MissingPeople {
    private long missingPeopleId;
    private String name;
    private String surname;
    private Date disappearanceDate;

    public MissingPeople() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MissingPeople that = (MissingPeople) o;
        if (missingPeopleId != that.missingPeopleId) {
            return false;
        }
        if (name != null ? name.equals(that.name) : that.name == null) {
            return false;
        }
        if (surname != null ? surname.equals(that.surname) : that.surname == null) {
            return false;
        }
        return disappearanceDate != null ? disappearanceDate.equals(that.disappearanceDate) : that.disappearanceDate == null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (missingPeopleId - (missingPeopleId >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((surname == null) ? 0 : surname.hashCode());
        result = result * 31 + (disappearanceDate != null ? disappearanceDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MissingPeople{");
        builder.append(", missingPeopleId ").append(missingPeopleId);
        builder.append(", name ").append(name);
        builder.append(", surname ") .append(surname);
        builder.append(", disappearanceDate ") .append(disappearanceDate);
        return builder.toString();
    }

    public static class MissingPeopleBuilder {
        private final MissingPeople missingPeople;

        public MissingPeopleBuilder() {
            missingPeople = new MissingPeople();
        }

        public MissingPeople.MissingPeopleBuilder setPeopleId(long id) {
            missingPeople.setMissingPeopleId(id);
            return this;
        }

        public MissingPeople.MissingPeopleBuilder setName(String name){
            missingPeople.setName(name);
            return this;
        }

        public MissingPeople.MissingPeopleBuilder setSurname(String surname){
            missingPeople.setSurname(surname);
            return this;
        }

        public MissingPeople.MissingPeopleBuilder setDisappearanceDate(Date disappearanceDate){
            missingPeople.setDisappearanceDate(disappearanceDate);
            return this;
        }


        public MissingPeople build(){
            return missingPeople;
        }

    }
}

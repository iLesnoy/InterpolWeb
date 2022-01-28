package by.petrorvskiy.webtask.entity;

import java.time.LocalDate;

public class MissingPeople {
    private long missingPeopleId;
    private String name;
    private String surname;
    private LocalDate disappearanceDate;
    private String photo;

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

    public LocalDate getDisappearanceDate() {
        return disappearanceDate;
    }

    public void setDisappearanceDate(LocalDate disappearance_date) {
        this.disappearanceDate = disappearance_date;
    }

    public long getMissingPeopleId() {
        return missingPeopleId;
    }

    public void setMissingPeopleId(long missingPeopleId) {
        this.missingPeopleId = missingPeopleId;
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
        MissingPeople people = (MissingPeople) o;
        if (missingPeopleId != people.missingPeopleId) {
            return false;
        }
        if (name != null ? name.equals(people.name) : people.name == null) {
            return false;
        }
        if (surname != null ? surname.equals(people.surname) : people.surname == null) {
            return false;
        }
        if(disappearanceDate != null ? disappearanceDate.equals(people.disappearanceDate) : people.disappearanceDate == null){
            return false;
        }
        return photo != null ? photo.equals(people.photo) : people.photo == null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (missingPeopleId - (missingPeopleId >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((surname == null) ? 0 : surname.hashCode());
        result = prime * result + (disappearanceDate != null ? disappearanceDate.hashCode() : 0);
        result = prime * result + ((photo == null) ? 0 : photo.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MissingPeople{");
        builder.append(", missingPeopleId=").append(missingPeopleId);
        builder.append(", name=").append(name);
        builder.append(", surname=") .append(surname);
        builder.append(", disappearanceDate=") .append(disappearanceDate).append("}");
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

        public MissingPeople.MissingPeopleBuilder setDisappearanceDate(LocalDate disappearanceDate){
            missingPeople.setDisappearanceDate(disappearanceDate);
            return this;
        }

        public MissingPeople.MissingPeopleBuilder setPhoto(String photo) {
            missingPeople.setPhoto(photo);
            return this;
        }

        public MissingPeople build(){
            return missingPeople;
        }

    }
}

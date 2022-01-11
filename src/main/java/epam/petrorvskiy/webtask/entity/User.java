package epam.petrorvskiy.webtask.entity;


public class User {

    private long userId;
    private String email;
    private String password;
    private String name;
    private String surname;
    private Role role;
    private Status status;

    public User() {
    }

    public enum Role {
        ADMIN, USER, AGENT,GUEST
    }

    public enum Status {
        ACTIVE, BLOCKED
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        if (userId != user.userId)
            return false;
        if (email == null) {
            if (user.email != null)
                return false;
        } else if (!email.equals(user.email))
            return false;
        if (password == null) {
            if (user.password != null)
                return false;
        } else if (!password.equals(user.password))
            return false;
        if (name == null) {
            if (user.name != null)
                return false;
        } else if (!name.equals(user.name))
            return false;
        if (surname == null) {
            if (user.surname != null)
                return false;
        } else if (!surname.equals(user.surname))
            return false;
        if (role != user.role)
            return false;
        return status != user.status;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (userId - (userId >>> 32));
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((surname == null) ? 0 : surname.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User{ ");
        builder.append("userId=").append(userId);
        builder.append(", email=").append(email);
        builder.append(", password='").append(password);
        builder.append(", name=").append(name);
        builder.append(", surname=").append(surname);
        builder.append(", userStatus=").append(status);
        builder.append(", userRole=").append(role);
        builder.append("}");
        return builder.toString();
    }

    public static class UserBuilder {
        private final User user;

        public UserBuilder() {
            user = new User();
        }

        public UserBuilder setUserid(long id) {
            user.setUserId(id);
            return this;
        }

        public UserBuilder setEmail(String email) {
            user.setEmail(email);
            return this;
        }

        public UserBuilder setPassword(String password) {
            user.setPassword(password);
            return this;
        }
        public UserBuilder setName(String name){
            user.setName(name);
            return this;
        }
        public UserBuilder setSurname(String surname){
            user.setSurname(surname);
            return this;
        }
        public UserBuilder setStatus(Status status){
            user.setStatus(status);
            return this;
        }
        public UserBuilder setRole(Role role){
            user.setRole(role);
            return this;
        }


        public User build(){
            return user;
        }

    }
}

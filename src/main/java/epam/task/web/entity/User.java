package epam.task.web.entity;


public class User {

    private long userId;
    private String email;
    private String password;
    private String name;
    private String surname;
    private Role role;
    private Status status;

    public enum Role {
        ADMIN,USER,AGENT
    }

    public enum Status {
        ACTIVE,BLOCKED
    }

    public User(long userId, String email, String password, String name, String surname, Role role, Status status) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.status = status;
    }

    public User(String email, String password, String name, String surname) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
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
        if (status != user.status)
            return false;
        return true;
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
        public String toString () {
            StringBuilder builder = new StringBuilder();
            builder.append("User{ ");
            builder.append("userId=").append(userId);
            builder.append(", email=").append(email);
            builder.append(", password='").append(password);
            builder.append(", name=").append(name);
            builder.append(", surname=").append(surname);
            builder.append(", userStatus=").append(status);
            builder.append(", userRoleId=").append(role);
            builder.append("}");
            return builder.toString();
        }
}

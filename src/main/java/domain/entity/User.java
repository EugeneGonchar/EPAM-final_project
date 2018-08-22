package domain.entity;

public class User implements Entity{
    private int id;
    private String login;
    private String password;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private int roleId;
    private String profileImage;

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }

        User user = (User) o;

        if (getId() != user.getId()){
            return false;
        }
        if (getRoleId() != user.getRoleId()){
            return false;
        }
        if (getLogin() != null ? !getLogin().equals(user.getLogin()) : user.getLogin() != null){
            return false;
        }
        if (getPassword() != null ? !getPassword().equals(user.getPassword()) : user.getPassword() != null){
            return false;
        }
        if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null){
            return false;
        }
        if (getPhone() != null ? !getPhone().equals(user.getPhone()) : user.getPhone() != null){
            return false;
        }
        if (getFirstName() != null ? !getFirstName().equals(user.getFirstName()) : user.getFirstName() != null){
            return false;
        }
        if (getLastName() != null ? !getLastName().equals(user.getLastName()) : user.getLastName() != null){
            return false;
        }
        return getProfileImage() != null ? getProfileImage().equals(user.getProfileImage()) : user.getProfileImage() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 37 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        result = 37 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 37 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 37 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        result = 37 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 37 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 37 * result + getRoleId();
        result = 37 * result + (getProfileImage() != null ? getProfileImage().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", roleId=" + roleId +
                ", profileImage='" + profileImage + '\'' +
                '}';
    }
}

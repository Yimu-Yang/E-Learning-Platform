package cmpe295.hungwenli.elearning.model;

import javax.persistence.*;

/**
 * Define JPA and Hibernate Entity
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_name", unique = true, nullable = false, columnDefinition = "nvarchar(100)")
    private String userName;

    @Column(name = "password", nullable = false, columnDefinition = "nvarchar(100)")
    private String password;

    public User() {

    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}

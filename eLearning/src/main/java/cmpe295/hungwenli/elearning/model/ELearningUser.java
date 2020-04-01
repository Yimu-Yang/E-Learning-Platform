package cmpe295.hungwenli.elearning.model;

import javax.persistence.*;
import java.util.List;

/**
 * Define JPA and Hibernate Entity
 */
@Entity
@Table(name = "user")
public class ELearningUser {

    @Id
    @Column(name = "user_name", unique = true, nullable = false, columnDefinition = "nvarchar(100)")
    private String userName;

    @Column(name = "password", nullable = false, columnDefinition = "nvarchar(100)")
    private String password;

    public ELearningUser() {

    }

    public ELearningUser(String userName, String password) {
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
        return String.format("Customer[userName='%s', password='%s']", this.userName, this.password);
    }

}

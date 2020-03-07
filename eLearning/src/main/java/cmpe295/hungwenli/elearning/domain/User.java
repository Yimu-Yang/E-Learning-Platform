package cmpe295.hungwenli.elearning.domain;

import javax.persistence.*;

//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;

/**
 * JPA Entity
 */

//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", unique = true, nullable = false, columnDefinition = "bigint")
//    private Integer id;

    @Id
    @Column(name = "user_name", unique = true, nullable = false, columnDefinition = "nvarchar(100)")
    private String userName;

    @Column(name = "password", nullable = false, columnDefinition = "nvarchar(100)")
    private String password;

    public User() {}

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
        return String.format("Customer[userName='%s', password='%s']", this.userName, this.password);
    }

}

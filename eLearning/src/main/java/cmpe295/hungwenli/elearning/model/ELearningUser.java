package cmpe295.hungwenli.elearning.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Define JPA and Hibernate Entity
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class ELearningUser {

    @Id
    @Column(name = "user_name", unique = true, nullable = false, columnDefinition = "nvarchar(100)")
    private String userName;

    @Column(name = "password", nullable = false, columnDefinition = "nvarchar(100)")
    private String password;

}

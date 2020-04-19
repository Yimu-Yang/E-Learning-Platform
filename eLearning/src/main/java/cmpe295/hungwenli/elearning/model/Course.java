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
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "bigint")
    private Long id;

    @Column(name = "course_name", nullable = false, columnDefinition = "nvarchar(255)")
    private String courseName;

    @Column(name = "provider", columnDefinition = "nvarchar(255)")
    private String provider;

    @Column(name = "price", columnDefinition = "nvarchar(255)")
    private String price;

    @Column(name = "rating", columnDefinition = "nvarchar(255)")
    private String rating;

    @Lob
    private String courseDescription;

    @Column(name = "image_url", columnDefinition = "nvarchar(255)")
    private String imageURL;

    @Column(name = "video_url", columnDefinition = "nvarchar(255)")
    private String videoURL;

    @Column(name = "coursetalk_url", columnDefinition = "nvarchar(255)")
    private String courseTalkURL;

    @Column(name = "course_redirect_url", columnDefinition = "nvarchar(255)")
    private String courseRedirectURL;

    @Column(name = "course_actual_url", columnDefinition = "nvarchar(255)")
    private String courseActualURL;

}

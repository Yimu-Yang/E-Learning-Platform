package cmpe295.hungwenli.elearning.model;

import javax.persistence.*;

//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;

/**
 * Define JPA and Hibernate Entity
 */

//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "bigint")
    private Integer id;

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

    public Course() {}

    public Course(Integer id, String courseName, String provider, String price, String rating, String courseDescription, String imageURL,
                  String videoURL, String courseTalkURL, String courseRedirectURL, String courseActualURL) {
        this.id = id;
        this.courseName = courseName;
        this.provider = provider;
        this.price = price;
        this.rating = rating;
        this.courseDescription = courseDescription;
        this.imageURL = imageURL;
        this.videoURL = videoURL;
        this.courseTalkURL = courseTalkURL;
        this.courseRedirectURL = courseRedirectURL;
        this.courseActualURL = courseActualURL;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getCourseTalkURL() {
        return courseTalkURL;
    }

    public void setCourseTalkURL(String courseTalkURL) {
        this.courseTalkURL = courseTalkURL;
    }

    public String getCourseRedirectURL() {
        return courseRedirectURL;
    }

    public void setCourseRedirectURL(String courseRedirectURL) {
        this.courseRedirectURL = courseRedirectURL;
    }

    public String getCourseActualURL() {
        return courseActualURL;
    }

    public void setCourseActualURL(String courseActualURL) {
        this.courseActualURL = courseActualURL;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", provider='" + provider + '\'' +
                ", price='" + price + '\'' +
                ", rating='" + rating + '\'' +
                ", courseDescription='" + courseDescription + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", videoURL='" + videoURL + '\'' +
                ", courseTalkURL='" + courseTalkURL + '\'' +
                ", courseRedirectURL='" + courseRedirectURL + '\'' +
                ", courseActualURl='" + courseActualURL + '\'' +
                '}';
    }

}

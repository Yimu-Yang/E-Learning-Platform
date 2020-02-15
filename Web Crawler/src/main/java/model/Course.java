package model;

public class Course {

    private String name; // private key
    private String provider;
    private String price;
    private String rating;
    private String description;
    private String imageURL;
    private String videoURL;

    private String courseTalkURL;
    private String courseRedirectURL;
    private String courseActualURL;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

}

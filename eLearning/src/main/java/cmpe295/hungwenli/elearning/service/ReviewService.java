package cmpe295.hungwenli.elearning.service;

import cmpe295.hungwenli.elearning.model.Course;
import cmpe295.hungwenli.elearning.model.DTO.CommentDTO;
import cmpe295.hungwenli.elearning.model.DTO.ReviewDTO;
import cmpe295.hungwenli.elearning.model.ELearningUser;
import cmpe295.hungwenli.elearning.model.Review;
import cmpe295.hungwenli.elearning.repository.CourseRepository;
import cmpe295.hungwenli.elearning.repository.ReviewRepository;
import cmpe295.hungwenli.elearning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReviewRepository reviewRepository;

    public void addReview(ReviewDTO review) {
        ELearningUser user = userRepository.findByUserName(review.getUser_name());
        Course course = courseRepository.findCourseById(review.getCourse_no());

        reviewRepository.save(Review.builder()
        .user(user)
        .course(course)
        .rating(String.valueOf(review.getRating()))
        .comment(review.getContent())
        .build());
    }

    public List<CommentDTO> getComments(Long courseId) {
        Course course = courseRepository.findCourseById(courseId);

        Iterable<Review> reviews = reviewRepository.findAll();

        List<CommentDTO> comments = new ArrayList<>();

        for (Review review: reviews) {
            if (review.getCourse().equals(course)) {
                CommentDTO commentDTO = new CommentDTO(review.getUser().getUserName(), Double.valueOf(review.getRating()), review.getComment());
                comments.add(commentDTO);
            }
        }

        return comments;
    }
}

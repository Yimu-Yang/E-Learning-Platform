package cmpe295.hungwenli.elearning.controller;

import cmpe295.hungwenli.elearning.model.DTO.CommentDTO;
import cmpe295.hungwenli.elearning.model.DTO.ReviewDTO;
import cmpe295.hungwenli.elearning.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping(path = "/add-comment", produces = "application/json")
    public HttpEntity<String> review(@RequestBody ReviewDTO review) {

        System.out.println(review);
        reviewService.addReview(review);

        return new ResponseEntity<>("Reviewed successfully!", HttpStatus.OK);
    }

    @GetMapping(path = "/comments", produces = "application/json")
    public HttpEntity<List<CommentDTO>> getAllComments(@RequestParam Map<String, String> param) {

        Long courseId = Long.valueOf(param.get("course_id"));

        List<CommentDTO> res = reviewService.getComments(courseId);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}

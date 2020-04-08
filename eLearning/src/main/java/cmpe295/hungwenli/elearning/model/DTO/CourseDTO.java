package cmpe295.hungwenli.elearning.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    private Integer id;

    private String course_name;

    private String provider;

    private String price;

    private String rating;

    private String course_description;

    private String image_url;

    private String video_url;

    private String coursetalk_url;

    private String course_redirect_url;

    private String course_actual_url;
}

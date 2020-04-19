package cmpe295.hungwenli.elearning.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {

    private Long course_no;

    private String user_name;

    private Double rating;

    private String content;
}

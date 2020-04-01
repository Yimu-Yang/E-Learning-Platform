package cmpe295.hungwenli.elearning.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ELearningUserDTO {

    private String email;

    private String password;

}

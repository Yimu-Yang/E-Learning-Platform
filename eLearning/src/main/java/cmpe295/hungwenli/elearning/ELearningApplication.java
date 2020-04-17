package cmpe295.hungwenli.elearning;

import cmpe295.hungwenli.elearning.service.TFIDFService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;

@SpringBootApplication
public class ELearningApplication {

    public static void main(String[] args) {

        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        TFIDFService tfidfService = new TFIDFService(restTemplateBuilder);

        tfidfService.initialization();

        SpringApplication.run(ELearningApplication.class, args);
    }

}

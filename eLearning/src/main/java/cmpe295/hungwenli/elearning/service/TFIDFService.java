package cmpe295.hungwenli.elearning.service;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class TFIDFService {

    private final RestTemplate restTemplate;

    public TFIDFService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String initialization() {
        System.out.println("Initialize Information Retrieval model.");
        String url = "http://127.0.0.1:5000/initialSearchingMode";
        return this.restTemplate.getForObject(url, String.class);
    }

    public List<String> search(int k, String query) {
        String url = "http://127.0.0.1:5000/tfidfSearching";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("k", k)
                .queryParam("query", query);

        System.out.println(builder.toUriString());

        ResponseEntity<List> response = this.restTemplate.getForEntity(builder.toUriString(), List.class);

        System.out.println(response.getBody());

        return response.getBody();
    }
}

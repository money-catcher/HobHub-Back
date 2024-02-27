package MoneyCatcher.HobHub.Chat;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//간단하게 플라스크에서 return하는애 가져오는 코드
@RestController
public class HobbyController {

    private final RestTemplate restTemplate;

    public HobbyController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping("/callFlaskAPI")
    public String callFlaskAPI() {
        String apiUrl = "http://localhost:5000/chat"; // Replace with your Flask server URL

        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, createRequestEntity(), String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            return "Response from Flask API: " + responseBody;
        } else {
            return "Failed to call Flask API. Status code: " + response.getStatusCodeValue();
        }
    }

    private HttpEntity<String> createRequestEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestPayload = "{\"message\": \"Hello from Spring Boot!\"}";

        return new HttpEntity<>(requestPayload, headers);
    }
}


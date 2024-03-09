package MoneyCatcher.HobHub.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

//간단하게 플라스크에서 return하는애 가져오는 코드
public class HobbyController {

    private final RestTemplate restTemplate;
    @Autowired
    private HobbyRepository hobbyRepository;

    public HobbyController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void callApi() {
        String apiUrl = "http://localhost:5000/hello"; // Flask 서버의 주소


        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
        //apiUrl로 GET요청 보내고 응답 받아옴(헤더,상태코드 및 응답본문 포함)-> 문자열로 해둠 아니면 다른걸로 바꾸기
        if (response.getStatusCode().is2xxSuccessful()) {//200맞나 체크
            String responseBody = response.getBody();//성공했음 가져오기(본문만)
            System.out.println("Response from Flask API: " + responseBody);
        } else {
            System.out.println("Failed to call Flask API. Status code: " + response.getStatusCodeValue());
        }
    }

    public static void main(String[] args) {
        HobbyController apiCaller = new HobbyController(new RestTemplateBuilder());
        apiCaller.callApi();
    }
}


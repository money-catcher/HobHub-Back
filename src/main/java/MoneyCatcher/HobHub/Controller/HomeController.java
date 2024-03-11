//package MoneyCatcher.HobHub.Controller;
//import MoneyCatcher.HobHub.Hobby.HobbyRepository;
//import MoneyCatcher.HobHub.User.UserDTO;
//import MoneyCatcher.HobHub.User.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.client.RestTemplate;
//
//@Controller
//public class HomeController {
//    private final RestTemplate restTemplate;
//    @Autowired
//    private HobbyRepository hobbyRepository;
//    @Autowired
//    private UserRepository userRepository;
//
//    public HomeController(RestTemplateBuilder restTemplateBuilder) {
//        this.restTemplate = restTemplateBuilder.build();
//    }
//
//    //유저정보 플라스크에서 전달받기
//    public void callApi() {
//        String apiUrl = "http://localhost:5000/hello"; // Flask 서버의 주소
//
//        ResponseEntity<UserDTO> response = restTemplate.getForEntity(apiUrl, UserDTO.class);
//        //apiUrl로 GET요청 보내고 응답 받아옴(헤더,상태코드 및 응답본문 포함)-> 문자열로 해둠 아니면 다른걸로 바꾸기
//        if (response.getStatusCode().is2xxSuccessful()) {//200맞나 체크
//            UserDTO responseBody = response.getBody();//성공했음 가져오기(본문만)
//            System.out.println("Response from Flask API: " + responseBody);
//        } else {
//            System.out.println("Failed to call Flask API. Status code: " + response.getStatusCodeValue());
//        }
//    }
//}

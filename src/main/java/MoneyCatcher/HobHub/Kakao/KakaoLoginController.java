package MoneyCatcher.HobHub.Kakao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class KakaoLoginController {

    private final KakaoService kakaoService;

    @PostMapping("/callback")
    public ResponseEntity<?> callback(@RequestBody Map<String, String> body) {
        String code = body.get("code");
        log.info("Received authorization code: {}", code);

        if (code == null || code.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Authorization code is missing");
        }

        KakaoTokenResponseDto tokenResponse = kakaoService.getAccessTokenFromKakao(code);
        log.info("Received access token: {}", tokenResponse.getAccessToken());

        KakaoUserInfoResponseDto userInfo = kakaoService.getUserInfo(tokenResponse.getAccessToken());
        log.info("Received user info: {}", userInfo);

        // access token이랑 refresh token
        Map<String, Object> response = new HashMap<>();
        response.put("userInfo", userInfo);
        response.put("accessToken", tokenResponse.getAccessToken());
        response.put("refreshToken", tokenResponse.getRefreshToken());

        //로그인, 회원가입 로직 필요하면 추가
        return ResponseEntity.ok(response);
    }
}

package MoneyCatcher.HobHub.Chat;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HobbyController {

    @PostMapping("/process-chat")
    public ResponseEntity<?> processChat(@RequestBody ChatRequest chatRequest) {
        // Process chat request and return response
        return ResponseEntity.ok(new ChatResponse());
    }
}


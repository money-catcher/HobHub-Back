package MoneyCatcher.HobHub.Hobby;

import MoneyCatcher.HobHub.Board.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/hobby")
@RequiredArgsConstructor
public class HobbyController {

    private final HobbyService hobbyService;

    @DeleteMapping("/delete/{hobbyId}")
    public void deleteHobby(@PathVariable Long hobbyId){
        hobbyService.deleteHobbyEntity(hobbyId);
    }

}

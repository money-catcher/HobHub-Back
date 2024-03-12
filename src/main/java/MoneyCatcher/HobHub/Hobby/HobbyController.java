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

    //모든 취미 보여주기
    @GetMapping("/")//데이터 가져오기
    @ResponseBody
    public ResponseEntity<List<HobbyDTO>> findAll(){//이 형태로 반환해야됨
        //DB에서 전체게시글 데이터 가져와서 list.html에 보여준다(DTO거쳐서)
        List<HobbyDTO> hobbyDTOList = hobbyService.findAll();//여러개 가져오는거라 리스트
        return new ResponseEntity<>(hobbyDTOList, HttpStatus.OK);
    }

    //취미 추가하기
    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<String> addHobby(@RequestBody HobbyDTO hobbyDTO)
    {
        hobbyService.save(hobbyDTO);
        return ResponseEntity.status(HttpStatus.OK).body(hobbyDTO.getHobby()+"(이)가 저장되었습니다.");
    }
}

package MoneyCatcher.HobHub.Board;

import MoneyCatcher.HobHub.Hobby.HobbyDTO;
import MoneyCatcher.HobHub.Hobby.HobbyEntity;
import MoneyCatcher.HobHub.Hobby.HobbyRepository;
import MoneyCatcher.HobHub.Hobby.HobbyService;
import MoneyCatcher.HobHub.User.UserEntity;
import MoneyCatcher.HobHub.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.xml.transform.OutputKeys;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//게시판
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    private final BoardRepository boardRepository;

    private final HobbyRepository hobbyRepository;

    private final UserRepository userRepository;
    private final HobbyService hobbyService;

    //특정 유저의 특정 취미의 게시물들 반환
    //보드리스트 중에 hobbyid부터 서치하고 그 중에서 다시 userid 서치해서 가져오기
    @GetMapping("/{hobbyId}/hobbyboard")//해당 유저가 쓴 게시물 중에 특정 취미에 해당하는 게시물 가져오기
    public ResponseEntity<List<BoardDTO>> getUserBoard(@PathVariable Long hobbyId){
        //가져온 취미에서 다시 find해서 특정 취미에 대한 게시물 get
        List<BoardDTO> boardDTOList = boardService.findAll(hobbyId);

        return new ResponseEntity<>(boardDTOList, HttpStatus.OK);
    }

    //특정 유저에 대한 특정 취미 게시물 업로드
    @PostMapping("/{userId}/hobbies/{hobbyId}/save")
    public ResponseEntity<String> save(@PathVariable Long userId, @PathVariable Long hobbyId, BoardDTO boardDTO) throws IOException//하나의 객체로 입력값 가져오기
    {
        UserEntity user = userRepository.findById(userId)//해당하는 유저 찾아서 user에 저장
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        HobbyEntity hobby = hobbyRepository.findById(hobbyId)
                .orElseThrow(() -> new RuntimeException("HobbyBoard not found with id: " + hobbyId));

        if (!user.getHobby().contains(hobby)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User does not have this hobby");
        }

        boardService.save(boardDTO, user, hobby);
        return ResponseEntity.status(HttpStatus.CREATED).body("Post added to hobby successfully");
    }



    @GetMapping("/{id}")//게시물 상세조회.
    public ResponseEntity<BoardDTO> findById(@PathVariable Long id)
    {
        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        return new ResponseEntity<>(boardDTO, HttpStatus.OK);
    }
    //update 누르면 보여지는 화면
    @GetMapping("/update/{id}")
    public BoardDTO updateForm(@PathVariable Long id)
    {
        BoardDTO boardDTO = boardService.findById(id);//해당 DTO 찾아서 리턴
        return boardDTO;
    }

    //업데이트 요청
    @PostMapping("/update")
    public ResponseEntity<BoardDTO> update(BoardDTO boardDTO)
    {
        BoardDTO board = boardService.update(boardDTO);//수정된 객체 가져와서 리턴해
        return new ResponseEntity<>(boardDTO,HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        boardService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted");
    }
    //특정 00의 게시물들 불러오기
    @GetMapping("/age/{age}")
    public ResponseEntity<List<BoardDTO>> getBoardsByAge(@PathVariable int age)
    {
        List<BoardDTO> boardEntityList = boardService.getBoardsByAge(age);
        return new ResponseEntity<>(boardEntityList, HttpStatus.OK);
    }
    @GetMapping("/home/{home}")
    public ResponseEntity<List<BoardDTO>> getBoardsByHome(@PathVariable String home)
    {
        List<BoardDTO> boardEntityList = boardService.getBoardsByHome(home);
        return new ResponseEntity<>(boardEntityList, HttpStatus.OK);
    }
    @GetMapping("/motive/{motive}")
    public ResponseEntity<List<BoardDTO>> getBoardsByMotive(@PathVariable String motive)
    {
        List<BoardDTO> boardEntityList = boardService.getBoardsByMotive(motive);
        return new ResponseEntity<>(boardEntityList, HttpStatus.OK);
    }

    @GetMapping("/gender/{gender}")
    public ResponseEntity<List<BoardDTO>> getBoardsByGender(@PathVariable String gender)
    {
        List<BoardDTO> boardEntityList = boardService.getBoardsByGender(gender);
        return new ResponseEntity<>(boardEntityList, HttpStatus.OK);
    }
    @GetMapping("/income/{income}")
    public ResponseEntity<List<BoardDTO>> getBoardsByIncome(@PathVariable String income)
    {
        List<BoardDTO> boardEntityList = boardService.getBoardsByIncome(income);
        return new ResponseEntity<>(boardEntityList, HttpStatus.OK);
    }

}



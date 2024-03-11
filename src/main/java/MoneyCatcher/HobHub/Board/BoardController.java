package MoneyCatcher.HobHub.Board;

import lombok.RequiredArgsConstructor;
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
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/")//데이터 가져오기
    @ResponseBody
    public ResponseEntity<List<BoardDTO>> findAll(){//이 형태로 반환해야됨
        //DB에서 전체게시글 데이터 가져와서 list.html에 보여준다(DTO거쳐서)
        List<BoardDTO> boardDTOList = boardService.findAll();//여러개 가져오는거라 리스트
        return new ResponseEntity<>(boardDTOList, HttpStatus.OK);
    }

    //게시물 업로드
    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<String> save(BoardDTO boardDTO) throws IOException//하나의 객체로 입력값 가져오기
    {
        boardService.save(boardDTO);
        return ResponseEntity.status(HttpStatus.OK).body("게시물 저장완료");
    }


    @GetMapping("/{id}")//게시물 상세조회
    public ResponseEntity<BoardDTO> findById(@PathVariable Long id)
    {
        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        return new ResponseEntity<>(boardDTO, HttpStatus.OK);
    }
    //게시물 정보를 뷰에 전달해서 사용자에게 보여줘야되기 때문에 조회한 게시물의 정보를 뷰(model)에 담아서사용

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

}




//    //이미지 출력
//    @GetMapping("board/generic/images/{fileId}")
//    @ResponseBody
//    public Resource downloadImage(@PathVariable("fileId") Long id, Model model) throws  IOException{
//        FileEntity file = fileRepository.findById(id).orElse(null);
//        return new UrlResource("file:" + file.getSavedPath());
//    }
//
//
//    @PostMapping("board/generic/new")
//    public String onecreate(@ModelAttribute BoardEntity one)
//    {
//        boardRepository.save(one);
//        return "redirect:/";
//    }



package MoneyCatcher.HobHub.Board;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

//DTO(Data Transfer Object) 하나의 객체에 담아서 전달
//lombok이 제공해주는 어노테이션
@Getter
@Setter
@ToString
@NoArgsConstructor//기본생성자
@AllArgsConstructor//모든 필드를 매개변수로 하는 생성자
public class BoardDTO {

    private Long id;
    private String title;
    private String content;
    private int boardHits;//조회수

    private LocalDateTime boardCreatedTime;//작성시간
    private LocalDateTime boardUpdatedTime;//수정시간

    private MultipartFile boardFile;// save.html -> controller 파일 담는 용도
    private String originalFileName;//원본 파일이름
    private String storedFileName;//서버 저장용 파일이름
    private int fileAttached; // 파일 첨부 여부(첨부 1, 미첨부 0) 이건 필요없을듯?

    //엔티티로 받아서 보드DTO형식으로 변환
    public static BoardDTO toBoardDTO(BoardEntity boardEntity)
    {
        BoardDTO boardDTO = new BoardDTO();//새로운 dto객체 생성, 엔티티에서 dto로 변환 후 dto로 리턴
        boardDTO.setId(boardEntity.getId());
        boardDTO.setTitle(boardEntity.getTitle());
        boardDTO.setContent(boardEntity.getContent());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        boardDTO.setBoardCreatedTime(boardEntity.getCreatedTime());
        boardDTO.setBoardUpdatedTime(boardEntity.getUpdatedTime());
        if(boardEntity.getFileAttached() == 0)
        {
            boardDTO.setFileAttached(0);//파일이 없으면 FileAttahced는 그대로 0으로 반납
        }
        else {
            //위에 내용이랑 비슷함 엔티티에서 가져와서 디티오로 리턴하기
            boardDTO.setFileAttached(1);//파일있으면 1로 셋팅해서 반납
            //부모 entyty 객체가 자식 객체한테 직접적으로 접근할 수 있음. 보드엔티티는 부모고 파일엔티티가 자식인데 바로 접근 가능
            boardDTO.setOriginalFileName(boardEntity.getFileEntityList().get(0).getOriginalFileName());//왜 get(0)임?
            boardDTO.setStoredFileName(boardEntity.getFileEntityList().get(0).getStoredFileName());

        }
        return boardDTO;
    }


}

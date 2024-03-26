package MoneyCatcher.HobHub.Board;

import MoneyCatcher.HobHub.File.FileEntity;
import MoneyCatcher.HobHub.Hobby.HobbyEntity;
import MoneyCatcher.HobHub.User.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "board_table")
public class BoardEntity extends baseEntity{

    @Id// pk 컬럼지정. 필수
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20, nullable = false)//null일수없다 크기는20
    private String title;

    @Column //크기 255, null 가능
    private String content;

    @Column
    private int boardHits;

    @Column
    private int fileAttached;//1 or 0 file 유무

    //mappedBy는 어떤거랑 매칭이 되냐. 파일엔티티는 보드엔티티라는 이름으로 매핑된다 이말일듯. FileEntity가 자식이고 보드엔티티가 부모. 부모가 삭제될때 자식도 함께 삭제된다
    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<FileEntity> FileEntityList = new ArrayList<>();//db에 컬럼이 정의되는건 아님 왜??
    //FileEntityList 라는 이름의 변수는 List<FileEntity> 형태를 담을 수 있고 new ArrayList<>()로 초기화됨!
    //아 기억남 new어쩌고가 초기화할때 사용됨

    @ManyToOne
    @JoinColumn(name = "hobby_id")
    private HobbyEntity hobby;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    //DTO로 받아서 보드엔티티로 리턴(파일이 없는 경우)
    public static BoardEntity toSaveEntity(BoardDTO boardDTO){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setTitle(boardDTO.getTitle());
        boardEntity.setContent(boardDTO.getContent());
        boardEntity.setBoardHits(0);
        boardEntity.setFileAttached(0); // 파일 없음.
        return boardEntity;
    }
    //DTO로 받아서 파일엔티티로 리턴(파일이 있는 경우)
    public static BoardEntity toSaveFileEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setTitle(boardDTO.getTitle());
        boardEntity.setContent(boardDTO.getContent());
        boardEntity.setBoardHits(0);
        boardEntity.setFileAttached(1);
        return boardEntity;
    }
    //DTO로 받아서 업데이트 후 보드엔티티로 리턴
    public static BoardEntity toUpdateEntity(BoardDTO boardDTO){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(boardDTO.getId());//아이디가 있어서 야는 업데이트
        boardEntity.setTitle(boardDTO.getTitle());
        boardEntity.setContent(boardDTO.getContent());
        boardEntity.setBoardHits(boardDTO.getBoardHits());
//        boardEntity.setFileAttached(0); // 파일 없음.
        return boardEntity;
    }
}

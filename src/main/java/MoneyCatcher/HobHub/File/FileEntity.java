package MoneyCatcher.HobHub.File;

import MoneyCatcher.HobHub.Board.baseEntity;
import MoneyCatcher.HobHub.Board.BoardEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="board_file_table")
@Entity
public class FileEntity extends baseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)//부모엔티티 조회시 자식엔티티 함께 조회(다같이가져와)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;//부모엔티티타입으로 적어줘야됨
    //실제 db에 들어갈때는 id값만 들어감 뭔말?

    //이곳에 저장합니다.
    public static FileEntity toBoardFileEntity(BoardEntity boardEntity, String originalFileName, String storedFileName)
    {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setOriginalFileName(originalFileName);
        fileEntity.setStoredFileName(storedFileName);
        fileEntity.setBoardEntity(boardEntity);//부모 엔티티를 넘겨주야된다 위에다가!!
        return fileEntity;
    }

}

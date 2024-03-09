package MoneyCatcher.HobHub.Board;

import MoneyCatcher.HobHub.File.FileEntity;
import MoneyCatcher.HobHub.File.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final FileRepository fileRepository;

    public void save(BoardDTO boardDTO) throws IOException {
        //파일첨부 여부에 따라 로직 분리
        if(boardDTO.getBoardFile().isEmpty())
        {
            //dto에 파일이 없을때
            //DTO->Entity. DTO형식으로 받아와서 Entity로 변환, 레포지토리에 저장
            BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
            boardRepository.save(boardEntity);
        } else{
            //첨부파일 있음.
            //1. DTO에 담긴 파일을 꺼냄
            //2, 파일의 이름을 가져옴
            //3. 서버 저장용 이름을 만듦 ex. 내사진.jpg => 87597879_내사진.jpg로 만들겠다(파일끼리 안겹치게)
            //4. 저장 경로 설정
            //5. 해당 경로에 파일 저장
            //6. board_table 에 해당 데이터 save 처리, board_file_table에 해당 데이터 save 처리
            MultipartFile boardFile = boardDTO.getBoardFile(); //디티오에 있는 파일꺼내서 boardFile에 저장
            String originalFilename = boardFile.getOriginalFilename();
            String storedFileName = System.currentTimeMillis() + "_" + originalFilename;
            String savePath = "c:/springboot_img/" + storedFileName;//고대로 위치에 파일 저장되게끔 이거 서버경로로 해야됨
            boardFile.transferTo(new File(savePath));//5. boardFile에 있는 파일 해당 경로에 저장(서버에 저장)
            BoardEntity boardEntity = BoardEntity.toSaveFileEntity(boardDTO);//파일 제외 title, content 등 보드엔티티에 값 save(보드엔티티에 0,1 값 설정하는거 있음)
            Long saveId = boardRepository.save(boardEntity).getId();//엔티티저장 후 방금 저장한 엔티티아이디가져옴 왜? 부모의 board_id값이 필요함(보드엔티티의 아이디값 겟)
            BoardEntity board = boardRepository.findById(saveId).get();//다시 디비에서 부모 얻어옴. findById는 Optional로 반환하는데 이걸 get으로 다시 가져오면서 보드엔티티값을 가져옴
//            BoardEntity board = boardEntity;

            FileEntity fileEntity = FileEntity.toBoardFileEntity(board,originalFilename,storedFileName);//파일엔티티로 변환하기위한 작업. 아까 title,content 이런거 들어있는 보드엔티티 포함 같이저장하기
            fileRepository.save(fileEntity);//db에 저장.. 어렵다 근데지금 파일을 디비에 저장하는데서 오류가 난거 아냐?
            //아니여기 파일저장하는거 없는데 대채 어디서 파일을 저장하는거야
            //일반적인 이미지 게시판 저장방법은 mysql에 이미지를 저장 하지는 않구요
            //이미지를 서버에 업로딩 한 후에 해당 경로를 DB에 저장 합니다.
        }

    }
    @Transactional
    public List<BoardDTO> findAll() {//찾을때 entity로 받아서 dto로 변환 후 리턴
        List<BoardEntity> boardEntityList = boardRepository.findAll();//레포지토리에서 모두 찾아서 엔티티를 리스트형태로 저장
        List<BoardDTO> boardDTOList = new ArrayList<>();//boardDTOList는 보드DTO담을수있는 빈 리스트
        for(BoardEntity boardEntity: boardEntityList)//엔티티리스트 반복문 돌려서 하나의 엔티티객체에 꺼내놔
        {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));//엔티티->디티오 변환한고 리스트에 저장
        }
        return boardDTOList;
    }

    //조회수증가
    @Transactional//수동적인쿼리 진행할때, 데이터일관성
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    //id로 찾아서 엔티티에서 ㅈ어보 가져와가지고 디티오형식으로 반환함
    @Transactional
    public BoardDTO findById(Long id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);//toBoardDTO 안에서 보드엔티티가 파일엔티티에 접근하고 있기 때문에 이런경우에는 무조건 Transaction붙여야됨!!!
            return boardDTO;
        } else {
            return null;
        }
    }

    //save 가지고 업데이트,인서트 가능
    //아이디가 있냐없냐가 두개 구분하는거
    public BoardDTO update(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO);
        boardRepository.save(boardEntity);
        return findById(boardDTO.getId());//위의 함수 호출

    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}

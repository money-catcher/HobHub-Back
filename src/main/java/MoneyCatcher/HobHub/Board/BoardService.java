package MoneyCatcher.HobHub.Board;

import MoneyCatcher.HobHub.File.FileEntity;
import MoneyCatcher.HobHub.File.FileRepository;
import MoneyCatcher.HobHub.File.FileService;
import MoneyCatcher.HobHub.Hobby.HobbyDTO;
import MoneyCatcher.HobHub.Hobby.HobbyEntity;
import MoneyCatcher.HobHub.User.UserEntity;
import MoneyCatcher.HobHub.User.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final FileRepository fileRepository;

    @Autowired
    private FileService fileService;

    @Autowired
    private UserRepository userRepository;

    public void save(BoardDTO boardDTO, UserEntity user, HobbyEntity hobby) throws IOException {
        //파일첨부 여부에 따라 로직 분리
        if(boardDTO.getBoardFile().isEmpty())
        {
            //dto에 파일이 없을때
            //DTO->Entity. DTO형식으로 받아와서 Entity로 변환, 레포지토리에 저장
            BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
            boardEntity.setUser(user);
            boardEntity.setHobby(hobby);
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

            String savePath = "/home/jin/images/" + storedFileName;//고대로 위치에 파일 저장되게끔 이거 서버경로로 해야됨
            boardFile.transferTo(new File(savePath));//5. boardFile에 있는 파일 해당 경로에 저장(서버에 저장)
            BoardEntity boardEntity = BoardEntity.toSaveFileEntity(boardDTO);//파일 제외 title, content 등 보드엔티티에 값 save(보드엔티티에 0,1 값 설정하는거 있음)
            boardEntity.setUser(user);//보드엔티티에 user 셋팅
            boardEntity.setHobby(hobby);//hobby 셋팅
            Long saveId = boardRepository.save(boardEntity).getId();//엔티티저장 후 방금 저장한 엔티티아이디가져옴 왜? 부모의 board_id값이 필요함(보드엔티티의 아이디값 겟)
            BoardEntity board = boardRepository.findById(saveId).get();//다시 디비에서 부모 얻어옴. findById는 Optional로 반환하는데 이걸 get으로 다시 가져오면서 보드엔티티값을 가져옴
//            BoardEntity board = boardEntity;
            savePath = "http://ec2-13-125-104-87.ap-northeast-2.compute.amazonaws.com/images/" + storedFileName;
            FileEntity fileEntity = FileEntity.toBoardFileEntity(board,originalFilename,savePath);//파일엔티티로 변환하기위한 작업. 아까 title,content 이런거 들어있는 보드엔티티 포함 같이저장하기
            fileRepository.save(fileEntity);
        }

    }
    //특정유저의 특정 취미에 대한 findall
    @Transactional
    public List<BoardDTO> findAll(Long hobbyId) {//찾을때 entity로 받아서 dto로 변환 후 리턴
        List<BoardEntity> boardEntityList = boardRepository.findAllByHobbyId(hobbyId);

        return boardEntityList.stream()
                .map(BoardDTO::toBoardDTO)
                .collect(Collectors.toList());//디티오로 변환 후 리턴
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

    @Transactional
    public List<BoardDTO> getBoardsByAge(int age)
    {
        List<UserEntity> users = new ArrayList<>();

        if(age>=20 && age<30){
            users = userRepository.findByAgeBetween(20,29);//user 여러명 가져옴
        }
        if(age>=30 && age<40) {
            users = userRepository.findByAgeBetween(30, 39);
        }
        if(age < 20)
        {
            users = userRepository.findByAgeBetween(10,19);
        }
//        List<UserEntity> users = userRepository.findByAge(20,29);//user 여러명 가져옴
        List<Long> userIds = new ArrayList<>();

        for (UserEntity user : users) {
            userIds.add(user.getId());
        }

        List<BoardEntity> boardList = new ArrayList<>();//새로운 보드리스트 생성

        for(long id : userIds)
        {
            List<BoardEntity> userboard = boardRepository.findAllByUserId(id);
            for(BoardEntity boards : userboard){
                boardList.add(boards);
            }
        }

        return boardList.stream()
                .map(BoardDTO::toBoardDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<BoardDTO> getBoardsByHome(String home) {
        List<UserEntity> users = userRepository.findByHome(home);//user 여러명 가져옴
        List<Long> userIds = new ArrayList<>();
        for (UserEntity user : users) {
            userIds.add(user.getId());
        }
        List<BoardEntity> boardList = new ArrayList<>();//새로운 보드리스트 생성
        for(long id : userIds)
        {
            List<BoardEntity> userboard = boardRepository.findAllByUserId(id);
            for(BoardEntity boards : userboard){
                boardList.add(boards);
            }
        }
        return boardList.stream()
                .map(BoardDTO::toBoardDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<BoardDTO> getBoardsByMotive(String motive) {
        List<UserEntity> users = userRepository.findByMotive(motive);//user 여러명 가져옴
        List<Long> userIds = new ArrayList<>();
        for (UserEntity user : users) {
            userIds.add(user.getId());
        }
        List<BoardEntity> boardList = new ArrayList<>();//새로운 보드리스트 생성
        for(long id : userIds)
        {
            List<BoardEntity> userboard = boardRepository.findAllByUserId(id);
            for(BoardEntity boards : userboard){
                boardList.add(boards);
            }
        }
        return boardList.stream()
                .map(BoardDTO::toBoardDTO)
                .collect(Collectors.toList());

    }

    public List<BoardDTO> getBoardsByGender(String gender) {
        List<UserEntity> users = userRepository.findByGender(gender);//user 여러명 가져옴
        List<Long> userIds = new ArrayList<>();
        for (UserEntity user : users) {
            userIds.add(user.getId());
        }
        List<BoardEntity> boardList = new ArrayList<>();//새로운 보드리스트 생성
        for(long id : userIds)
        {
            List<BoardEntity> userboard = boardRepository.findAllByUserId(id);
            for(BoardEntity boards : userboard){
                boardList.add(boards);
            }
        }
        return boardList.stream()
                .map(BoardDTO::toBoardDTO)
                .collect(Collectors.toList());
    }
    public List<BoardDTO> getBoardsByIncome(String income) {
        List<UserEntity> users = userRepository.findByIncome(income);//user 여러명 가져옴
        List<Long> userIds = new ArrayList<>();
        for (UserEntity user : users) {
            userIds.add(user.getId());
        }
        List<BoardEntity> boardList = new ArrayList<>();//새로운 보드리스트 생성
        for(long id : userIds)
        {
            List<BoardEntity> userboard = boardRepository.findAllByUserId(id);
            for(BoardEntity boards : userboard){
                boardList.add(boards);
            }
        }
        return boardList.stream()
                .map(BoardDTO::toBoardDTO)
                .collect(Collectors.toList());
    }
}

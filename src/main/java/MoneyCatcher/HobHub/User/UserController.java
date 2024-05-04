package MoneyCatcher.HobHub.User;


import MoneyCatcher.HobHub.Board.BoardDTO;
import MoneyCatcher.HobHub.Board.BoardRepository;
import MoneyCatcher.HobHub.Board.BoardService;
import MoneyCatcher.HobHub.Hobby.HobbyDTO;
import MoneyCatcher.HobHub.Hobby.HobbyEntity;
import MoneyCatcher.HobHub.Hobby.HobbyRepository;
import MoneyCatcher.HobHub.Hobby.HobbyService;
import MoneyCatcher.HobHub.UserH.UserHEntity;
import MoneyCatcher.HobHub.UserH.UserHRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static MoneyCatcher.HobHub.User.UserEntity.ToUserEntity;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final HobbyService hobbyService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HobbyRepository hobbyRepository;

    @Autowired
    private UserHRepository userHRepository;

    @GetMapping("/in")
    public ResponseEntity<Void> GetRequest(){
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //유저정보 저장
    @PostMapping("/save")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO)
    {
        userService.save(userDTO);//유저 저장
        List<UserEntity> entities = userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));//가장 최근에 저장한 유저 불러와서
        UserDTO userDTO1 = UserDTO.toUserDTO(entities.get(0));//userDTO1에 다시 저장. userDTO는 primary key인 id값이 없어서 쓸 수 없음!
        //엔티티 리스트를 가져오는거야? 쓰읍,, 아 userDTO1이 제일 최근에 저장한 디티오 말하는거 아님? 리스트가 아니라 디티오 하나
        UserEntity user = userRepository.findById(userDTO1.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        UserHEntity userHEntity = new UserHEntity(); //연관테이블 new 생성
        user.setUserh(userHEntity);
        userHEntity.setUser(user);
        userHRepository.save(userHEntity);
        return new ResponseEntity<>(userDTO1, HttpStatus.OK);
    }

    //취미를 해당하는 유저에 저장하기
    @PostMapping("/{userId}/hobby")
    public ResponseEntity<HobbyDTO> addHobbyToUser(@PathVariable Long userId, @RequestBody HobbyDTO hobbyDTO)
    {
        UserEntity user = userRepository.findById(userId)//해당하는 유저 찾아서 user에 저장
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        hobbyService.save(hobbyDTO, user);
//        UserHEntity userHEntity = new UserHEntity();
//        user.setUserh(userHEntity);
//        userHEntity.setUser(user);
//        userHRepository.save(userHEntity);
        List<HobbyEntity> entities = hobbyRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        HobbyDTO hobbyDTO1 = HobbyDTO.toHobbyDTO(entities.get(0));
        return new ResponseEntity<>(hobbyDTO1, HttpStatus.OK);
    }

    //특정 유저의 취미들 반환
    @GetMapping("/{userId}/hobbylist")
    public ResponseEntity<List<HobbyDTO>> getUserHobby(@PathVariable Long userId)
    {
        //hobbyEntity에서 user_id가 userId랑 같은애들 싹다 가져와서(엔티티로) 디티오로 변환해서 다시 갖다주기
        List<HobbyDTO> hobbyDTOList = hobbyService.findAll(userId);
        return new ResponseEntity<>(hobbyDTOList, HttpStatus.OK);
    }

}

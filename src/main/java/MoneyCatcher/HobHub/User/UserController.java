package MoneyCatcher.HobHub.User;


import MoneyCatcher.HobHub.Board.BoardDTO;
import MoneyCatcher.HobHub.Board.BoardRepository;
import MoneyCatcher.HobHub.Board.BoardService;
import MoneyCatcher.HobHub.Hobby.HobbyDTO;
import MoneyCatcher.HobHub.Hobby.HobbyEntity;
import MoneyCatcher.HobHub.Hobby.HobbyRepository;
import MoneyCatcher.HobHub.Hobby.HobbyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    //falsk에서 유저정보 받아와
    @PostMapping("/save")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO)
    {
        userService.save(userDTO);
        List<UserEntity> entities = userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        UserDTO userDTO1 = UserDTO.toUserDTO(entities.get(0));
        return new ResponseEntity<>(userDTO1, HttpStatus.OK);
    }

    //허비엔티티 받아와서(프론트에서!!) 해당하는 유저에 저장하기
    @PostMapping("/{userId}/hobby")
    public ResponseEntity<HobbyDTO> addHobbyToUser(@PathVariable Long userId, @RequestBody HobbyDTO hobbyDTO)
    {
        UserEntity user = userRepository.findById(userId)//해당하는 유저 찾아서 user에 저장
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        hobbyService.save(hobbyDTO, user);
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

package MoneyCatcher.HobHub.User;


import MoneyCatcher.HobHub.Hobby.HobbyDTO;
import MoneyCatcher.HobHub.Hobby.HobbyEntity;
import MoneyCatcher.HobHub.Hobby.HobbyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HobbyRepository hobbyRepository;

    //유저정보 받아와
    @PostMapping("/SaveUser")
    public ResponseEntity<String> saveUser(@RequestBody UserDTO userDTO)
    {
        userService.save(userDTO);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO.getName() + "의 정보가 저장되었습니다.");
    }

    //허비디티오로 받아와서 해당하는 유저에 저장하기
    @PostMapping("/{userId}/hobby")
    public ResponseEntity<String> addHobbyToUser(@PathVariable Long userId, @RequestBody HobbyEntity hobbyEntity)
    {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        hobbyEntity.setUser(user);
        hobbyRepository.save(hobbyEntity);
        return ResponseEntity.status(HttpStatus.OK).body(hobbyEntity.getHobby()+"을(를) 유저에 저장완료");
    }
}

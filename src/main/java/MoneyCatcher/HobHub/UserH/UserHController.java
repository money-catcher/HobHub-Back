package MoneyCatcher.HobHub.UserH;

import MoneyCatcher.HobHub.Hobby.HobbyEntity;
import MoneyCatcher.HobHub.Hobby.HobbyRepository;
import MoneyCatcher.HobHub.Hobby.HobbyService;
import MoneyCatcher.HobHub.User.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;

@RestController
@RequiredArgsConstructor
@RequestMapping("/star")
public class UserHController {

    private final HobbyService hobbyService;


    private final UserHService userHService;

    @Autowired
    private UserHRepository userHRepository;

    @Autowired
    private HobbyRepository hobbyRepository;

    @PostMapping("/{user_id}/{hobby_id}/{grade}")
    public ResponseEntity<String> grading(@PathVariable long user_id, @PathVariable long hobby_id, @PathVariable int grade){
        //find hobby, hobby이름 가져오기
        //userh 레포에서 hobby 컬럼의 grade값을 바꾼다
        HobbyEntity hobby = hobbyService.find(hobby_id);
        String hobbyname = hobby.getHobby();
        // UserHEntity 객체를 가져옴
        UserHEntity user = userHService.findHUser(user_id);

        // hobbyname에 해당하는 필드를 찾아서 값 설정
        try {
            Field field = UserHEntity.class.getDeclaredField(hobbyname);
            field.setAccessible(true); // private 필드에 접근하기 위해 설정
            field.set(user, grade); // 해당 필드의 값을 grade로 설정
        } catch (NoSuchFieldException | IllegalAccessException e) {
            // 필드가 존재하지 않거나 접근할 수 없는 경우에 대한 예외 처리
            e.printStackTrace(); // 혹은 로깅 등의 작업 수행
            return ResponseEntity.badRequest().body("Error occurred while updating grade");
        }

        // 변경된 값을 저장 (예시에서는 UserService에 사용자를 저장하는 메서드를 호출하도록 가정)
        userHService.saveUser(user);

        // 응답 반환
        return ResponseEntity.ok("Grade updated successfully");

    }
}

package MoneyCatcher.HobHub.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void save(UserDTO userDTO) {
        UserEntity userEntity = UserEntity.ToUserEntity(userDTO);
        userRepository.save(userEntity);

    }
}

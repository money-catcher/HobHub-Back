package MoneyCatcher.HobHub.UserH;

import MoneyCatcher.HobHub.User.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserHService {
    private final UserHRepository userHRepository;
    public UserHEntity findHUser(long id) {
        UserHEntity user = userHRepository.findByUserId(id);
        return user;
    }

    public void saveUser(UserHEntity user) {
        userHRepository.save(user);
    }
}

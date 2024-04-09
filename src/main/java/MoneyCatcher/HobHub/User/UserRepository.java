package MoneyCatcher.HobHub.User;

import MoneyCatcher.HobHub.Hobby.HobbyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByAge(int age);
    List<UserEntity> findByHome(String home);
    HobbyEntity findByHobby(String hobby);
}

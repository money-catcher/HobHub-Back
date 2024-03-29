package MoneyCatcher.HobHub.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByAge(int age);

    List<UserEntity> findByHome(String home);
}

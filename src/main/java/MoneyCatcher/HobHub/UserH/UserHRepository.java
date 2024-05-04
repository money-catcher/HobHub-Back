package MoneyCatcher.HobHub.UserH;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserHRepository extends JpaRepository<UserHEntity, Long> {
    UserHEntity findByUserId(Long id);
}

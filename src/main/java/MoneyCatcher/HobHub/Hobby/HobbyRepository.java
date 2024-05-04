package MoneyCatcher.HobHub.Hobby;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HobbyRepository extends JpaRepository<HobbyEntity, Long> {
    List<HobbyEntity> findAllByUserId(Long id);
//    HobbyEntity findById(Long id);
}

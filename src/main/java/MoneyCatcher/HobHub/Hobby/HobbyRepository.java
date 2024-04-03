package MoneyCatcher.HobHub.Hobby;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HobbyRepository extends JpaRepository<HobbyEntity, Long> {
    List<HobbyEntity> findAllByUserId(Long id);
}

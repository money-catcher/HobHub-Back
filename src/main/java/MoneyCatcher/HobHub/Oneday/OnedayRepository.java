package MoneyCatcher.HobHub.Oneday;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OnedayRepository extends JpaRepository<OnedayEntity, Long> {
    List<OnedayEntity> findAllByHobby(String hobbyName);
}

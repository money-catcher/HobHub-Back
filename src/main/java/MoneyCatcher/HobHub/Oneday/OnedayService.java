package MoneyCatcher.HobHub.Oneday;

import MoneyCatcher.HobHub.Board.BoardDTO;
import MoneyCatcher.HobHub.Board.BoardEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OnedayService {

    private final OnedayRepository onedayRepository;

    @Transactional
    public List<OnedayEntity> findAll(String hobbyName) {
        List<OnedayEntity> onedayEntityList = onedayRepository.findAllByHobby(hobbyName);

        return new ArrayList<>(onedayEntityList);
    }

    public void save(List<OnedayEntity> onedayEntity) {
        onedayRepository.saveAll(onedayEntity);
    }
}

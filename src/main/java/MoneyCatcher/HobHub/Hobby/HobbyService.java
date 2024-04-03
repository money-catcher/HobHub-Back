package MoneyCatcher.HobHub.Hobby;

import MoneyCatcher.HobHub.User.UserEntity;
import MoneyCatcher.HobHub.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HobbyService {

    private final HobbyRepository hobbyRepository;
    private final UserRepository userRepository;

    //hobby 모두 찾기 인데!! userid로 찾아야됨
    @Transactional
    public List<HobbyDTO> findAll(Long id) {//찾을때 entity로 받아서 dto로 변환 후 리턴
        List<HobbyEntity> hobbyEntityList = hobbyRepository.findAllByUserId(id);

        return hobbyEntityList.stream()
                .map(HobbyDTO::toHobbyDTO)
                .collect(Collectors.toList());
    }

    public void save(HobbyDTO hobbyDTO){

        HobbyEntity hobbyEntity = HobbyEntity.ToEntity(hobbyDTO);
        hobbyRepository.save(hobbyEntity);
    }
}


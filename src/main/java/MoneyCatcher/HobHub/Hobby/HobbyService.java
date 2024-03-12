package MoneyCatcher.HobHub.Hobby;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HobbyService {

    private final HobbyRepository hobbyRepository;

    @Transactional
    public List<HobbyDTO> findAll() {//찾을때 entity로 받아서 dto로 변환 후 리턴
        List<HobbyEntity> hobbyEntityList = hobbyRepository.findAll();//레포지토리에서 모두 찾아서 엔티티를 리스트형태로 저장
        List<HobbyDTO> hobbyDTOList = new ArrayList<>();//boardDTOList는 보드DTO담을수있는 빈 리스트
        for(HobbyEntity hobbyEntity: hobbyEntityList)//엔티티리스트 반복문 돌려서 하나의 엔티티객체에 꺼내놔
        {
            hobbyDTOList.add(HobbyDTO.toHobbyDTO(hobbyEntity));//엔티티->디티오 변환한고 리스트에 저장
        }
        return hobbyDTOList;
    }


    public void save(HobbyDTO hobbyDTO){

        HobbyEntity hobbyEntity = HobbyEntity.ToEntity(hobbyDTO);
        hobbyRepository.save(hobbyEntity);
    }
}


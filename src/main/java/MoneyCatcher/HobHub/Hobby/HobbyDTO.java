package MoneyCatcher.HobHub.Hobby;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HobbyDTO {

    private Long id;
    private String hobby;
    private String category;
    private int percent;

    public static HobbyDTO toHobbyDTO(HobbyEntity hobbyEntity) {

        HobbyDTO hobbyDTO = new HobbyDTO();
        hobbyDTO.setId(hobbyEntity.getId());
        hobbyDTO.setHobby(hobbyEntity.getHobby());
        hobbyDTO.setCategory(hobbyEntity.getCategory());
        hobbyDTO.setPercent(hobbyEntity.getPercent());

        return hobbyDTO;
    }
}

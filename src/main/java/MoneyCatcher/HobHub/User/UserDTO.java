package MoneyCatcher.HobHub.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String gender;
    private int age;
    private String home;
    private String income;
    private String motive;
    private int work;
    private int wkend;

    //엔티티로 받아서 디티오로 변환(디비에서 밖으로 끄집어낼때)
    public static UserDTO toUserDTO(UserEntity userEntity)
    {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(userEntity.getName());
        userDTO.setId(userEntity.getId());
        userDTO.setAge(userEntity.getAge());
        userDTO.setGender(userEntity.getGender());
        userDTO.setHome(userEntity.getHome());
        userDTO.setIncome(userEntity.getIncome());
        userDTO.setMotive(userEntity.getMotive());
        userDTO.setWork(userEntity.getWork());
        userDTO.setWkend(userEntity.getWkend());

        return userDTO;
    }
}

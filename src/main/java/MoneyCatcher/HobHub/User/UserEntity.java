package MoneyCatcher.HobHub.User;

import MoneyCatcher.HobHub.Board.BoardDTO;
import MoneyCatcher.HobHub.Board.BoardEntity;
import MoneyCatcher.HobHub.Hobby.HobbyEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String gender;
    private int age;
    private String home;
    private String income;
    private String motive;
    private int work;
    private int wkend;

    @OneToMany(mappedBy = "user")
    private List<HobbyEntity> hobby;

    @OneToMany(mappedBy = "user")
    private List<BoardEntity> boardEntityList = new ArrayList<>();
    public List<BoardEntity> getBoardEntityList(){
        return boardEntityList;
    }
    //밖에서 받아서 디비로 저장
    public static UserEntity ToUserEntity(UserDTO userDTO)
    {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDTO.getName());
        userEntity.setAge(userDTO.getAge());
        userEntity.setHome(userDTO.getHome());
        userEntity.setGender(userDTO.getGender());
        userEntity.setIncome(userDTO.getIncome());
        userEntity.setMotive(userDTO.getMotive());
        userEntity.setWork(userDTO.getWork());
        userEntity.setWkend(userDTO.getWkend());

        return userEntity;
    }

}

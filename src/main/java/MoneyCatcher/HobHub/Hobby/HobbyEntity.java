package MoneyCatcher.HobHub.Hobby;

import MoneyCatcher.HobHub.Board.BoardEntity;
import MoneyCatcher.HobHub.User.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//얘 생성자없는데 왜 잘돌아감??
@Entity
@Table(name = "hobby_list")
@Getter
@Setter
@NoArgsConstructor
public class HobbyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String hobby;

//    @ManyToOne(fetch = FetchType.LAZY)//부모엔티티 조회시 자식엔티티 함께 조회(다같이가져와)
//    @JoinColumn(name = "user_id")
//    private UserEntity userEntity;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "hobby")
    private List<BoardEntity> boardEntityList = new ArrayList<>();

    public static HobbyEntity ToEntity(HobbyDTO hobbyDTO)
    {
        HobbyEntity hobbyEntity = new HobbyEntity();
        hobbyEntity.setHobby(hobbyDTO.getHobby());

        return hobbyEntity;
    }
}

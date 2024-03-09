package MoneyCatcher.HobHub.Chat;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class HobbyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String gender;
    private int age;
    private String home;
    private String income;
    private String motive;
    private int work;
    private int wkend;



}

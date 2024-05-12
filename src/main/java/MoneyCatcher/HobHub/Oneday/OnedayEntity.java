package MoneyCatcher.HobHub.Oneday;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "oneday")
@Setter
@Getter
@NoArgsConstructor
public class OnedayEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String hobby;

    @Column
    private String title;

    @Column
    private String price;

    @Column
    private String location;

    @Column
    private int total_time;

    @Column
    private String link;

    @Column
    private String level;

    @Column
    private String picture;
}

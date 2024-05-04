package MoneyCatcher.HobHub.UserH;

import MoneyCatcher.HobHub.User.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user2")
@Getter
@Setter
public class UserHEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private int 공예;

    @Column
    private int 가죽;

    @Column
    private int 비누;

    @Column
    private int 목공예;

    @Column
    private int 캔들;

    @Column
    private int 유리;

    @Column
    private int 뜨개;

    @Column
    private int 도예;

    @Column
    private int 꽃;

    @Column
    private int 터프팅;

    @Column
    private int 액세서리;

    @Column
    private int 여행;

    @Column
    private int 축제;

    @Column
    private int 연극;

    @Column
    private int 전통예술;

    @Column
    private int 박물관;

    @Column
    private int 미술과;

    @Column
    private int 음악;

    @Column
    private int 전통악기;

    @Column
    private int 드럼;

    @Column
    private int 기타;

    @Column
    private int 보컬;

    @Column
    private int 피아노;

    @Column
    private int 미술;

    @Column
    private int 사진;

    @Column
    private int 인테리어;

    @Column
    private int 오일파스텔;

    @Column
    private int 펜드로잉;

    @Column
    private int 뷰티;

    @Column
    private int 패션;

    @Column
    private int 향수;

    @Column
    private int 수영;

    @Column
    private int 다이빙;

    @Column
    private int 구기스포츠;

    @Column
    private int 댄스;

    @Column
    private int 라켓스포츠;

    @Column
    private int 볼링;

    @Column
    private int 클라이밍;

    @Column
    private int 실내스포츠;

    @Column
    private int 낚시;

    @Column
    private int 하이킹;

    @Column
    private int 라이딩;

    @Column
    private int 러닝;

    @Column
    private int 캠핑;

    @Column
    private int 골프;

    @Column
    private int 피크닉;

    @Column
    private int 야외스포츠;

    @Column
    private int 요리;

    @Column
    private int 주류;

    @Column
    private int 커피;

    @Column
    private int 요가;

    @Column
    private int 필라테스;

    @Column
    private int 헬스;

    @Column
    private int 자기계발;

    @Column
    private int 독서;

    @Column
    private int 어학;

    @Column
    private int 신문;

    @Column
    private int 다쿠아즈;

    @Column
    private int 마들렌;

    @Column
    private int 케이크;

    @Column
    private int 쿠키;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public UserHEntity(){
        this.공예 = 3;
        this.가죽 = 3;
        this.비누 = 3;
        this.목공예 = 3;
        this.캔들 = 3;
        this.유리 = 3;
        this.뜨개 = 3;
        this.도예 = 3;
        this.꽃 = 3;
        this.터프팅 = 3;
        this.액세서리 = 3;
        this.여행 = 3;
        this.축제 = 3;
        this.연극 = 3;
        this.전통예술 = 3;
        this.박물관 = 3;
        this.미술과 = 3;
        this.음악 = 3;
        this.전통악기 = 3;
        this.드럼 = 3;
        this.기타 = 3;
        this.보컬 = 3;
        this.피아노 = 3;
        this.미술 = 3;
        this.사진 = 3;
        this.인테리어 = 3;
        this.오일파스텔 = 3;
        this.펜드로잉 = 3;
        this.뷰티 = 3;
        this.패션 = 3;
        this.향수 = 3;
        this.수영 = 3;
        this.다이빙 = 3;
        this.구기스포츠 = 3;
        this.댄스 = 3;
        this.라켓스포츠 = 3;
        this.볼링 = 3;
        this.클라이밍 = 3;
        this.실내스포츠 = 3;
        this.낚시 = 3;
        this.하이킹 = 3;
        this.라이딩 = 3;
        this.러닝 = 3;
        this.캠핑 = 3;
        this.골프 = 3;
        this.피크닉 = 3;
        this.야외스포츠 = 3;
        this.요리 = 3;
        this.주류 = 3;
        this.커피 = 3;
        this.요가 = 3;
        this.필라테스 = 3;
        this.헬스 = 3;
        this.자기계발 = 3;
        this.독서 = 3;
        this.어학 = 3;
        this.신문 = 3;
        this.다쿠아즈 = 3;
        this.마들렌 = 3;
        this.케이크 = 3;
        this.쿠키 = 3;

    }

}

package MoneyCatcher.HobHub.Board;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

//시간정보 다루는 엔티티
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class baseEntity {
    //생성시 시간
    @CreationTimestamp
    @Column(updatable = false)//수정시 얘는 반영안함
    private LocalDateTime createdTime;

    //수정시간
    @UpdateTimestamp
    @Column(insertable = false)//입력시 관여안함
    private LocalDateTime updatedTime;
}

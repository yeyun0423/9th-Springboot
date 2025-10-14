    package com.example.umc_9th_springboot.domain.mission.entity;

    import com.example.umc_9th_springboot.domain.user.entity.User;
    import com.example.umc_9th_springboot.domain.mission.enums.MissionStatus;
    import jakarta.persistence.*;
    import lombok.*;
    import com.example.umc_9th_springboot.domain.common.BaseEntity;
    import org.springframework.data.jpa.domain.support.AuditingEntityListener;

    import java.time.LocalDateTime;

    @Entity
    @Table(name = "user_mission")
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    @EntityListeners(AuditingEntityListener.class)

    public class UserMission extends BaseEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false, length = 20)
        private MissionStatus status;

        @Column(name = "success_requested_at")
        private LocalDateTime successRequestedAt;

        @Column(name = "success_confirmed_at")
        private LocalDateTime successConfirmedAt;

        @Column(name = "is_review", nullable = false)
        private boolean isReview;

        // 유저 FK
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", nullable = false)
        private User user;

        // 미션 FK
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "mission_id", nullable = false)
        private Mission mission;
    }

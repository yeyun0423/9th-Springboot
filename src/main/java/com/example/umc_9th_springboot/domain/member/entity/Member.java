package com.example.umc_9th_springboot.domain.member.entity;

import com.example.umc_9th_springboot.domain.member.enums.Gender;
import com.example.umc_9th_springboot.domain.member.enums.MemberStatus;
import com.example.umc_9th_springboot.domain.mission.entity.UserMission;
import com.example.umc_9th_springboot.domain.term.entity.UserTerms;
import com.example.umc_9th_springboot.domain.shop.entity.UserRegion;
import com.example.umc_9th_springboot.domain.review.entity.Review;
import com.example.umc_9th_springboot.domain.review.entity.ReviewComment;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 1)
    private Gender gender;

    @Column(nullable = false)
    private LocalDate birth;

    @Column(nullable = false, length = 100)
    private String address;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(nullable = false, length = 10)
    private MemberStatus status = MemberStatus.ACTIVE;

    @Column(name = "inactive_date")
    private LocalDateTime inactiveDate;

    @Column(nullable = false)
    private int point;


    // 유저미션 (1:N)
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserMission> userMissionList = new ArrayList<>();

    // 유저지역 (1:N)
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRegion> userRegionList = new ArrayList<>();

    // 유저약관 (1:N)
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserTerms> userTermsList = new ArrayList<>();

    // 유저음식 (1:N)
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserFood> userFoodList = new ArrayList<>();

    // 리뷰 (1:N)
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviewList = new ArrayList<>();

    // 리뷰 댓글 (1:N)
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewComment> reviewCommentList = new ArrayList<>();
}

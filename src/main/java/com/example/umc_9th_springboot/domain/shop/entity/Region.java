package com.example.umc_9th_springboot.domain.shop.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "region")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 20)
    private String name;

    @Column(name = "mission_reward_count", nullable = false)
    private int missionRewardCount;

    // 지역 가게 연관 관계
    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Shop> shopList = new ArrayList<>();

    // 지역 유저지역 연관 관계
    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRegion> userRegionList = new ArrayList<>();
}

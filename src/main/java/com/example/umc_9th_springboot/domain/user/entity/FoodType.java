package com.example.umc_9th_springboot.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "food_type")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FoodType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    // 연관관계 (음식 ↔ 유저음식)
    @OneToMany(mappedBy = "foodType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserFood> userFoodList = new ArrayList<>();
}

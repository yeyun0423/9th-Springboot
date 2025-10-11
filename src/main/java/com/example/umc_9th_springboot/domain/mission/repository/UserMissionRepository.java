package com.example.umc_9th_springboot.domain.mission.repository;

import com.example.umc_9th_springboot.domain.mission.entity.UserMission;
import com.example.umc_9th_springboot.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
//루트 엔티티가 어디인지에 따라 나눔


    //사용자가 완료한 미션 개수 조회
    @Query("select count(um) from UserMission um " +
            "where um.member.id = :userId and um.status = 'COMPLETED'")
    Long countCompletedMissions(@Param("userId") Long userId);
}

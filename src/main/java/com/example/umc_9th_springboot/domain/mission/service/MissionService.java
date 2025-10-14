package com.example.umc_9th_springboot.domain.mission.service;

import com.example.umc_9th_springboot.domain.mission.entity.Mission;
import com.example.umc_9th_springboot.domain.mission.repository.MissionRepository;
import com.example.umc_9th_springboot.domain.mission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;

   //완료한 미션 카운트 -홈
    public Long getCompletedMissionCount(Long userId) {
        return userMissionRepository.countCompletedMissions(userId);
    }

    //도전 가능한 미션 목록 조회 -홈
    public Page<Mission> getAvailableMissions(Long regionId, Long userId, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return missionRepository.findAvailableMissionsByRegion(regionId, userId, pageable);
    }
}

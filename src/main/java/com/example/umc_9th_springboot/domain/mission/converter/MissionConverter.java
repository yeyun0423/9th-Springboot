package com.example.umc_9th_springboot.domain.mission.converter;

import com.example.umc_9th_springboot.domain.mission.dto.res.MissionResDTO;
import com.example.umc_9th_springboot.domain.mission.entity.Mission;
import com.example.umc_9th_springboot.domain.mission.entity.UserMission;
import com.example.umc_9th_springboot.domain.mission.enums.MissionStatus;
import com.example.umc_9th_springboot.domain.user.entity.User;
import org.springframework.data.domain.Page;

public class MissionConverter {

    // UserMission 엔티티 생성
    public static UserMission toUserMission(User user, Mission mission) {
        return UserMission.builder()
                .mission(mission)
                .user(user)
                .status(MissionStatus.PROGRESS)
                .isReview(false)
                .build();
    }

    // 응답 DTO 변환
    public static MissionResDTO.ChallengeDTO toChallengeDTO(UserMission userMission) {
        return MissionResDTO.ChallengeDTO.builder()
                .userMissionId(userMission.getId())
                .missionId(userMission.getMission().getId())
                .userId(userMission.getUser().getId())
                .status(userMission.getStatus().name())
                .isReview(userMission.isReview())
                .createdAt(userMission.getCreatedAt())
                .build();
    }

     //미션 페이지 → 미션 목록 DTO로 변환
    public static MissionResDTO.MissionPreviewListDTO toMissionPreviewListDTO(Page<Mission> result) {
        return MissionResDTO.MissionPreviewListDTO.builder()
                .missionList(
                        result.getContent().stream()
                                .map(MissionConverter::toMissionPreviewDTO)
                                .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    //하나의 미션 엔티티 → 미션 미리보기 DTO 변환
    public static MissionResDTO.MissionPreviewDTO toMissionPreviewDTO(Mission mission) {
        return MissionResDTO.MissionPreviewDTO.builder()
                .missionId(mission.getId())
                .title(mission.getTitle())
                .description(mission.getDescription())
                .build();
    }
}

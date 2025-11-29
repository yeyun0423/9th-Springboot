package com.example.umc_9th_springboot.domain.mission.controller;

import com.example.umc_9th_springboot.domain.mission.dto.res.MissionResDTO;
import com.example.umc_9th_springboot.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc_9th_springboot.domain.mission.service.MissionService;
import com.example.umc_9th_springboot.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.umc_9th_springboot.global.validation.ValidPage;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService missionService;

    // 가게의 미션을 도전 중인 미션에 추가
    @PostMapping("/{missionId}/challenge")
    public ApiResponse<MissionResDTO.ChallengeDTO> challengeMission(
            @PathVariable Long missionId
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.CHALLENGE_CREATED,
                missionService.challengeMission(missionId)
        );
    }

     // 특정 가게의 미션 목록
    @GetMapping("/{shopId}/missions")
    public ApiResponse<MissionResDTO.MissionPreviewListDTO> getShopMissions(
            @PathVariable Long shopId,
            @RequestParam(name = "page") @ValidPage Integer page
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.FOUND,
                missionService.getShopMissions(shopId, page)
        );
    }
}

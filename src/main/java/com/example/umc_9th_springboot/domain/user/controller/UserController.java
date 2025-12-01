package com.example.umc_9th_springboot.domain.user.controller;

import com.example.umc_9th_springboot.domain.user.dto.req.UserReqDTO;
import com.example.umc_9th_springboot.domain.user.dto.res.UserResDTO;
import com.example.umc_9th_springboot.domain.user.service.UserCommandService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserCommandService userCommandService;

    //회원가입 세션 API
    @PostMapping("/sign-up/session")
    public UserResDTO.SignUpDTO signUp(@RequestBody UserReqDTO.SignUpDTO request) {
        return userCommandService.signup(request);
    }

    // 로그인 세션 API
    @PostMapping("/login/session")
    public UserResDTO.LoginDTO loginSession(
            @RequestBody UserReqDTO.LoginDTO request,
            HttpServletRequest httpRequest
    ) {
        UserResDTO.LoginDTO response = userCommandService.login(request);

        // 세션 생성 및 사용자 정보 저장
        HttpSession session = httpRequest.getSession(true);
        session.setAttribute("loginUserId", response.getUserId());
        session.setAttribute("loginUserEmail", response.getEmail());

        return response;
    }
}

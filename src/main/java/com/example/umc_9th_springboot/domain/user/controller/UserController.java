package com.example.umc_9th_springboot.domain.user.controller;

import com.example.umc_9th_springboot.domain.user.dto.req.UserReqDTO;
import com.example.umc_9th_springboot.domain.user.dto.res.UserResDTO;
import com.example.umc_9th_springboot.domain.user.service.UserCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserCommandService userCommandService;

    //회원가입 API
    @PostMapping("/sign-up")
    public UserResDTO.SignUpDTO signUp(@RequestBody UserReqDTO.SignUpDTO request) {
        return userCommandService.signup(request);
    }
}

package com.example.umc_9th_springboot.domain.user.service;

import com.example.umc_9th_springboot.domain.user.dto.req.UserReqDTO;
import com.example.umc_9th_springboot.domain.user.dto.res.UserResDTO;

public interface UserCommandService {

    //회원가입
    UserResDTO.SignUpDTO signup(UserReqDTO.SignUpDTO dto);
    // 로그인
    UserResDTO.LoginDTO login(UserReqDTO.LoginDTO dto);
}

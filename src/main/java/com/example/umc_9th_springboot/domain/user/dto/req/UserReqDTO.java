package com.example.umc_9th_springboot.domain.user.dto.req;

import com.example.umc_9th_springboot.domain.user.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

public class UserReqDTO {

    //회원가입 요청 DTO
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SignUpDTO {
        private String email;
        private String password;
        private String name;
        private Gender gender;
        private String birth;     // LocalDate로 변환 필요
        private String address;
    }

    //로그인 요청 DTO
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class LoginDTO {
        private String email;
        private String password;
    }
}

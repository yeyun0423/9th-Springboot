package com.example.umc_9th_springboot.domain.user.service.impl;

import com.example.umc_9th_springboot.domain.user.converter.UserConverter;
import com.example.umc_9th_springboot.domain.user.dto.req.UserReqDTO;
import com.example.umc_9th_springboot.domain.user.dto.res.UserResDTO;
import com.example.umc_9th_springboot.domain.user.entity.User;
import com.example.umc_9th_springboot.domain.user.repository.UserRepository;
import com.example.umc_9th_springboot.domain.user.service.UserCommandService;
import com.example.umc_9th_springboot.global.auth.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    //회원가입
    @Override
    public UserResDTO.SignUpDTO signup(UserReqDTO.SignUpDTO dto) {

        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        User user = UserConverter.toUser(dto, encodedPassword, Role.USER);

        User savedUser = userRepository.save(user);

        return UserResDTO.SignUpDTO.builder()
                .userId(savedUser.getId())
                .email(savedUser.getEmail())
                .name(savedUser.getName())
                .role(savedUser.getRole())
                .build();
    }

    // 로그인
    @Override
    public UserResDTO.LoginDTO login(UserReqDTO.LoginDTO dto) {

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원입니다."));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return UserResDTO.LoginDTO.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole())
                .build();
    }
}

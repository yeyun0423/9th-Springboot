package com.example.umc_9th_springboot.domain.member.service;

import com.example.umc_9th_springboot.domain.member.entity.Member;
import com.example.umc_9th_springboot.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //마이페이지 회원 정보 조회
    public Member getMemberInfo(Long memberId) {
        return memberRepository.findMemberInfo(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }
}

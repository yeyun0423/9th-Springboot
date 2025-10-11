package com.example.umc_9th_springboot.domain.member.repository;

import com.example.umc_9th_springboot.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //마이페이지 회원 정보 조회
    @Query("select m from Member m where m.id = :id")
    Optional<Member> findMemberInfo(@Param("id") Long id);

}

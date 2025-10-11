package com.example.umc_9th_springboot.domain.review.repository;

import com.example.umc_9th_springboot.domain.review.entity.ReviewComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Long> {

}

package com.example.umc_9th_springboot.domain.review.service;

import com.example.umc_9th_springboot.domain.member.entity.Member;
import com.example.umc_9th_springboot.domain.member.repository.MemberRepository;
import com.example.umc_9th_springboot.domain.review.entity.Review;
import com.example.umc_9th_springboot.domain.review.entity.ReviewComment;
import com.example.umc_9th_springboot.domain.review.repository.ReviewCommentRepository;
import com.example.umc_9th_springboot.domain.review.repository.ReviewRepository;
import com.example.umc_9th_springboot.domain.shop.entity.Shop;
import com.example.umc_9th_springboot.domain.shop.repository.ShopRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewCommentRepository reviewCommentRepository;
    private final MemberRepository memberRepository;
    private final ShopRepository shopRepository;

   //리뷰 작성
    @Transactional
    public Review createReview(Long userId, Long shopId, BigDecimal rating, String content) {

        // 중복 리뷰 방지
        reviewRepository.findByMemberIdAndShopId(userId, shopId)
                .ifPresent(r -> {
                    throw new IllegalStateException("이미 이 가게에 리뷰를 작성하셨습니다!");
                });

        // Member, Shop 조회
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));

        // Review 엔티티 생성 및 저장
        Review review = Review.builder()
                .rating(rating)
                .content(content)
                .member(member)
                .shop(shop)
                .build();

        return reviewRepository.save(review);
    }

   //점표별 리뷰, 댓글 조회
    public List<Review> getReviewsByShop(Long shopId) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));
        return reviewRepository.findAllByShopWithComments(shop);
    }

    //리뷰에 댓글 작성
    @Transactional
    public ReviewComment createReviewComment(Long reviewId, Long userId, String content) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 리뷰입니다."));
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        ReviewComment comment = ReviewComment.builder()
                .review(review)
                .member(member)
                .content(content)
                .build();

        return reviewCommentRepository.save(comment);
    }
}

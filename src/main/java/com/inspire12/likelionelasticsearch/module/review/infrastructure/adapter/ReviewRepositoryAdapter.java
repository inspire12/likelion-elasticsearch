package com.inspire12.likelionelasticsearch.module.review.infrastructure.adapter;

import com.inspire12.likelionelasticsearch.module.review.domain.Review;
import com.inspire12.likelionelasticsearch.module.review.domain.ReviewRepository;
import com.inspire12.likelionelasticsearch.module.review.infrastructure.esrepoistory.ReviewEsRepository;
import com.inspire12.likelionelasticsearch.module.review.support.ReviewMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepositoryAdapter implements ReviewRepository {

//    private final ReviewJpaRepository reviewRepository;
    private final ReviewEsRepository reviewEsRepository;

    public ReviewRepositoryAdapter(ReviewEsRepository reviewEsRepository) {
        this.reviewEsRepository = reviewEsRepository;
    }

    @Override
    public List<Review> getReviewsByCustomerId(Long customerId, Pageable pageable) {
        return reviewEsRepository.findAllByCustomerId(customerId, Pageable.ofSize(10))
                .getContent().stream().map(ReviewMapper::fromDocument).toList();
    }

    @Override
    public void save(Review review) {
        reviewEsRepository.save(ReviewMapper.toEntity(review));
    }
}

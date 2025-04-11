package com.inspire12.likelionelasticsearch.module.review.infrastructure.adapter;

import com.inspire12.likelionelasticsearch.module.review.application.dto.request.ReviewRequest;
import com.inspire12.likelionelasticsearch.module.review.application.dto.request.ReviewSearchRequest;
import com.inspire12.likelionelasticsearch.module.review.domain.Review;
import com.inspire12.likelionelasticsearch.module.review.application.port.in.ReviewRepository;
import com.inspire12.likelionelasticsearch.module.review.infrastructure.document.ReviewDocument;
import com.inspire12.likelionelasticsearch.module.review.infrastructure.esrepoistory.ReviewEsRepository;
import com.inspire12.likelionelasticsearch.module.review.support.ReviewMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
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
    public Page<Review> getReviewsByCustomerId(Long customerId, Pageable pageable) {
        Page<ReviewDocument> allByCustomerId = reviewEsRepository.findAllByCustomerId(customerId, pageable);
        return new PageImpl<>(allByCustomerId.stream().map(ReviewMapper::fromDocument).toList(),
                pageable, allByCustomerId.getTotalElements());

    }

    @Override
    public void save(Review review) {
//        reviewEsRepository.save(ReviewMapper.toEntity(review));
        reviewEsRepository.saveWithIndex(ReviewMapper.toEntity(review));

    }

    @Override
    public void saveBulk(List<ReviewRequest> reviews) {
        List<ReviewDocument> list = reviews.stream().map(a -> ReviewMapper.toEntity(ReviewMapper.fromRequest(a))).toList();
        reviewEsRepository.saveBulk(list);
    }

    @Override
    public Page<Review> search(ReviewSearchRequest request) {
        SearchHits<ReviewDocument> searchHits = reviewEsRepository.search(request);
        return new PageImpl<>(searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .map(ReviewMapper::fromEntity)
                .toList(),
                PageRequest.of(request.getPage(), request.getSize()), searchHits.getTotalHits());
    }

    @Override
    public Page<Review> searchByUserInfo(ReviewSearchRequest request) {
        SearchHits<ReviewDocument> searchHits = reviewEsRepository.searchByUserInfo(request);
        return new PageImpl<>(searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .map(ReviewMapper::fromEntity)
                .toList(),
                PageRequest.of(request.getPage(), request.getSize()), searchHits.getTotalHits());
    }
}

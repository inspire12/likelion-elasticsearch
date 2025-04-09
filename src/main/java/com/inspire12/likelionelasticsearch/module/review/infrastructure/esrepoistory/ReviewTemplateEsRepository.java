package com.inspire12.likelionelasticsearch.module.review.infrastructure.esrepoistory;


import com.inspire12.likelionelasticsearch.module.review.application.dto.request.ReviewSearchRequest;
import com.inspire12.likelionelasticsearch.module.review.infrastructure.document.ReviewDocument;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReviewTemplateEsRepository {
//    List<String> autocompleteSentiment(String input);

    SearchHits<ReviewDocument> search(ReviewSearchRequest request);

    SearchHits<ReviewDocument> searchByUserInfo(ReviewSearchRequest request);

    void saveBulk(List<ReviewDocument> reviews);

    ReviewDocument saveWithIndex(ReviewDocument reviewDocument);
}
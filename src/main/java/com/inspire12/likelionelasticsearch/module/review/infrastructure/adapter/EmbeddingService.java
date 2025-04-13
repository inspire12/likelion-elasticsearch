package com.inspire12.likelionelasticsearch.module.review.infrastructure.adapter;

import com.inspire12.likelionelasticsearch.module.review.infrastructure.document.ReviewDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.MetadataMode;
import org.springframework.ai.embedding.Embedding;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.openai.OpenAiEmbeddingOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.retry.RetryUtils;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmbeddingService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private final OpenAiApi openAiApi;

    public EmbeddingService(OpenAiApi openAiApi) {
        this.openAiApi = openAiApi;
    }

    public Embedding getEmbedding(ReviewDocument reviewDocument) {

        OpenAiEmbeddingModel openAiEmbeddingModel = new OpenAiEmbeddingModel(
                this.openAiApi,
                MetadataMode.EMBED,
                OpenAiEmbeddingOptions.builder()
                        .model("text-embedding-ada-002")
                        .user("user-6")
                        .build(),
                RetryUtils.DEFAULT_RETRY_TEMPLATE);

        EmbeddingResponse embeddingResponse = openAiEmbeddingModel
                .embedForResponse(List.of(reviewDocument.toString()));

        Embedding embedding = embeddingResponse.getResults().getFirst();
        return embedding;
    }

    public List<Embedding> getEmbeddings(List<ReviewDocument> reviewDocuments) {

        OpenAiEmbeddingModel openAiEmbeddingModel = new OpenAiEmbeddingModel(
                this.openAiApi,
                MetadataMode.EMBED,
                OpenAiEmbeddingOptions.builder()
                        .model("text-embedding-ada-002")
                        .user("user-6")
                        .build(),
                RetryUtils.DEFAULT_RETRY_TEMPLATE);

        EmbeddingResponse embeddingResponse = openAiEmbeddingModel
                .embedForResponse(reviewDocuments.stream().map(ReviewDocument::toString).toList());

        List<Embedding> list = embeddingResponse.getResults().stream().toList();
        for (Embedding embedding : list) {
            log.info("{}" , embedding.getOutput());
        }
        return list;
    }
}

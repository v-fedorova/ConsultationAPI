package uk.co.heliosx.domain.model;

import java.util.Set;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record SubmittedAnswer(
        @Schema(name = "questionId",
                example = "allergy-genovian-pear-q2",
                description = "Answered question id",
                requiredMode = Schema.RequiredMode.REQUIRED)
        String questionId,

        @ArraySchema(arraySchema =  @Schema(
                example ="[\"allergy-genovian-pear-q2-a1\", \"allergy-genovian-pear-q2-a3\"]",
                description = "Chosen answers ids"))
        Set<String> answerIds,

        @Schema(name = "freeFormAnswer",
                example = "I'm allergic to starch also",
                description = "Text that customer has entered as a answer a free-form question",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String freeFormAnswer
) {}

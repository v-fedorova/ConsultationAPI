package uk.co.heliosx.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record Answer(
        @Schema(name = "answerId",
                example = "allergy-genovian-pear-1-1",
                description = "Unique id of the answer",
                requiredMode = Schema.RequiredMode.REQUIRED)
        String answerId,

        @Schema(name = "answerText",
                example = "Yes",
                description = "Text of the answer to show to customer",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String answerText,

        @Schema(name = "freeForm",
                example = "true",
                description = "If this answer allows free-form text from a customer",
                requiredMode = Schema.RequiredMode.REQUIRED)
        boolean freeForm
) {}

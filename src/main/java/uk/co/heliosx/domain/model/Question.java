package uk.co.heliosx.domain.model;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record Question(
        @Schema(name = "questionId",
                example = "allergy-genovian-pear-1",
                description = "Unique id of the question",
                requiredMode = Schema.RequiredMode.REQUIRED)
        String questionId,

        @Schema(name = "questionText",
                example = "Do you think you are allergic to Genovian Pear?",
                description = "Text of the question to show to customer",
                requiredMode = Schema.RequiredMode.REQUIRED)
        String questionText,

        @Schema(name = "questionType",
                example = "RADIO_GROUP",
                description = "Number and type of answers that this question has",
                requiredMode = Schema.RequiredMode.REQUIRED)
        QuestionType questionType,

        @Schema(name = "answers",
                description = "Possible answers to let customer choose from",
                requiredMode = Schema.RequiredMode.REQUIRED)
        List<Answer> answers
) {
        public boolean areAnswersValid(Set<String> givenAnswerIds, String freeFormAnswer) {
                if (!this.answers.stream()
                        .map(Answer::answerId)
                        .collect(Collectors.toSet())
                        .containsAll(givenAnswerIds)) {
                        return false;
                }

                if (this.questionType.equals(QuestionType.RADIO_GROUP) && givenAnswerIds.size() != 1) {
                        return false;
                }

                if (this.questionType.equals(QuestionType.CHECK_BOXES) && givenAnswerIds.isEmpty()) {
                        return false;
                }

                if (this.questionType.equals(QuestionType.FREE_FORM) && isBlank(freeFormAnswer)) {
                        return false;
                }

                return true;
        }
}

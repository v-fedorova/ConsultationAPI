package uk.co.heliosx.domain;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.stereotype.Repository;

import uk.co.heliosx.domain.exceptions.ConditionNotFoundException;
import uk.co.heliosx.domain.exceptions.QuestionNotFoundException;
import uk.co.heliosx.domain.model.Answer;
import uk.co.heliosx.domain.model.Question;
import uk.co.heliosx.domain.model.QuestionType;

@Repository
public class QuestionRepository {
    public static final String QUESTION_1 = "allergy-genovian-pear-q1";
    public static final String QUESTION_2 = "allergy-genovian-pear-q2";
    public static final String QUESTION_3 = "allergy-genovian-pear-q3";

    private static final Map<String, Set<String>> conditionsQuestions = Map.of(
            "allergy-genovian-pear", Set.of(QUESTION_1, QUESTION_2, QUESTION_3)
    );

    private static final Map<String, Question> questions = Map.of(
            QUESTION_1,
            Question.builder()
                    .questionId(QUESTION_1)
                    .questionText("Do you think you are allergic to Genovian Pear?")
                    .questionType(QuestionType.RADIO_GROUP)
                    .answers(List.of(Answer.builder()
                                .answerId("allergy-genovian-pear-q1-a1")
                                .answerText("Yes")
                                .build(),
                            Answer.builder()
                                .answerId("allergy-genovian-pear-q1-a2")
                                .answerText("No")
                                .build()))
                    .build(),

            QUESTION_2,
            Question.builder()
                    .questionId(QUESTION_2)
                    .questionText("Please check all symptoms you usually have after eating Genovian Pear")
                    .questionType(QuestionType.CHECK_BOXES)
                    .answers(List.of(Answer.builder()
                                .answerId("allergy-genovian-pear-q2-a1")
                                .answerText("runny nose or sneezing")
                                .build(),
                            Answer.builder()
                                .answerId("allergy-genovian-pear-q2-a2")
                                .answerText("pain or tenderness around your cheeks, eyes or forehead")
                                .build(),
                            Answer.builder()
                                .answerId("allergy-genovian-pear-q2-a3")
                                .answerText("itchy skin")
                                .build(),
                            Answer.builder()
                                .answerId("allergy-genovian-pear-q2-a4")
                                .answerText("swollen eyes, lips, mouth or throat")
                                .build()))
                    .build(),

            QUESTION_3,
            Question.builder()
                    .questionId(QUESTION_3)
                    .questionText("Anything else you would like us to know")
                    .questionType(QuestionType.FREE_FORM)
                    .answers(List.of(Answer.builder()
                             .answerId("allergy-genovian-pear-q3-a1")
                             .freeForm(true)
                             .build()))
                    .build()
    );

    public List<Question> getQuestionsByConditionId(String conditionId) throws ConditionNotFoundException {
        validateConditionId(conditionId);

        return conditionsQuestions.get(conditionId).stream()
                .map(questions::get)
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(Question::questionId))
                .toList();
    }

    public void validateConditionId(String conditionId) throws ConditionNotFoundException {
        if (!conditionsQuestions.containsKey(conditionId)) {
            throw new ConditionNotFoundException(conditionId);
        }
    }

    public void validateQuestionId(String conditionId, String questionId) throws QuestionNotFoundException {
        if (!conditionsQuestions.get(conditionId).contains(questionId)) {
            throw new QuestionNotFoundException(questionId);
        }
    }
}

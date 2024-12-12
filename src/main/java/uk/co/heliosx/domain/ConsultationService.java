package uk.co.heliosx.domain;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uk.co.heliosx.domain.exceptions.ConditionNotFoundException;
import uk.co.heliosx.domain.exceptions.QuestionNotFoundException;
import uk.co.heliosx.domain.exceptions.SubmittedAnswerBadRequestException;
import uk.co.heliosx.domain.model.PreliminaryCustomerResponse;
import uk.co.heliosx.domain.model.Question;
import uk.co.heliosx.domain.model.SubmittedAnswer;

@Service
@AllArgsConstructor
public class ConsultationService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public List<Question> getQuestions(String conditionId) throws ConditionNotFoundException {
        return questionRepository.getQuestionsByConditionId(conditionId);
    }

    public PreliminaryCustomerResponse submitAnswers(String customerId, String conditionId, List<SubmittedAnswer> submittedAnswers)
            throws ConditionNotFoundException, QuestionNotFoundException, SubmittedAnswerBadRequestException {
        questionRepository.validateConditionId(conditionId);
        submittedAnswers.forEach(answer -> questionRepository.validateQuestionId(conditionId, answer.questionId()));
        validateAnswers(submittedAnswers);

        answerRepository.saveAnswer(customerId, conditionId, submittedAnswers);

        return getPreliminaryResponse(submittedAnswers);
    }

    private void validateAnswers(List<SubmittedAnswer> submittedAnswers) throws SubmittedAnswerBadRequestException {
        // for every submitted answer fetch question from questionRepository by id and call Question::areAnswersValid
        // pass all ids of questions with invalid answers to SubmittedAnswerBadRequestException

        if (submittedAnswers.isEmpty()) {
            throw new SubmittedAnswerBadRequestException(String.join(", ", QuestionRepository.QUESTION_1, QuestionRepository.QUESTION_2, QuestionRepository.QUESTION_3));
        }
    }

    private PreliminaryCustomerResponse getPreliminaryResponse(List<SubmittedAnswer> submittedAnswers) {
        boolean noAllergy = submittedAnswers.stream()
                .anyMatch(answer -> answer.questionId().equals(QuestionRepository.QUESTION_1) && answer.answerIds().contains("allergy-genovian-pear-q1-a2"));
        if (noAllergy) {
            return PreliminaryCustomerResponse.builder()
                    .couldPrescribe(false)
                    .failureReason("We are not able to prescribe you this kind of medication, because you may not have allergy at all")
                    .build();
        }

        return PreliminaryCustomerResponse.builder()
                .couldPrescribe(true)
                .build();
    }
}

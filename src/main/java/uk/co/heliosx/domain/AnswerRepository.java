package uk.co.heliosx.domain;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import uk.co.heliosx.domain.model.SubmittedAnswer;

@Slf4j
@Repository
public class AnswerRepository {
    public void saveAnswer(String customerId, String conditionId, List<SubmittedAnswer> submittedAnswers) {
      log.info("Answers from customer {} about condition {} was saved", customerId, conditionId);
    }
}

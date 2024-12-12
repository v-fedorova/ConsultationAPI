package uk.co.heliosx.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.SneakyThrows;
import uk.co.heliosx.domain.exceptions.ConditionNotFoundException;

class QuestionRepositoryTest {
    @Test
    @DisplayName("given not valid condition id when call validateConditionId then throw ConditionNotFoundException")
    @SneakyThrows
    void notValidConditionId() {
        QuestionRepository questionRepository = new QuestionRepository();
        assertThrows(ConditionNotFoundException.class, () -> questionRepository.validateConditionId("not-valid-condition"));
    }

    // Test that validateQuestionId will throw correctly

    // Tests for getQuestionsByConditionId:
    // 1. Will throw if conditionId is not valid
    // 2. Happy path
}
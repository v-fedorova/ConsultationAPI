package uk.co.heliosx.domain.exceptions;

import static java.lang.String.format;

public class QuestionNotFoundException extends RuntimeException {
    public QuestionNotFoundException(String questionId) {
        super(format("Question with id %s not found", questionId));
    }
}

package uk.co.heliosx.domain.exceptions;

import static java.lang.String.format;

public class SubmittedAnswerBadRequestException extends Exception {
    public SubmittedAnswerBadRequestException(String questionIds) {
        super(format("Not allowed combination of answers for the question(s) %s", questionIds));
    }
}

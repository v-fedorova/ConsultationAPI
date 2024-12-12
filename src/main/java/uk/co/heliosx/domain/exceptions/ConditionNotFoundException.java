package uk.co.heliosx.domain.exceptions;

import static java.lang.String.format;

public class ConditionNotFoundException extends Exception {
    public ConditionNotFoundException(String conditionId) {
        super(format("Condition with id %s not found", conditionId));
    }
}

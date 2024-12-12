package uk.co.heliosx;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import uk.co.heliosx.domain.exceptions.ConditionNotFoundException;
import uk.co.heliosx.domain.exceptions.QuestionNotFoundException;
import uk.co.heliosx.domain.exceptions.SubmittedAnswerBadRequestException;

@Slf4j
@RestControllerAdvice(basePackageClasses = ConsultationController.class)
public class ConsultationControllerAdvice {
    @ExceptionHandler(value = {
            ConditionNotFoundException.class,
            QuestionNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> entityNotFound(Exception exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(value = SubmittedAnswerBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> badRequest(Exception exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}

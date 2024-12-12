package uk.co.heliosx;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import uk.co.heliosx.domain.exceptions.ConditionNotFoundException;
import uk.co.heliosx.domain.ConsultationService;
import uk.co.heliosx.domain.exceptions.QuestionNotFoundException;
import uk.co.heliosx.domain.exceptions.SubmittedAnswerBadRequestException;
import uk.co.heliosx.domain.model.PreliminaryCustomerResponse;
import uk.co.heliosx.domain.model.Question;
import uk.co.heliosx.domain.model.SubmittedAnswer;

@RestController
@RequestMapping("/consultation")
@Tag(name = "Consultation", description = "Consultation API")
@AllArgsConstructor
public class ConsultationController {

    private final ConsultationService consultationService;

    @GetMapping("/questions")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            description = "Get questions with possible answers for a given condition",
            summary = "Get questions for a condition"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "404", description = "Condition not found", content = @Content)
    })
    public List<Question> getQuestions(
            @Schema(name = "condition-id",
                    example = "allergy-genovian-pear",
                    description = "Unique id of the condition to treat",
                    requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestParam("condition-id") String conditionId
    ) throws ConditionNotFoundException {
        return consultationService.getQuestions(conditionId);
    }

    @PostMapping("/customers/{customer-id}/conditions/{condition-id}/answers")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            description = "Submit a set of customer's answers for given condition.<br>" +
                    "Required authentication: customer with id \"customer-id\"",
            summary = "Submit customer's answers for a condition"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Invalid answers", content = @Content),
            @ApiResponse(responseCode = "404", description = "Condition or question not found", content = @Content)
    })
    @SecurityRequirement(name = "Bearer Authentication")
    public PreliminaryCustomerResponse submitAnswers(
            @Schema(name = "customer-id",
                    example = "181273e1-7cd8-47d7-85ee-aa34e34c3a6a",
                    description = "Unique id of the customer",
                    requiredMode = Schema.RequiredMode.REQUIRED)
            @PathVariable("customer-id") String customerId,

            @Schema(name = "condition-id",
                    example = "allergy-genovian-pear",
                    description = "Unique id of the condition to treat",
                    requiredMode = Schema.RequiredMode.REQUIRED)
            @PathVariable("condition-id") String conditionId,

            @RequestBody List<SubmittedAnswer> submittedAnswers
    ) throws ConditionNotFoundException, QuestionNotFoundException, SubmittedAnswerBadRequestException {
        return consultationService.submitAnswers(customerId, conditionId, submittedAnswers);
    }
}

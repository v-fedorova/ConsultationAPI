package uk.co.heliosx.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record PreliminaryCustomerResponse(
        @Schema(name = "couldPrescribe",
                example = "true",
                description = "If we are likely able to prescribe the medication",
                requiredMode = Schema.RequiredMode.REQUIRED)
        boolean couldPrescribe,

        @Schema(name = "failureReason",
                example = "We are not able to prescribe you this kind of medication, because you may not have allergy at all",
                description = "Explanation why a customer could not be prescribed this medication",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String failureReason
) {}

package com.easyrides.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;


@Data
@Schema(
        name = "Loans",
        description = "Loan details of the Customer"
)
public class LoansDto {

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    @Schema(
            description = "Mobile number of EasyRidersBank", example = "1234567890"
    )
    private String mobileNumber;

    @NotEmpty(message = "Loan number can not be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Loan number must be 10 digits")
    @Schema(
            description = "Loan number of EasyRidersBank", example = "1234567890"
    )
    private String loanNumber;

    @NotEmpty(message = "Loan type can not be null or empty")
    @Schema(
            description = "Loan type of EasyRidersBank", example = "Home Loan"
    )
    private String loanType;
    @Positive(message = "Total Loan should be greater than zeroy")
    @Schema(
            description = "Total Loan of Customer", example = "100000"
    )
    private int totalLoan;

    @Positive(message = "Amount paid used should be greater than zeroy")
    @Schema(
            description = "Amount paid used by a customer", example = "10000"
    )
    private int amountPaid;

    @Positive(message = "Outstanding Amount used should be greater than zeroy")
    @Schema(
            description = "Outstanding Amount against card", example = "90000"
    )
    private int outstandingAmount;
}

package com.easyrides.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
@Schema(
        name = "Accounts",
        description = "Account details of the Customer"
)
public class AccountsDto {

    @NotEmpty(message = "Account number can not be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits")
    @Schema(
            description = "Account number of EasyRidersBank", example = "1234567890"
    )
    private Long accountNumber;
    @NotEmpty(message = "Account type can not be null or empty")
    @Schema(
            description = "Account type of EasyRidersBank", example = "Savings"
    )
    private String accountType;
    @NotEmpty(message = "Branch address type can not be null or empty")
    @Schema(
            description = "Branch address of EasyRidersBank", example = "21, Mumbai"
    )
    private String branchAddress;
}

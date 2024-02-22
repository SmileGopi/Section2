package com.easyrides.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Schema(
        name = "Customer",
        description = "Schema to build Customer and Account information"
)
public class CustomerDto {
    @NotEmpty(message = "Customer name can not be a null or empty")
    @Size(min = 5, max = 30, message = "The length of customer name should be between 5 and 30")
    @Schema(
            description = "Customer name of EasyRidersBank", example = "Victor"
    )
    private String name;
    @NotEmpty(message = "Email address can not be null or empty")
    @Email(message = "Email should be a valid value")
    @Schema(
            description = "Email address of EasyRidersBank", example = "emai@email.com"
    )
    private String email;
    @NotEmpty(message = "Mobile number can not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    @Schema(
            description = "Mobile number of EasyRidersBank", example = "21, Mumbai"
    )
    private String mobileNumber;

    @Schema(
            description = "Account details of the customer"
    )
    private AccountsDto accountsDto;
}

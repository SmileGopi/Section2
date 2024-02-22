package com.easyrides.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;


@Data
@Schema(
        name = "Cards",
        description = "Card details of the Customer"
)
public class CardsDto {

    @NotEmpty(message = "Card number can not be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    @Schema(
            description = "Mobile number of EasyRidersBank", example = "1234567890"
    )
    private String mobileNumber;

    @NotEmpty(message = "Card number can not be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Card number must be 10 digits")
    @Schema(
            description = "Card number of EasyRidersBank", example = "1234567890"
    )
    private String cardNumber;

    @NotEmpty(message = "Card type can not be null or empty")
    @Schema(
            description = "Card type of EasyRidersBank", example = "Credit card"
    )
    private String cardType;
    @Positive(message = "Total card limit should be greater than zeroy")
    @Schema(
            description = "Total limit of Card", example = "100000"
    )
    private int totalLimit;

        @Positive(message = "Total amount used should be greater than zeroy")
    @Schema(
            description = "Total amount used by a customer", example = "10000"
    )
    private int amountUsed;

    @Positive(message = "Available amount used should be greater than zeroy")
    @Schema(
            description = "Total availabe amount against card", example = "90000"
    )
    private int availableAmount;
}

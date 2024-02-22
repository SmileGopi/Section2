package com.easyrides.accounts.controller;

import com.easyrides.accounts.constants.AccountConstants;
import com.easyrides.accounts.dto.CustomerDto;
import com.easyrides.accounts.dto.ErrorResponseDto;
import com.easyrides.accounts.dto.ResponseDto;
import com.easyrides.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;


@Tag(
        name = "CRUD REST APIs for Accounts in EasyRidersBank",
        description = "CRUD REST APIs in EasyRidersBank to create, update, fetch and delete account details"
)
@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountsController {
    @Autowired
    IAccountsService iAccountsService;
    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create new Customer & Account inside EasyRidersBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status Created"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal server error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){
        System.out.println(customerDto.getMobileNumber());
        iAccountsService.createCustomer(customerDto);
        return ResponseEntity.status((HttpStatus.CREATED))
                .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(
            @RequestParam @Pattern(regexp="(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                            String mobileNumber)
    {
        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

}

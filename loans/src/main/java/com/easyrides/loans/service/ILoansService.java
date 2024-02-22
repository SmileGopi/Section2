package com.easyrides.loans.service;

import com.easyrides.loans.dto.LoansDto;
import org.springframework.http.ResponseEntity;

public interface ILoansService {
    /**
     *
     * @param mobileNumber - Mobile number of the customer
     */
    void createLoan(String mobileNumber);

    /**
     *
     * @param mobileNumber - mobile number as input
     * @return LoansDto object with loan details based on given mobile number
     */
    LoansDto fetchLoan(String mobileNumber);

    /**
     *
     * @param loansDto - LoansDto Object
     * @return boolean indicating if the loan details are updated successfully or not
     */
    boolean updateLoan(LoansDto loansDto);

    /**
     *
     * @param mobileNumber - Input Mobile number
     * @return boolean indicating if the delete loan details are successful or not
     */
    boolean deleteLoan(String mobileNumber);
}

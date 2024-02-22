package com.easyrides.accounts.service;

import com.easyrides.accounts.dto.CustomerDto;
import org.springframework.http.ResponseEntity;

public interface IAccountsService {
    /**
     *
     * @param customerDto - CustomerDto object to create customer details
     */
    void createCustomer(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber - mobile number as input
     * @return CustomerDto object with account details based on given mobile number
     */
    CustomerDto fetchAccount(String mobileNumber);

    /**
     *
     * @param customerDto - CustomerDto Object
     * @return boolean indicating if the account details are updated successfully or not
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber - Input Mobile number
     * @return boolean indicating if the delete account details are successful or not
     */
    boolean deleteAccount(String mobileNumber);
}

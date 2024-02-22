package com.easyrides.accounts.service.Impl;

import com.easyrides.accounts.constants.AccountConstants;
import com.easyrides.accounts.dto.AccountsDto;
import com.easyrides.accounts.dto.CustomerDto;
import com.easyrides.accounts.entity.Accounts;
import com.easyrides.accounts.entity.Customer;
import com.easyrides.accounts.exception.CustomerAlreadyExistsException;
import com.easyrides.accounts.exception.ResourceNotFoundException;
import com.easyrides.accounts.mapper.AccountsMapper;
import com.easyrides.accounts.mapper.CustomerMapper;
import com.easyrides.accounts.repository.AccountsRepository;
import com.easyrides.accounts.repository.CustomerRepository;
import com.easyrides.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service @AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {
    private CustomerRepository customerRepository;
    private AccountsRepository accountsRepository;

    /**
     *
     * @param customerDto - CustomerDto object to create customer details
     */
    @Override
    public void createCustomer(CustomerDto customerDto)
    {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("customer already registered with the given mobile number "+customerDto.getMobileNumber());

        }
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    /**
     *
     * @param customer data as input
     * @return Accounts object which is created based on the given data
     */
    private Accounts createNewAccount(Customer customer)
    {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccountNumber = 1000000000L * new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccountNumber);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        return newAccount;
    }

    /**
     *
     * @param mobileNumber - mobile number as input
     * @return CustomerDto object for the given mobile number
     */
    @Override
    public CustomerDto fetchAccount(String mobileNumber)
    {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
        return customerDto;
    }

    /**
     *
     * @param customerDto - CustomerDto Object
     * @return boolean to identify whether the account is updated or not for the given CustomerDto object
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto)
    {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if (accountsDto!=null) {
            Accounts accunts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "Account Number", accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccounts(accountsDto, accunts);
            accountsRepository.save(accunts);
            Long customerId = accunts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Custmer", "Customer ID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
            return isUpdated;
    }

    /**
     *
     * @param mobileNumber - Input Mobile number
     * @return boolean to identify whether the account is deleted or not for the given mobile number
     */
    @Override
    public boolean deleteAccount(String mobileNumber)
    {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }



}

package com.easyrides.loans.service.Impl;

import com.easyrides.loans.constants.LoansConstants;

import com.easyrides.loans.dto.LoansDto;

import com.easyrides.loans.entity.Loans;
import com.easyrides.loans.exception.LoanAlreadyExistsException;
import com.easyrides.loans.exception.ResourceNotFoundException;

import com.easyrides.loans.mapper.LoansMapper;

import com.easyrides.loans.repository.LoansRepository;
import com.easyrides.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service @AllArgsConstructor
public class LoansServiceImpl implements ILoansService {
    private LoansRepository loansRepository;


    /**
     *
     * @param mobileNumber - Mobile number to create card details
     */
    @Override
    public void createLoan(String mobileNumber)
    {
        Optional<Loans> optionalLoans = loansRepository.findByMobileNumber(mobileNumber);
        if (optionalLoans.isPresent()) {
            throw new LoanAlreadyExistsException("Loan already registered with the given mobile number "+mobileNumber);

        }
        loansRepository.save(createNewLoan(mobileNumber));
    }

    /**
     *
     * @param mobileNumber data as input
     * @return Loans object which is created based on the given data
     */
    private Loans createNewLoan(String mobileNumber)
    {
        Loans newLoan = new Loans();
        long randomLoanNumber = 1000000000L * new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }

    /**
     *
     * @param mobileNumber - mobile number as input
     * @return LoansDto object for the given mobile number
     */
    @Override
    public LoansDto fetchLoan(String mobileNumber)
    {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));

        return LoansMapper.mapToLoansDto(loans, new LoansDto());
    }

    /**
     *
     * @param loansDto - LoansDto Object
     * @return boolean to identify whether the loan is updated or not for the given loansDto object
     */
    @Override
    public boolean updateLoan(LoansDto loansDto)
    {
           Loans loans = loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Loan", "Loan Number", loansDto.getLoanNumber())
            );
            LoansMapper.mapToLoans(loansDto, loans);
            loansRepository.save(loans);
            return true;
    }

    /**
     *
     * @param mobileNumber - Input Mobile number
     * @return boolean to identify whether the loan is deleted or not for the given mobile number
     */
    @Override
    public boolean deleteLoan(String mobileNumber)
    {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        loansRepository.deleteById(loans.getLoanId());
        return true;
    }



}

package com.easyrides.cards.service;

import com.easyrides.cards.dto.CardsDto;
import org.springframework.http.ResponseEntity;

public interface ICardsService {
    /**
     *
     * @param mobileNumber - Mobile number of the customer
     */
    void createCard(String mobileNumber);

    /**
     *
     * @param mobileNumber - mobile number as input
     * @return CardsDto object with cards details based on given mobile number
     */
    CardsDto fetchCard(String mobileNumber);

    /**
     *
     * @param cardsDto - CardsDto Object
     * @return boolean indicating if the card details are updated successfully or not
     */
    boolean updateCard(CardsDto cardsDto);

    /**
     *
     * @param mobileNumber - Input Mobile number
     * @return boolean indicating if the delete card details are successful or not
     */
    boolean deleteCard(String mobileNumber);
}

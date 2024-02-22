package com.easyrides.cards.service.Impl;

import com.easyrides.cards.constants.CardsConstants;

import com.easyrides.cards.dto.CardsDto;

import com.easyrides.cards.entity.Cards;
import com.easyrides.cards.exception.CardAlreadyExistsException;
import com.easyrides.cards.exception.ResourceNotFoundException;

import com.easyrides.cards.mapper.CardsMapper;

import com.easyrides.cards.repository.CardsRepository;
import com.easyrides.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service @AllArgsConstructor
public class CardsServiceImpl implements ICardsService {
    private CardsRepository cardsRepository;


    /**
     *
     * @param mobileNumber - Mobile number to create card details
     */
    @Override
    public void createCard(String mobileNumber)
    {
        Optional<Cards> optionalCards = cardsRepository.findByMobileNumber(mobileNumber);
        if (optionalCards.isPresent()) {
            throw new CardAlreadyExistsException("card already registered with the given mobile number "+mobileNumber);

        }
        cardsRepository.save(createNewCard(mobileNumber));
    }

    /**
     *
     * @param mobileNumber data as input
     * @return Cards object which is created based on the given data
     */
    private Cards createNewCard(String mobileNumber)
    {
        Cards newCard = new Cards();
        long randomCardNumber = 1000000000L * new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    /**
     *
     * @param mobileNumber - mobile number as input
     * @return CardsDto object for the given mobile number
     */
    @Override
    public CardsDto fetchCard(String mobileNumber)
    {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));

        return CardsMapper.mapToCardsDto(cards, new CardsDto());
    }

    /**
     *
     * @param cardsDto - CardsDto Object
     * @return boolean to identify whether the account is updated or not for the given CustomerDto object
     */
    @Override
    public boolean updateCard(CardsDto cardsDto)
    {
           Cards cards = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Card", "Card NumberID", cardsDto.getCardNumber())
            );
            CardsMapper.mapToCards(cardsDto, cards);
            cardsRepository.save(cards);


            return true;
    }

    /**
     *
     * @param mobileNumber - Input Mobile number
     * @return boolean to identify whether the card is deleted or not for the given mobile number
     */
    @Override
    public boolean deleteCard(String mobileNumber)
    {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        cardsRepository.deleteById(cards.getCardId());

        return true;
    }



}

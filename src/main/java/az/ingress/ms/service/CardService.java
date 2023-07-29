package az.ingress.ms.service;

import az.ingress.ms.dao.entity.Card;
import az.ingress.ms.dao.repository.CardRepository;
import az.ingress.ms.exception.NotFoundException;
import az.ingress.ms.mapper.CardMapper;
import az.ingress.ms.model.request.SaveCardRequest;
import az.ingress.ms.model.request.UpdateCardRequest;
import az.ingress.ms.model.response.CardResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static az.ingress.ms.mapper.CardMapper.*;
import static az.ingress.ms.model.enums.CardStatus.BLOCKED;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public void cardServiceRunning(){
        System.out.println("Card service is running");
    }

    public List<CardResponse> getAllCards(){
        List<CardResponse> cards= cardRepository.findAll()
                .stream().map(CardMapper::buildCardResponse)
                .collect(Collectors.toList());
        return cards;
    }
    public CardResponse getCardById(Long id){
        var card=fetchCardIfExist(id);
        return buildCardResponse(card);
    }

    public void createCard(SaveCardRequest request){
        cardRepository.save(buildToEntity(request));
    }

    public void updateCard(Long id, UpdateCardRequest cardRequest){
        var cards=fetchCardIfExist(id);
        updateCards(cards, cardRequest);
        cardRepository.save(cards);
    }

    public void deleteCard(Long id){
        var cards=fetchCardIfExist(id);
        cards.setCardStatus(BLOCKED);
        cardRepository.save(cards);
    }

    public Card fetchCardIfExist(Long id){
        return cardRepository.findById(id)
                .orElseThrow(()->new NotFoundException("CARD_NOT_FOUND"));
    }
}

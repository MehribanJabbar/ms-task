package az.ingress.ms.mapper;

import az.ingress.ms.dao.entity.Card;
import az.ingress.ms.model.request.SaveCardRequest;
import az.ingress.ms.model.request.UpdateCardRequest;
import az.ingress.ms.model.response.CardResponse;

public class CardMapper {

    public static CardResponse buildCardResponse(Card cards){
        return CardResponse.builder()
                .id(cards.getId())
                .pan(cards.getPan())
                .cardsHolder(cards.getCardsHolder())
                .cvv(cards.getCvv())
                .expirationDate(cards.getExpirationDate())
                .build();

    }

    public static Card buildToEntity(SaveCardRequest request){
        return Card.builder()
                .cardsHolder(request.getCardsHolder())
                .expirationDate(request.getExpirationDate())
                .cvv(request.getCvv())
                .pan(request.getPan())
                .build();
    }

    public static void updateCards(Card cards, UpdateCardRequest request){

    }
}

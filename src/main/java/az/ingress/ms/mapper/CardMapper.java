package az.ingress.ms.mapper;

import az.ingress.ms.dao.entity.CardEntity;
import az.ingress.ms.model.request.SaveCardRequest;
import az.ingress.ms.model.request.UpdateCardRequest;
import az.ingress.ms.model.response.CardResponse;

public class CardMapper {

    public static CardResponse buildCardResponse(CardEntity cards){
        return CardResponse.builder()
                .id(cards.getId())
                .pan(cards.getPan())
                .cardsHolder(cards.getCardsHolder())
                .cvv(cards.getCvv())
                .expirationDate(cards.getExpirationDate())
                .build();

    }

    public static CardEntity buildToEntity(SaveCardRequest request){
        return CardEntity.builder()
                .cardsHolder(request.getCardsHolder())
                .expirationDate(request.getExpirationDate())
                .cvv(request.getCvv())
                .pan(request.getPan())
                .build();
    }

    public static void updateCards(CardEntity cardEntity, UpdateCardRequest request){
        cardEntity.setCardsHolder(request.getCardsHolder());
        cardEntity.setCvv(request.getCvv());
        cardEntity.setExpirationDate(request.getExpirationDate());
        cardEntity.setPan(request.getPan());
    }
}

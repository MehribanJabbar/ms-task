package az.ingress.ms.mapper

import az.ingress.ms.dao.entity.CardEntity
import az.ingress.ms.model.request.SaveCardRequest
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class CardEntityMapperTest extends Specification{
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private CardMapper cardMapper

    def "TestBuildCardResponse"(){
        given:
        def card = random.nextObject(CardEntity)

        when:
        def actual = CardMapper.buildCardResponse(card)

        then:
        actual.id == card.id
        actual.pan == card.pan
        actual.cvv == card.cvv
        actual.cardsHolder == card.cardsHolder
        actual.expirationDate == card.expirationDate
    }

    def "TestCardRequest"(){
        given:
        def card = random.nextObject(SaveCardRequest)

        when:
        def actual = CardMapper.buildCardRequest(card)

        then:
        actual.cardsHolder == card.cardsHolder
        actual.pan == card.pan
        actual.expirationDate == card.expirationDate
        actual.cvv == card.cvv

    }
}

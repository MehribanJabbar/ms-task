package az.ingress.ms.service


import az.ingress.ms.dao.entity.CardEntity
import az.ingress.ms.dao.repository.CardRepository
import az.ingress.ms.exception.NotFoundException
import az.ingress.ms.mapper.CardMapper
import az.ingress.ms.model.request.SaveCardRequest
import az.ingress.ms.model.request.UpdateCardRequest
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class CardServiceTest extends Specification{
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private CardRepository cardsRepository
    private CardService cardService

    def setup(){
        cardsRepository = Mock()
        cardService = new CardService(cardsRepository)
    }

    def "TestGetCardById success"(){
        given:
        def id = random.nextObject(Long)
        def entity = random.nextObject(CardEntity)

        when:
        def actual = cardService.getCardById(id)

        then:
        1 * cardsRepository.findById(id) >> Optional.of(entity)
        actual.id == entity.id
        actual.cardsHolder == entity.cardsHolder
        actual.pan == entity.pan
        actual.cvv == entity.cvv
        actual.expirationDate == entity.expirationDate
    }

    def "TestGetCardById entity not found"(){
        given:
        def id = random.nextObject(Long)

        when:
        cardService.getCardById(id)

        then:
        1 * cardsRepository.findById(id) >> Optional.empty()

        NotFoundException ex = thrown()
        ex.message == "CARD_NOT_FOUND"
    }

    def "testUpdateCard success"(){
        given:
        def id = random.nextObject(Long)
        def request = random.nextObject(UpdateCardRequest)
        def card = random.nextObject(CardEntity)

        when:
        cardService.updateCard(id, request)

        then:
        1 * cardsRepository.findById(id) >> Optional.of(card)
        1 * cardsRepository.save(card)
    }

    def "testUpdateCard entity not found"(){
        given:
        def id = random.nextObject(Long)
        def request = random.nextObject(UpdateCardRequest)

        when:
        cardService.updateCard(id, request)

        then:
        1 * cardsRepository.findById(id) >> Optional.empty()

        NotFoundException ex = thrown()
        ex.message == "CARD_NOT_FOUND"
    }

    def "TestCreateCard success"(){
        given:
        def request = new SaveCardRequest()
        def card = CardMapper.buildToEntity(request)

        when:
        cardService.createCard(request)

        then:
        1 * cardsRepository.save(card)
    }

    def "TestDeleteCard success"(){
        given:
        def id = random.nextObject(Long)
        def entity = random.nextObject(CardEntity)

        when:
        cardService.deleteCard(id)

        then:
        1 * cardsRepository.findById(id) >> Optional.of(entity)
        1 * cardsRepository.save(entity)
    }

    def "TestDeleteCard entity not found"(){
        given:
        def id = random.nextObject(Long)

        when:
        cardService.deleteCard(id)

        then:
        1 * cardsRepository.findById(id) >> Optional.empty()

        NotFoundException ex = thrown()
        ex.message == "CARD_NOT_FOUND"
    }
}

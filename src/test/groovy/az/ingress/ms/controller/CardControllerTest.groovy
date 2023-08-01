package az.ingress.ms.controller

import az.ingress.ms.model.request.SaveCardRequest
import az.ingress.ms.model.request.UpdateCardRequest
import az.ingress.ms.model.response.CardResponse
import az.ingress.ms.service.CardService
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import spock.lang.Specification

import java.time.LocalDate

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put

class CardControllerTest extends Specification {
    public static final dto1 = new CardResponse(1L, "123", "1234234534564567",
            LocalDate.of(2020, 1, 1), "Tony Stark")
    private CardService cardService
    private MockMvc mockMvc

    def setup() {
        cardService = Mock()
        def cardController = new CardController(cardService)
        mockMvc = MockMvcBuilders.standaloneSetup(cardController)
                .build()
    }

    def "TestGetAllCards"(){
        given:
        def url = "/v1/cards"

        def dto1 = new CardResponse(1L, "123", "1234234534564567",
                LocalDate.of(2020, 1,1), "Tony Stark")
        def dto2 = new CardResponse(2L, "321", "1234234509879876",
                LocalDate.of(2021, 1,1), "Natasha")

        def responseViewList = [dto1, dto2]

        def expectedResponse = '''
        [
            {
                "id" : 1,
                "cvv" : "123",
                "pan" : "1234234534564567",
                "expirationDate" : [2020,1,1],
                "cardsHolder" : "Tony Stark"
            },
            {
                "id" : 2,
                "cvv" : "321",
                "pan" : "1234234509879876",
                "expirationDate" : [2021,1,1],
                "cardsHolder" : "Natasha"
            }
            ]
        '''

        when:
        def result = mockMvc.perform(get(url)).andReturn()

        then:
        1 * cardService.getAllCards() >> responseViewList

        def response = result.response
        response.status == HttpStatus.OK.value()
        JSONAssert.assertEquals(expectedResponse, response.getContentAsString(), false)


    }

    def "TestGetCardById"() {
        given:
        def id = 1L
        def url = "/v1/cards/$id"

        def responseView = new CardResponse(1L, "123", "1234345645676789", LocalDate.of(2020, 1, 1), "Tony Stark")

        def expectedResponse = '''
                {
                    "id" : 1,
                    "cvv" : "123",
                    "pan" : "1234345645676789",
                    "expirationDate" : [2020,1,1],
                    "cardsHolder" : "Tony Stark"
                }
        '''

        when:
        def result = mockMvc.perform(
                get(url)
        )
                .andReturn()

        then:
        1 * cardService.getCardById(id) >> responseView

        def response = result.response
        response.status == HttpStatus.OK.value()
        JSONAssert.assertEquals(expectedResponse, response.getContentAsString(), false)
    }

    def "TestSaveCard"() {
        def url = "/v1/cards"

        def dto = new SaveCardRequest("123", "1234345645676789",
                LocalDate.of(2020, 1, 1), "Tony Stark")

        def requestBody = '''
                {
                    "cvv" : "123",
                    "pan" : "1234345645676789",
                    "expirationDate" : [2020,1,1],
                    "cardsHolder" : "Tony Stark"
                }
        '''

        when:
        def result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andReturn()

        then:
        1 * cardService.createCard(dto)

        def response = result.response
        response.status == HttpStatus.CREATED.value()
    }


    def "TestUpdateProduct"(){
        given:
        def id = 1L
        def url = "/v1/cards/$id"

        def dto = new UpdateCardRequest("123", "1234345645676789",
                LocalDate.of(2020, 1, 1), "Tony Stark")

        def requestBody = '''
                {
                    "cvv" : "123",
                    "pan" : "1234345645676789",
                    "expirationDate" : [2020,1,1],
                    "cardsHolder" : "Tony Stark"
                }
        '''

        when:
        def result = mockMvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andReturn()

        then:
        1 * cardService.updateCard(id, dto)

        def response = result.response
        response.status == HttpStatus.NO_CONTENT.value()
    }

    def "TestDeleteCard"(){
        given:
        def id = 1L
        def url = "/v1/cards/$id"

        when:
        def result = mockMvc.perform (delete(url)).andReturn()

        then:
        1 * cardService.deleteCard(id)

        def response = result.response
        response.status == HttpStatus.NO_CONTENT.value()
    }
}

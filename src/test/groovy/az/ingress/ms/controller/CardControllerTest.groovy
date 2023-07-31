package az.ingress.ms.controller

import az.ingress.ms.model.response.CardResponse
import az.ingress.ms.service.CardService
import org.mockito.Mock
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import java.time.LocalDate

class CardControllerTest extends Specification {
    private CardService cardService
    private MockMvc mockMvc

    def setup() {
        cardService = Mock()
        def cardController = new CardController(cardService)
        mockMvc = MockMvcBuilders.standaloneSetup(cardController)
                .build()
    }

    def "TestGetCardById"() {
        given:
        def id = 1L
        def url = "v1/cards/$id"

        def responseView = new CardResponse( 1L ,"123", "1234345645676789",LocalDate.of(2020,1,1), "Mehriban Jabbar")

        def expectedResponse = '''
                {
                    "id" = 1,
                    "cvv" = "123",
                    "pan" = "1234345645676789",
                    "expirationDate" = [2020,1,1],
                    "cardsHolder" = "Mehriban Jabbar",
                }
                '''



    }
}

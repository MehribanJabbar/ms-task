package az.ingress.ms.controller;

import az.ingress.ms.model.request.SaveCardRequest;
import az.ingress.ms.model.request.UpdateCardRequest;
import az.ingress.ms.model.response.CardResponse;
import az.ingress.ms.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("v1/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping
    public List<CardResponse> getAllCards(){
        return cardService.getAllCards();
    }

    @GetMapping("/{id}")
    public CardResponse getCardsById(@PathVariable Long id){
        return cardService.getCardById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void createCards(@RequestBody SaveCardRequest request){
        cardService.createCard(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateCards(@PathVariable Long id, @RequestBody UpdateCardRequest cardRequest){
        cardService.updateCard(id, cardRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteCards(@PathVariable Long id){
        cardService.deleteCard(id);
    }


}

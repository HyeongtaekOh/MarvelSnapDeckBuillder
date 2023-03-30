package snap.deckBuilder.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import snap.deckBuilder.Service.CardInformationService;
import snap.deckBuilder.domain.CardDTO;
import snap.deckBuilder.domain.CardInfo;

import java.util.List;

@Controller
public class CardController {

  private final CardInformationService cardInformationService;

  @Autowired
  public CardController(CardInformationService cardInformationService) {
    this.cardInformationService = cardInformationService;
  }

  @GetMapping("card")
  public @ResponseBody List<CardInfo> getAllCards() {
    return cardInformationService.findAllCards();
  }

  @GetMapping("card/no-etc")
  public @ResponseBody List<CardInfo> getAllCardsNotETC() {
    return cardInformationService.findAllCardsNotETC();
  }

  @GetMapping("card/cost")
  public @ResponseBody List<CardInfo> getAllCardsOrderByCost() {
    return cardInformationService.findAllCardsOrderByCost();
  }

  @GetMapping("/card/new")
  public String addNewCardForm() {
    return "card/createCardInfoForm";
  }

  @PostMapping("/card/new")
  public String addNewCard(CardForm cardForm) {

    CardDTO cardDTO = new CardDTO(cardForm.getName(), cardForm.getSeries(), Byte.parseByte(cardForm.getCost()), Byte.parseByte(cardForm.getPower()),
            cardForm.getDescription(), cardForm.getOnReveal(), cardForm.getOngoing(), cardForm.getNoAbility());

    cardInformationService.join(cardDTO);

    return "redirect:/";
  }
}

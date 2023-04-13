package snap.deckBuilder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import snap.deckBuilder.service.DeckMakerService;

@Controller
public class DeckController {

  private final DeckMakerService deckMakerService;

  @Autowired
  public DeckController(DeckMakerService deckMakerService) {
    this.deckMakerService = deckMakerService;
  }
}

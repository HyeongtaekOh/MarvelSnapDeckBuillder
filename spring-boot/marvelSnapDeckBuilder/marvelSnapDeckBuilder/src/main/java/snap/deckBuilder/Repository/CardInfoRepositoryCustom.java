package snap.deckBuilder.repository;

import snap.deckBuilder.domain.CardInfo;

public interface CardInfoRepositoryCustom {

  CardInfo saveNewCard(CardInfo card);
}

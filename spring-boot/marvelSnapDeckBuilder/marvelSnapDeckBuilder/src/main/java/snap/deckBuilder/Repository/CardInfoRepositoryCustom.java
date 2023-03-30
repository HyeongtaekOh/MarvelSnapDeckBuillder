package snap.deckBuilder.Repository;

import snap.deckBuilder.domain.CardInfo;

public interface CardInfoRepositoryCustom {

  CardInfo saveNewCard(CardInfo card);
}

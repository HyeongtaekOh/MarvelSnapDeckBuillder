package snap.deckBuilder.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import snap.deckBuilder.domain.CardInfo;

@Repository
public class CardInfoRepositoryImpl implements CardInfoRepositoryCustom{

  @PersistenceContext
  private EntityManager entityManager;


  @Override
  public CardInfo saveNewCard(CardInfo card) {
    entityManager.persist(card.getCardCondition());
    entityManager.persist(card);

    return card;
  }
}

package snap.deckBuilder.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import snap.deckBuilder.Repository.CardConditionRepository;
import snap.deckBuilder.domain.CardCondition;

import java.util.Optional;

@SpringBootTest
@Transactional
class CardConditionRepositoryTest {

  @Autowired
  CardConditionRepository cardConditionRepository;

  @Test
  void saveCondition() {

    // given

    CardCondition cardCondition = CardCondition.createCardCondition("Y", "Y", "Y");

    // when

    CardCondition card = cardConditionRepository.save(cardCondition);

    //then

    Long cardId = card.getCardId();
    Optional<CardCondition> selectedCardCondition = cardConditionRepository.findById(cardId);
    Assertions.assertTrue(selectedCardCondition.isPresent());
    CardCondition selectedCard = selectedCardCondition.get();
    Assertions.assertEquals(selectedCard, cardCondition);

  }
}
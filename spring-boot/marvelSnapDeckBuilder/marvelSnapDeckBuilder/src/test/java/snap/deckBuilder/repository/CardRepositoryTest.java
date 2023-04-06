package snap.deckBuilder.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import snap.deckBuilder.domain.CardCondition;
import snap.deckBuilder.domain.CardInfo;

import java.util.Optional;

@SpringBootTest
@Transactional
public class CardRepositoryTest {

  @Autowired
  CardInfoRepository cardInfoRepository;

  @Autowired
  CardConditionRepository cardConditionRepository;

  @Test
  void findByIdFromCardInfo() {

    // given

    Long cardId = 1L;
    String series = "5";
    Byte cost = 5;
    Byte power = 8;

    // when
    Optional<CardInfo> cardInfo = cardInfoRepository.findById(cardId);

    // then
    Assertions.assertTrue(cardInfo.isPresent());
    CardInfo card = cardInfo.get();
    Assertions.assertEquals(series, card.getSeries());
    Assertions.assertEquals(cost, card.getCost());
    Assertions.assertEquals(power, card.getPower());
  }

  @Test
  void findByIdFromCardCondition() {

    // given

    Long cardId = 1L;
    Boolean onReveal = Boolean.TRUE;
    Boolean ongoing = Boolean.TRUE;
    Boolean noAbility = Boolean.TRUE;

    // when

    Optional<CardCondition> cardCondition = cardConditionRepository.findById(cardId);

    // then

    Assertions.assertTrue(cardCondition.isPresent());
    CardCondition card = cardCondition.get();
    Assertions.assertTrue(card.hasOnReveal());
    Assertions.assertFalse(card.hasOngoing());
    Assertions.assertFalse(card.hasNoAbility());
  }

  @Test
  void saveCardInfoWithCardCondition() {

    // given


    // when



    // then


  }
}

package snap.deckBuilder.Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import snap.deckBuilder.Repository.CardInfoRepository;
import snap.deckBuilder.domain.CardCondition;
import snap.deckBuilder.domain.CardDTO;
import snap.deckBuilder.domain.CardInfo;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CardInformationServiceTest {

  @Autowired CardInformationService cardInformationService;
  @Autowired CardInfoRepository cardInfoRepository;

  @Test
  void findAllCardsNotETC() {
    // given
    int totalCount = 233;
    int etcCount = 25;
    // when
    List<CardInfo> allCardsNotETC = cardInformationService.findAllCardsNotETC();
    // then
    Assertions.assertEquals(totalCount - etcCount, allCardsNotETC.size());
  }

  @Test
  void findAllCards() {

    // given
    int totalCount = 233;

    // when
    List<CardInfo> allCards = cardInformationService.findAllCards();
    // then
    Assertions.assertEquals(totalCount, allCards.size());
  }

  @Test
  void saveCardInfoWithCardCondition() {

    // given

    CardInfo cardInfo = CardInfo.createCardInfo("TESTCARD", "5",
            (byte) 7, (byte) 7, "조인 테스트 실행 결과입니다.");
    CardCondition cardCondition = CardCondition.createCardCondition("Y", "Y", "Y");
    cardInfo.allocateConditions(cardCondition);
    CardDTO cardDTO = CardDTO.from(cardInfo);

    // when

    Long cardId = cardInformationService.join(cardDTO);

    // then

    Optional<CardInfo> cardById = cardInfoRepository.findById(cardId);
    assertTrue(cardById.isPresent());
    CardInfo searchCard = cardById.get();
    assertEquals(searchCard.getCardId(), searchCard.getCardCondition().getCardId());
    assertEquals(searchCard, cardInfo);
  }

  @Test
  void updateCardInfoAndCondition() {

    // given

    CardInfo cardInfo = CardInfo.createCardInfo("TEST", "5",
            (byte) 7, (byte) 7, "조인 테스트 실행 결과입니다.");
    CardCondition cardCondition = CardCondition.createCardCondition("Y", "Y", "Y");
    cardInfo.allocateConditions(cardCondition);
    CardDTO cardDTO = CardDTO.from(cardInfo);

    CardInfo updatedCardInfo = CardInfo.createCardInfo("업데이트 테스트", "4",
            (byte) 6, (byte) 7, "업데이트 테스트 실행 결과입니다.");
    CardCondition updatedCardCondition = CardCondition.createCardCondition("Y", "Y", "N");
    updatedCardInfo.allocateConditions(updatedCardCondition);
    CardDTO updatedCardDTO = CardDTO.from(updatedCardInfo);

    // when

    Long id = cardInformationService.join(cardDTO);
    Optional<CardInfo> byId = cardInfoRepository.findById(id);
    assertTrue(byId.isPresent());
    CardInfo temp = byId.get();
    System.out.println("변경 전 : " + temp);
    cardInformationService.updateById(id, updatedCardDTO);

    //then

    Optional<CardInfo> find = cardInfoRepository.findById(id);
    assertTrue(find.isPresent());
    CardInfo updatedCard = find.get();
    System.out.println("변경 후 : " + updatedCard);
    System.out.println("변경 후 테스트 엔티티 : " + updatedCardDTO.toEntity());
    assertEquals(id, updatedCard.getCardId());
    assertEquals(updatedCardDTO.toEntity(), updatedCard);

  }
}
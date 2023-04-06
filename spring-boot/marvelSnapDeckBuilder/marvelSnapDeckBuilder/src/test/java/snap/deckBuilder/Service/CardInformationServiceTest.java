package snap.deckBuilder.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import snap.deckBuilder.repository.CardInfoRepository;
import snap.deckBuilder.domain.CardCondition;
import snap.deckBuilder.domain.CardDTO;
import snap.deckBuilder.domain.CardInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CardInformationServiceTest {

  @Autowired CardInformationService cardInformationService;
  @Autowired CardInfoRepository cardInfoRepository;

  @Test
  void findCardsNotETC() {
    // given
    int totalCount = 233;
    int etcCount = 25;
    // when
    List<CardInfo> allCardsNotETC = cardInformationService.findCardsNotETCOrderByName();
    // then
    Assertions.assertEquals(totalCount - etcCount, allCardsNotETC.size());
  }

  @Test
  void findAllCards() {

    // given
    int totalCount = 233;

    // when
    List<CardInfo> allCards = cardInformationService.findAllCardsOrderByName();
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
    cardInformationService.updateById(id, updatedCardDTO);

    //then

    Optional<CardInfo> find = cardInfoRepository.findById(id);
    assertTrue(find.isPresent());
    CardInfo updatedCard = find.get();
    assertEquals(id, updatedCard.getCardId());
    assertEquals(updatedCardDTO.toEntity(), updatedCard);

  }

  @Test
  void findCardsNoAbility() {

    // given

    long[] cardIds = {16, 24, 31, 34, 37, 63, 71, 72, 75, 83, 95, 109, 116, 125, 127, 128, 142, 156, 165, 171, 201, 215};

    // when

    List<CardInfo> cardList = cardInformationService.findCardsNoAbility();

    // then

    assertEquals(cardIds.length, cardList.size());

    boolean allCardIdsFound = cardList.stream()
            .mapToLong(CardInfo::getCardId)
            .allMatch(id -> Arrays.stream(cardIds).anyMatch(cardId -> cardId == id));

    assertTrue(allCardIdsFound);

    for (int i = 0; i < cardList.size(); i++) {
      assertEquals(cardIds[i], cardList.get(i).getCardId());
    }
  }

  @Test
  void findCardsWithPagingAndSorting() {

    /* Test 1 */

    // given

    long[] cardIds = {1,2,3,4,227,5,6,7,8,9,10,11,12,232,13,14,15,16,230,17};

    // when

    Sort sort = Sort.by("name");
    Pageable pageable = PageRequest.of(0, 20, sort);
    List<CardInfo> cardList = cardInformationService.findCardsWithPagingAndSorting(pageable);

    // then

    assertEquals(cardIds.length, cardList.size());

    boolean allCardIdsFound = cardList.stream()
            .mapToLong(CardInfo::getCardId)
            .allMatch(id -> Arrays.stream(cardIds).anyMatch(cardId -> cardId == id));

    assertTrue(allCardIdsFound);

    for (int i = 0; i < cardList.size(); i++) {
      assertEquals(cardIds[i], cardList.get(i).getCardId());
    }


    /* Test 2 */

    // given

    long[] cardIds2 = {169,35,38,9,140,27,54,215,200,20,110,137,203,219,63,132,142,152,1,84};

    // when

    Sort sort2 = Sort.by("power").descending().and(Sort.by("name"));
    Pageable pageable2 = PageRequest.of(0, 20, sort2);
    List<CardInfo> cardList2 = cardInformationService.findCardsWithPagingAndSorting(pageable2);

    // then

    assertEquals(cardIds2.length, cardList2.size());

    for (int i = 0; i < cardList2.size(); i++) {
      assertEquals(cardIds2[i], cardList2.get(i).getCardId());
    }


    /* Test 2 */

    // given

    long[] cardIds3 = {132, 142, 152, 1, 84, 105, 122, 138, 151, 159, 176, 194, 217, 222, 2};

    // when

    Sort sort3 = Sort.by("power").descending().and(Sort.by("name"));
    Pageable pageable3 = PageRequest.of(1, 15, sort3);
    List<CardInfo> cardList3 = cardInformationService.findCardsWithPagingAndSorting(pageable3);

    // then

    assertEquals(cardIds3.length, cardList3.size());

    for (int i = 0; i < cardList3.size(); i++) {
      assertEquals(cardIds3[i], cardList3.get(i).getCardId());
    }

  }
}
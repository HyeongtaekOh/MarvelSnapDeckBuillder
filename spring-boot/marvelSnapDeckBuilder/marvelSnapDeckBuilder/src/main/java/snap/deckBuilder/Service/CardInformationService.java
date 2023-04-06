package snap.deckBuilder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.deckBuilder.repository.CardConditionRepository;
import snap.deckBuilder.repository.CardInfoRepository;
import snap.deckBuilder.domain.CardDTO;
import snap.deckBuilder.domain.CardInfo;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CardInformationService {

  private final CardInfoRepository cardInfoRepository;
  private final CardConditionRepository cardConditionRepository;

  @Autowired
  public CardInformationService(CardInfoRepository cardInfoRepository, CardConditionRepository cardConditionRepository) {
    this.cardInfoRepository = cardInfoRepository;
    this.cardConditionRepository = cardConditionRepository;
  }

  @Transactional
  public Long join(CardDTO cardDTO) {

    CardInfo card = cardInfoRepository.save(cardDTO.toEntity());

    return card.getCardId();
  }

  @Transactional
  public void updateById(Long id, CardDTO cardDTO) {

    CardInfo cardInfo = cardInfoRepository.findById(id).orElseThrow(
            NoSuchElementException::new);

    cardInfo.updateCardInfoAndCondition(cardDTO);
  }

  /* search cards */
  public List<CardInfo> findAllCardsOrderByCost() {
    return cardInfoRepository.findByOrderByCost();
  }

  public List<CardInfo> findCardsNotETCOrderByName() {
    return cardInfoRepository.findBySeriesNotOrderByName("etc");
  }

  public List<CardInfo> findAllCardsOrderByName() {
    return cardInfoRepository.findByOrderByName();
  }

  public List<CardInfo> findCardsNoAbility() {
    return cardInfoRepository.findByCardConditionNoAbility("Y");
  }

  /* search cards with paging and sorting */

  public List<CardInfo> findCardsWithPagingAndSorting(Pageable pageable) {
    return cardInfoRepository.findBy(pageable);
  }
}

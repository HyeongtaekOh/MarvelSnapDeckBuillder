package snap.deckBuilder.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.deckBuilder.Repository.CardConditionRepository;
import snap.deckBuilder.Repository.CardInfoRepository;
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

  public List<CardInfo> findAllCardsNotETC() {
    return cardInfoRepository.findAllBySeriesNotOrderByName("etc");
  }

  public List<CardInfo> findAllCards() {
    return cardInfoRepository.findAllByOrderByName();
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

  public List<CardInfo> findAllCardsOrderByCost() {
    return cardInfoRepository.findAllByOrderByCost();
  }
}

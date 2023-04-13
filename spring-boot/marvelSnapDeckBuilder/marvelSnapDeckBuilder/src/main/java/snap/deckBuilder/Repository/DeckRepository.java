package snap.deckBuilder.repository;

import org.springframework.data.repository.Repository;
import snap.deckBuilder.domain.CardInfo;
import snap.deckBuilder.domain.Deck;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface DeckRepository extends Repository<Deck, Long> {

  Optional<Deck> findByName(String name);

  List<Deck> findDecksByCards(Collection<Long> cardIds);

  List<Deck> findDecksByCard(Long cardId);

}

package snap.deckBuilder.Repository;

import org.springframework.data.repository.CrudRepository;
import snap.deckBuilder.domain.CardCondition;

public interface CardConditionRepository extends CrudRepository<CardCondition, Long> {


}

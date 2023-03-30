package snap.deckBuilder.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import snap.deckBuilder.domain.CardInfo;

import java.util.List;

public interface JpaCardRepository extends JpaRepository<CardInfo, Long> {

  @Query("select ci from CardInfo ci where ci.series <> 'etc' order by ci.name asc")
  List<CardInfo> findAllBySeriesNotETCOrderByName();

  /* Order By Methods */
  List<CardInfo> findAllByOrderByName();

  List<CardInfo> findAllBySeriesNotOrderByName(String series);

  List<CardInfo> findAllByCostEquals(Byte cost);

  List<CardInfo> findAllByNameContaining(String name);
}

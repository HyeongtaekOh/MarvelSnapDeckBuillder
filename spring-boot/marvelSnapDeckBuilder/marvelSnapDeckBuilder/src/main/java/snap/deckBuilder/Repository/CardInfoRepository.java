package snap.deckBuilder.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.deckBuilder.domain.CardInfo;

import java.util.List;

public interface CardInfoRepository extends JpaRepository<CardInfo, Long>, CardInfoRepositoryCustom {

//  @Query("select ci from CardInfo ci where ci.series <> 'etc' order by ci.name asc")
//  List<CardInfo> findAllBySeriesNotETCOrderByName();

  List<CardInfo> findAllByOrderByName();

  List<CardInfo> findAllBySeriesNotOrderByName(String series);

  List<CardInfo> findAllByCostEquals(Byte cost);

  List<CardInfo> findAllByNameContaining(String name);

  List<CardInfo> findAllByOrderByCost();
}

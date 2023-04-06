package snap.deckBuilder.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import snap.deckBuilder.domain.CardInfo;

import java.util.List;

public interface CardInfoRepository extends JpaRepository<CardInfo, Long>, CardInfoRepositoryCustom {

//  @Query("select ci from CardInfo ci where ci.series <> 'etc' order by ci.name asc")
//  List<CardInfo> findAllBySeriesNotETCOrderByName();

  List<CardInfo> findByOrderByName();

  List<CardInfo> findBySeriesNotOrderByName(String series);

  List<CardInfo> findByCostEquals(Byte cost);

  List<CardInfo> findByNameContaining(String name);

  List<CardInfo> findByOrderByCost();

  List<CardInfo> findByCardConditionNoAbility(String noAbility);

  List<CardInfo> findBy(Pageable pageable);
}

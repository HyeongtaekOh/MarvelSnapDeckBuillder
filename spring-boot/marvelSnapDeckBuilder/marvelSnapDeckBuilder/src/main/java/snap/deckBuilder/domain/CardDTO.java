package snap.deckBuilder.domain;

public class CardDTO {

  public final String name;
  public final String series;
  public final Byte cost;
  public final Byte power;
  public final String description;
  public final String onReveal;
  public final String ongoing;
  public final String noAbility;

  public CardDTO(String name, String series, Byte cost, Byte power, String description,
                 String onReveal, String ongoing, String noAbility) {
    this.name = name;
    this.series = series;
    this.cost = cost;
    this.power = power;
    this.description = description;
    this.onReveal = onReveal;
    this.ongoing = ongoing;
    this.noAbility = noAbility;
  }

  public CardInfo toEntity() {

    CardInfo card = CardInfo.createCardInfo(name, series, cost, power, description);
    card.allocateConditions(CardCondition.createCardCondition(onReveal, ongoing, noAbility));

    return card;
  }

  public static CardDTO from(CardInfo cardInfo) {

    CardCondition condition = cardInfo.getCardCondition();

    return new CardDTO(cardInfo.getName(), cardInfo.getSeries(), cardInfo.getCost(), cardInfo.getPower(),
            cardInfo.getDescription(), condition.getOnReveal(), condition.getOngoing(), condition.getNoAbility());
  }
}

package snap.deckBuilder.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Objects;

@Entity
@DynamicUpdate
public class CardInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cardId;

  @Column(name = "NAME")
  private String name;

  @Column(name = "SERIES")
  private String series;

  @Column(name = "COST")
  private Byte cost;

  @Column(name = "POWER")
  private Byte power;

  @Column(name = "DESCRIPTION")
  private String description;

  @JsonIgnore
  @OneToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "CARD_ID")
  private CardCondition cardCondition;

  protected CardInfo() {}

  private CardInfo(String name, String series, Byte cost, Byte power, String description) {
    this.name = name;
    this.series = series;
    this.cost = cost;
    this.power = power;
    this.description = description;
  }

  public static CardInfo createCardInfo(String name, String series, Byte cost, Byte power, String description) {
    return new CardInfo(name, series, cost, power, description);
  }

  public Long getCardId() {
    return cardId;
  }

  public String getName() {
    return name;
  }

  public String getSeries() {
    return series;
  }

  public Byte getCost() {
    return cost;
  }

  public Byte getPower() {
    return power;
  }

  public String getDescription() {
    return description;
  }

  public CardCondition getCardCondition() {
    return cardCondition;
  }

  public void allocateConditions(CardCondition cardCondition) {
    this.cardCondition = cardCondition;
  }

  public void updateCardInfoAndCondition(CardDTO cardDTO) {
    updateCardInfo(cardDTO.name, cardDTO.series, cardDTO.cost, cardDTO.power, cardDTO.description);
    cardCondition.updateCardCondition(cardDTO.onReveal, cardDTO.ongoing, cardDTO.noAbility);
  }

  private void updateCardInfo(String name, String series, Byte cost, Byte power, String description) {
    this.name = name;
    this.series = series;
    this.cost = cost;
    this.power = power;
    this.description = description;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || Hibernate.getClass(this) != Hibernate.getClass(obj))
      return false;
    CardInfo card = (CardInfo) obj;
    return Objects.equals(name, card.getName()) &&
            Objects.equals(series, card.getSeries()) &&
            Objects.equals(cost, card.getCost()) &&
            Objects.equals(power, card.getPower()) &&
            Objects.equals(description, card.getDescription()) &&
            cardCondition.equals(card.getCardCondition());
  }

  @Override
  public String toString() {
    return "CardInfo{" +
            "cardId=" + cardId +
            ", name='" + name + '\'' +
            ", series='" + series + '\'' +
            ", cost=" + cost +
            ", power=" + power +
            ", description='" + description + '\'' +
            ", cardCondition=" + cardCondition +
            '}';
  }
}

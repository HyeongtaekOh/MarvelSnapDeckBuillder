package snap.deckBuilder.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Objects;

@Getter
@Entity
@DynamicUpdate
public class CardInfo implements Comparable<CardInfo>{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CARD_ID")
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
  public int compareTo(CardInfo c) {
    if (cost > c.getCost()) {
      return 1;
    } else if (cost < c.getCost()) {
      return -1;
    } else {
      return name.compareTo(c.getName());
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || Hibernate.getClass(this) != Hibernate.getClass(obj))
      return false;
    CardInfo card = (CardInfo) obj;
    return Objects.equals(cardId, card.getCardId()) &&
            Objects.equals(name, card.getName()) &&
            Objects.equals(series, card.getSeries()) &&
            Objects.equals(cost, card.getCost()) &&
            Objects.equals(power, card.getPower()) &&
            Objects.equals(description, card.getDescription()) &&
            Objects.equals(cardCondition, card.getCardCondition());
  }

  @Override
  public int hashCode() {
    int h = 0;

    h += cardId.hashCode();
    h = 31 * h + name.hashCode();
    h = 31 * h + series.hashCode();
    h = 31 * h + cost.hashCode();
    h = 31 * h + power.hashCode();
    h = 31 * h + description.hashCode();

    return h;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("CardInfo{");
    sb.append("cardId=").append(cardId);
    sb.append(", name='").append(name).append("'");
    sb.append(", series='").append(series).append("'");
    sb.append(", cost='").append(cost).append("'");
    sb.append(", power='").append(power).append("'");
    sb.append(", description='").append(description).append("'");
    sb.append(", cardCondition=").append(cardCondition.toString()).append("}");

    return  sb.toString();
  }
}

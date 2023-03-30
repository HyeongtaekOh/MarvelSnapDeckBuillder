package snap.deckBuilder.domain;

import jakarta.persistence.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnTransformer;

import java.util.Objects;

@Entity
public class CardConditionTemp {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cardId;

  @Column(name = "ON_REVEAL")
  @ColumnTransformer(read = "CASE WHEN ON_REVEAL = 'Y' THEN TRUE ELSE FALSE END",
          write = "CASE WHEN ? THEN 'Y' ELSE 'N' END")
  private Boolean onReveal;

  @Column(name = "ONGOING")
  @ColumnTransformer(read = "CASE WHEN ONGOING = 'Y' THEN TRUE ELSE FALSE END",
          write = "CASE WHEN ? THEN 'Y' ELSE 'N' END")
  private Boolean ongoing;

  @Column(name = "NO_ABILITY")
  @ColumnTransformer(read = "CASE WHEN NO_ABILITY = 'Y' THEN TRUE ELSE FALSE END",
          write = "CASE WHEN ? THEN 'Y' ELSE 'N' END")
  private Boolean noAbility;

  public CardConditionTemp() {

  }

  private CardConditionTemp(Boolean onReveal, Boolean ongoing, Boolean noAbility) {
    this.onReveal = onReveal;
    this.ongoing = ongoing;
    this.noAbility = noAbility;
  }

  public static CardConditionTemp createCardCondition(Boolean onReveal, Boolean ongoing, Boolean noAbility) {
    return new CardConditionTemp(onReveal, ongoing, noAbility);
  }

  public Long getCardId() {
    return cardId;
  }

  public Boolean getOnReveal() {
    return onReveal;
  }

  public Boolean getOngoing() {
    return ongoing;
  }



  public Boolean getNoAbility() {
    return noAbility;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || Hibernate.getClass(this) != Hibernate.getClass(obj))
      return false;
    CardCondition card = (CardCondition) obj;
    return Objects.equals(cardId, card.getCardId()) &&
            Objects.equals(onReveal, card.getOnReveal()) &&
            Objects.equals(ongoing, card.getOngoing()) &&
            Objects.equals(noAbility, card.getNoAbility());
  }
}

package snap.deckBuilder.domain;

import jakarta.persistence.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Objects;

@Entity
@DynamicUpdate
public class CardCondition {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cardId;

  @Column(name = "ON_REVEAL")
  private String onReveal;

  @Column(name = "ONGOING")
  private String ongoing;

  @Column(name = "NO_ABILITY")
  private String noAbility;

  public CardCondition() {

  }

  private CardCondition(String onReveal, String ongoing, String noAbility) {
    this.onReveal = onReveal;
    this.ongoing = ongoing;
    this.noAbility = noAbility;
  }

  public static CardCondition createCardCondition(String onReveal, String ongoing, String noAbility) {
    return new CardCondition(onReveal, ongoing, noAbility);
  }

  public void updateCardCondition(String onReveal, String ongoing, String noAbility) {
    this.onReveal = onReveal;
    this.ongoing = ongoing;
    this.noAbility = noAbility;
  }

  public Long getCardId() {
    return cardId;
  }

  public String getOnReveal() {
    return onReveal;
  }

  public String getOngoing() {
    return ongoing;
  }

  public String getNoAbility() {
    return noAbility;
  }

  public boolean hasOnReveal() {
    return onReveal.equals("Y");
  }

  public boolean hasOngoing() {
    return ongoing.equals("Y");
  }

  public boolean hasNoAbility() {
    return noAbility.equals("Y");
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || Hibernate.getClass(this) != Hibernate.getClass(obj))
      return false;
    CardCondition card = (CardCondition) obj;
    return Objects.equals(onReveal, card.getOnReveal()) &&
            Objects.equals(ongoing, card.getOngoing()) &&
            Objects.equals(noAbility, card.getNoAbility());
  }

  @Override
  public String toString() {
    return "CardCondition{" +
            "cardId=" + cardId +
            ", onReveal='" + onReveal + '\'' +
            ", ongoing='" + ongoing + '\'' +
            ", noAbility='" + noAbility + '\'' +
            '}';
  }
}

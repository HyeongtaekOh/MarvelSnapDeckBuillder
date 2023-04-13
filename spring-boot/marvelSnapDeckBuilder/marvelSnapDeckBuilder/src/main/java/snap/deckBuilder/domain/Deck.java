package snap.deckBuilder.domain;


import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.Hibernate;

import java.util.*;

@Getter
@Entity
@Table(name = "DECKS")
public class Deck {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "DECK_ID")
  private Long deckId;

  @Column
  private String name;

  @ManyToMany
  @JoinTable(
          name = "DECK_CARD",
          joinColumns = @JoinColumn(name = "DECK_ID"),
          inverseJoinColumns = @JoinColumn(name = "CARD_ID"))
  private Set<CardInfo> cards = new TreeSet<>();

  protected Deck() {

  }

  public static Optional<Deck> buildWith(Collection<CardInfo> cardInfos) {

    Deck deck = new Deck();

    if (cardInfos.size() == 12 && deck.getCards().addAll(cardInfos)) {
      return Optional.of(deck);
    } else {
      return Optional.empty();
    }
  }

  public boolean have(CardInfo card) {
    return cards.contains(card);
  }

  public boolean rebuildDeckWith(Collection<CardInfo> cardInfos) {

    if (cardInfos.size() != 12) {
      return false;
    }

    if (!cards.isEmpty()) {
      cards.clear();
    }

    return cards.addAll(cardInfos);
  }



  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || Hibernate.getClass(this) != Hibernate.getClass(obj))
      return false;
    Deck deck = (Deck) obj;

    return Objects.equals(name, deck.getName()) &&
            Objects.equals(cards, deck.getCards());
  }

  @Override
  public int hashCode() {
    int h = 0;

    h += deckId.hashCode();

    for (CardInfo card : cards) {
      h = 31 * h + card.hashCode();
    }

    return h;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("Deck Name : ").append("\n");

    for (CardInfo card : cards) {
      sb.append(String.format("[%d] ", card.getCost()));
      sb.append(card.getName()).append("\n");
    }

    return sb.toString();
  }

}

package snap.deckBuilder.Controller;

public class CardForm {

  private String name;
  private String series;
  private String cost;
  private String power;
  private String description;
  private String onReveal;
  private String ongoing;
  private String noAbility;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSeries() {
    return series;
  }

  public void setSeries(String series) {
    this.series = series;
  }

  public String getCost() {
    return cost;
  }

  public void setCost(String cost) {
    this.cost = cost;
  }

  public String getPower() {
    return power;
  }

  public void setPower(String power) {
    this.power = power;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getOnReveal() {
    return onReveal;
  }

  public void setOnReveal(String onReveal) {
    this.onReveal = onReveal;
  }

  public String getOngoing() {
    return ongoing;
  }

  public void setOngoing(String ongoing) {
    this.ongoing = ongoing;
  }

  public String getNoAbility() {
    return noAbility;
  }

  public void setNoAbility(String noAbility) {
    this.noAbility = noAbility;
  }
}

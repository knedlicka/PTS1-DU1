public class Card {
    private final CardType type;
    private final Integer value;

    public Card(CardType type, Integer value) {
        this.type = type;
        this.value = value;
    }

    public CardType getType() {
        return type;
    }

    public Integer getValue() {
        return value;
    }
}

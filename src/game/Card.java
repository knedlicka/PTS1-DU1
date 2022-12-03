package game;

import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Card other = (Card) obj;
        if (this.type != other.type) {
            return false;
        }
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        return true;
    }
}

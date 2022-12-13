package game;

import java.util.Objects;

public class AwokenQueenPosition implements IPosition {
    private final Integer cardIndex;
    private final Integer playerIndex;

    public AwokenQueenPosition(Integer cardIndex, Integer playerIndex) {
        this.cardIndex = cardIndex;
        this.playerIndex = playerIndex;
    }
    @Override
    public Integer getCardIndex() {
        return cardIndex;
    }

    public Integer getPlayerIndex() {
        return playerIndex;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AwokenQueenPosition other = (AwokenQueenPosition) obj;
        if (!Objects.equals(this.cardIndex, other.cardIndex)) {
            return false;
        }
        if (!Objects.equals(this.playerIndex, other.playerIndex)) { //REVIEW: this if can be simplified to
            return false;                                           // return Objects.equals(this.playerIndex, other.playerIndex);
        }
        return true;
    }

    @Override
    public String toString() {
        return "AwokenQueenPosition{" + "cardIndex=" + cardIndex + ", playerIndex=" + playerIndex + '}';
    }
}

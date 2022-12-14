package game;

import java.util.Objects;

public class SleepingQueenPosition implements IPosition {
    private final Integer cardIndex;

    public SleepingQueenPosition(Integer cardIndex) {
        this.cardIndex = cardIndex;
    }
    @Override
    public Integer getCardIndex() {
        return cardIndex;
    }
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SleepingQueenPosition other = (SleepingQueenPosition) obj;
        return Objects.equals(this.cardIndex, other.cardIndex);
    }

    @Override
    public String toString() {
        return "SleepingQueenPosition{" + "cardIndex=" + cardIndex + '}';
    }
}

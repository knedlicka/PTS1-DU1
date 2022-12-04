package game;

public class HandPosition implements IPosition {
    private final Integer cardIndex;
    private final Integer playerIndex;

    public HandPosition(Integer cardIndex, Integer playerIndex) {
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HandPosition other = (HandPosition) obj;
        return this.cardIndex.equals(other.cardIndex) && this.playerIndex.equals(other.playerIndex);
    }
}

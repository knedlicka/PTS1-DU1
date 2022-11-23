public class AwokenQueenPosition implements Position{
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
}

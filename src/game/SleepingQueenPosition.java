package game;

public class SleepingQueenPosition implements Position {
    private final Integer cardIndex;

    public SleepingQueenPosition(Integer cardIndex) {
        this.cardIndex = cardIndex;
    }
    @Override
    public Integer getCardIndex() {
        return cardIndex;
    }
}

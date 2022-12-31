package game;

import java.util.ArrayList;
import java.util.List;

public class EvaluateNumberedCards implements IEvaluateNumberedCards{
    private final IHand hand;

    public EvaluateNumberedCards(IHand hand) {
        this.hand = hand;
    }

    private boolean canBeDiscarded(List<Card> cards) {
        if(cards.stream().filter(card -> card.getType().equals(CardType.Number)).count() != cards.size()) {
            return false;
        }
        if(cards.size() == 1) {
            return true;
        }
        List<Card> newList = new ArrayList<Card>(cards);
        int sum = newList.stream().mapToInt(Card::getValue).sum();
        return newList.stream().anyMatch(card -> sum - card.getValue() == card.getValue());
    }

    public boolean play(List<Card> cards, List<IPosition> handPositions) {
        if (canBeDiscarded(cards)) {
            if(handPositions.size() != cards.size()) {
                return false;
            }
            hand.pickCards(handPositions);
            hand.removePickedCardsAndRedraw();
            return true;
        }
        return false;
    }
}

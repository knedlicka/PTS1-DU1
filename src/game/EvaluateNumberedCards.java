package game;

import java.util.ArrayList;
import java.util.List;

public class EvaluateNumberedCards implements IEvaluateNumberedCards{
    private final IHand hand;

    private final Integer playerIndex;

    public EvaluateNumberedCards(IHand hand, Integer playerIndex) {
        this.hand = hand;
        this.playerIndex = playerIndex;
    }

    private boolean canBeDiscarded(List<Card> cards) {
        for(Card card : cards) {
            if(!card.getType().equals(CardType.Number)) {
                return false;
            }
        }

        if(cards.size() == 1) {
            return true;
        }

        for (int i = 0; i < cards.size(); i++) {
            int sum = 0;
            for (int j = 0; j < cards.size(); j++) {
                if (i != j) {
                    sum += cards.get(j).getValue();
                }
            }
            if (sum == cards.get(i).getValue()) {
                return true;
            }
        }
        return false;
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

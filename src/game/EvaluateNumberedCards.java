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

    public boolean play(List<Card> cards) { // TODO nemusi vyhodit kartu na tej pozicii, ktoru si vybral hrac, ak ma na ruke aspon dve rovnake karty
        if (canBeDiscarded(cards)) {
            List<IPosition> handPositions = new ArrayList<>();
            List<Card> handCards = hand.getCards();
            for (Card card : cards) {
                for (int i = 0; i < handCards.size(); i++) {
                    if (card.equals(handCards.get(i))) {
                        HandPosition hp = new HandPosition(i, playerIndex);
                        handPositions.add(hp);
                    }
                }
            }
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

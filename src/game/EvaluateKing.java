package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EvaluateKing implements IEvaluateKing {
    private static IQueenCollection awokenQueens;
    private static IQueenCollection sleepingQueens;
    private static IHand hand;

    public EvaluateKing(IQueenCollection awokenQueens, IHand hand) {
        EvaluateKing.awokenQueens = awokenQueens;
        EvaluateKing.hand = hand;
    }

    public boolean play(HandPosition handPosition, SleepingQueenPosition sleepingQueenPosition) {
        Card king = hand.getCards().get(handPosition.getCardIndex());
        if (!king.getType().equals(CardType.King)) {
            return false;
        }

        Map<IPosition, Queen> allSleeping = sleepingQueens.getQueens();
        if(!allSleeping.containsKey(sleepingQueenPosition)) {
            return false;
        }

        List<IPosition> handPositions = new ArrayList<>();
        handPositions.add(handPosition);
        hand.pickCards(handPositions);
        hand.removePickedCardsAndRedraw();

        sleepingQueens.removeQueen(sleepingQueenPosition);
        awokenQueens.addQueen(allSleeping.get(sleepingQueenPosition));

        return true;

    }
}

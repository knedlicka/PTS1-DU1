import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EvaluateKing {
    private static AwokenQueens awokenQueens;
    private static SleepingQueens sleepingQueens;
    private static IHand hand;

    public EvaluateKing(AwokenQueens awokenQueens, IHand hand) {
        EvaluateKing.awokenQueens = awokenQueens;
        EvaluateKing.hand = hand;
    }

    public boolean play(HandPosition handPosition, SleepingQueenPosition sleepingQueenPosition) {
        Card king = hand.getCards().get(handPosition.getCardIndex());
        if (!king.getType().equals(CardType.King)) {
            return false;
        }

        Map<Position, Queen> allSleeping = sleepingQueens.getQueens();
        if(!allSleeping.containsKey(sleepingQueenPosition)) {
            return false;
        }

        List<HandPosition> handPositions = new ArrayList<>();
        handPositions.add(handPosition);
        hand.pickCards(handPositions);
        hand.removePickedCardsAndRedraw();

        sleepingQueens.removeQueen(sleepingQueenPosition);
        awokenQueens.addQueen(allSleeping.get(sleepingQueenPosition));

        return true;

    }
}

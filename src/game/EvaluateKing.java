package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EvaluateKing implements IEvaluateKing {
    private final IQueenCollection awokenQueens;
    private final IQueenCollection sleepingQueens;
    private final IHand hand;

    public EvaluateKing(IQueenCollection awokenQueens, IHand hand, IQueenCollection sleepingQueens) {
        this.awokenQueens = awokenQueens;
        this.hand = hand;
        this.sleepingQueens = sleepingQueens;
    }

    public boolean play(HandPosition handPosition, IPosition sleepingQueenPosition) {
        Card king = hand.getCards().get(handPosition.getCardIndex());
        if (!king.getType().equals(CardType.King)) {
            return false;
        }

        Map<IPosition, Queen> allSleeping = sleepingQueens.getQueens();
        boolean contains = false;
        for(IPosition p: allSleeping.keySet()) {
            if(p.equals(sleepingQueenPosition)) {
                contains = true;
                break;
            }
        }
        if(!contains) {
            return false;
        }

        List<IPosition> handPositions = new ArrayList<>();
        handPositions.add(handPosition);
        hand.pickCards(handPositions);
        hand.removePickedCardsAndRedraw();

        Optional<Queen> queen = sleepingQueens.removeQueen(sleepingQueenPosition);
        if(queen.isPresent()) {
            awokenQueens.addQueen(queen.get());
            return true;
        }
        return false;

    }
}

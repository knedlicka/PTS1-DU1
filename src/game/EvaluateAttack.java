package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EvaluateAttack implements IEvaluateAttack{
    private IHand targetHand;
    private CardType type; // REVIEW: this variable is never used
    private CardType defenseCardType;
    private IMoveQueen moveQueen;
    public EvaluateAttack(IHand targetHand, CardType type, CardType defenseCardType, IMoveQueen moveQueen) {
        this.targetHand = targetHand;
        this.type = type;
        this.defenseCardType = defenseCardType;
        this.moveQueen = moveQueen;
    }

    public boolean play(IPosition targetQueen, Integer targetPlayerIndex) {
        Optional<IPosition> hp = targetHand.hasCardOfType(defenseCardType);
        if(hp.isEmpty()) { // uskutocni sa utok
            moveQueen.play(targetQueen);
            return true;
        }
        List<IPosition> pick = new ArrayList<>();
        pick.add(hp.get());
        targetHand.pickCards(pick);
        targetHand.removePickedCardsAndRedraw();
        return false; // super sa ubranil (targetHand sa ubranila)
    }
}

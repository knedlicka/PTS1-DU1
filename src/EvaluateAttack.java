import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EvaluateAttack {
    private IHand targetHand;
    private CardType type;
    private CardType defenseCardType;
    private IMoveQueen moveQueen;
    public EvaluateAttack(IHand targetHand, CardType type, CardType defenseCardType, IMoveQueen moveQueen) {
        this.targetHand = targetHand;
        this.type = type;
        this.defenseCardType = defenseCardType;
        this.moveQueen = moveQueen;
    }

    boolean play(Position targetQueen, Integer targetPlayerIndex) {
        Optional<HandPosition> hp = targetHand.hasCardOfType(defenseCardType);
        if(hp.isEmpty()) { // uskutocni sa utok
            moveQueen.play(targetQueen);
            return true;
        }
        List<HandPosition> pick = new ArrayList<>();
        pick.add(hp.get());
        targetHand.pickCards(pick);
        targetHand.removePickedCardsAndRedraw();
        return false; // super sa ubranil (targetHand sa ubranila)
    }

}

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EvaluateAttack {
    private IHand targetHand;
    private CardType type;
    private CardType defenseCardType;
    public EvaluateAttack(IHand targetHand, CardType type, CardType defenseCardType) {
        this.targetHand = targetHand;
        this.type = type;
        this.defenseCardType = defenseCardType;
    }

    boolean play(Position targetQueen, Integer targetPlayerIndex) {
        Optional<HandPosition> hp = targetHand.hasCardOfType(defenseCardType);
        if(hp.isEmpty()) { // TODO vykonat attak
            
            return true;
        }
        List<HandPosition> pick = new ArrayList<>();
        pick.add(hp.get());
        targetHand.pickCards(pick);
        targetHand.removePickedCardsAndRedraw();
        return false; //super sa ubranil (targetHand sa ubranila)
    }

}

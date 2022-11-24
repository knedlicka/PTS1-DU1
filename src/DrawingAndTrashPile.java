import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DrawingAndTrashPile {
    private List<Card> drawingPile;
    private List<Card> discardPile;
    private List<Card> discardedThisTurn;

    public DrawingAndTrashPile(List<Card> drawingPile, List<Card> discardPile) {
        this.drawingPile = drawingPile;
        this.discardPile = discardPile;
    }

    List<Card> discardAndDraw(List<Card> discard){
        discardPile.addAll(discard);
        discardedThisTurn = discard;
        int numberOfCardsToDraw = discard.size();
        List<Card> draw = new ArrayList<>();
        while(numberOfCardsToDraw > 0) {
            if(drawingPile.isEmpty()) {
                Collections.shuffle(discardPile);
                drawingPile.addAll(discardPile);
                discardPile = new ArrayList<>();
            }
            int last = drawingPile.size() - 1;
            draw.add(drawingPile.get(last));
            drawingPile.remove(last);
            numberOfCardsToDraw--;
        }
        return draw;
    }

    newTurn() {} //TODO co je toto?

    List<Card> getCardsDiscardedThisTurn() {
        return discardedThisTurn;
    }


}

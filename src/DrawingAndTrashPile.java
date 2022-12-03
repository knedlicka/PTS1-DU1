import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DrawingAndTrashPile implements IDrawingAndTrashPile{
    private final List<Card> drawingPile;
    private List<Card> discardPile;
    private List<Card> discardedThisTurn;

    private IDrawingStrategy drawingStrategy;
    public DrawingAndTrashPile(List<Card> drawingPile, List<Card> discardPile, IDrawingStrategy drawingStrategy) {
        this.drawingPile = drawingPile;
        this.discardPile = discardPile;
        this.drawingStrategy = drawingStrategy;
    }

    public List<Card> discardAndDraw(List<Card> discard){
        discardPile.addAll(discard)
        discardedThisTurn = discard;
        int numberOfCardsToDraw = discard.size();
        return drawCards(numberOfCardsToDraw);
    }

    public void newTurn() {
        discardedThisTurn = new ArrayList<>();
    }

    public List<Card> getCardsDiscardedThisTurn() {
        return discardedThisTurn;
    }

    public List<Card> drawCards(int numberOfCardsToDraw) {
        List<Card> draw = drawingStrategy.draw(this.drawingPile, this.discardPile, numberOfCardsToDraw);
        return draw;
    }
}

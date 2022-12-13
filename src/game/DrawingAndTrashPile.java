package game;

import java.util.ArrayList;
import java.util.List;

public class DrawingAndTrashPile implements IDrawingAndTrashPile{
    private final List<Card> drawingPile;
    private final List<Card> discardPile;
    private List<Card> discardedThisTurn;

    private final IDrawingStrategy drawingStrategy;
    public DrawingAndTrashPile(List<Card> drawingPile, List<Card> discardPile, IDrawingStrategy drawingStrategy) {
        this.drawingPile = drawingPile;
        this.discardPile = discardPile;
        this.drawingStrategy = drawingStrategy;
    }

    public List<Card> discardAndDraw(List<Card> discard){
        return drawingStrategy.discardAndDraw(discard, drawingPile, discardPile, new java.util.Random());
    }

    public void newTurn() {
        discardedThisTurn = new ArrayList<>();
    }

    public List<Card> getCardsDiscardedThisTurn() {
        return discardedThisTurn;
    }

    public List<Card> drawCards(int numberOfCardsToDraw) {
        List<Card> draw = drawingStrategy.draw(this.drawingPile, this.discardPile, numberOfCardsToDraw, new java.util.Random()); //REVIEW: this variable can be omitted
        return draw;                                                                                                             //draw() returned value can be directly returned
    }
}

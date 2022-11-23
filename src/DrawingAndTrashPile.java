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
        //TODO pridat draw a managovanie drawingPile
    }

    newTurn() {} //TODO co je toto?

    List<Card> getCardsDiscardedThisTurn() {
        return discardedThisTurn;
    }


}

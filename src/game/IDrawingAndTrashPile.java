package game;

import java.util.List;

public interface IDrawingAndTrashPile {
    public List<Card> discardAndDraw(List<Card> discard);
    public void newTurn();
    public List<Card> getCardsDiscardedThisTurn();
    List<Card> drawCards(int numberOfCardsToDraw);
}

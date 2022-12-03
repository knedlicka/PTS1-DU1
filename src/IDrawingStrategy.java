import java.util.List;

public interface IDrawingStrategy {
    public List<Card> discardAndDraw(List<Card> discard, List<Card> drawingPile, List<Card> discardPile);
    public List<Card> draw(List<Card> drawingPile, List<Card> discardPile, int numberOfCardsToDraw);
}

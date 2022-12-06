package game;

import java.util.List;
import java.util.Random;

public interface IDrawingStrategy {
    public List<Card> discardAndDraw(List<Card> discard, List<Card> drawingPile, List<Card> discardPile, Random rnd);
    public List<Card> draw(List<Card> drawingPile, List<Card> discardPile, int numberOfCardsToDraw, Random rnd);
}

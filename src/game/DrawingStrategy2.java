package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DrawingStrategy2 implements IDrawingStrategy{
    @Override
    public List<Card> discardAndDraw(List<Card> discard, List<Card> drawingPile, List<Card> discardPile, Random rnd) {
        int numberOfCardsToDraw = discard.size();
        List<Card> draw = draw(drawingPile, discardPile, numberOfCardsToDraw, rnd);
        discardPile.addAll(discard);
        return draw;
    }

    @Override
    public List<Card> draw(List<Card> drawingPile, List<Card> discardPile, int numberOfCardsToDraw, Random rnd) {
        if(drawingPile.size() < numberOfCardsToDraw) {
            Collections.shuffle(discardPile, rnd);
            drawingPile.addAll(discardPile);
            discardPile.clear();
        }
        List<Card> draw = new ArrayList<>();
        while(numberOfCardsToDraw > 0) {
            draw.add(drawingPile.get(0));
            drawingPile.remove(0);
            numberOfCardsToDraw--;
        }
        return draw;
    }
}

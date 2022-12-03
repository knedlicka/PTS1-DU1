package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DrawingStrategy1 implements IDrawingStrategy{

    public List<Card> discardAndDraw(List<Card> discard, List<Card> drawingPile, List<Card> discardPile, Random rnd) {
        discardPile.addAll(discard);
        int numberOfCardsToDraw = discard.size();
        return draw(drawingPile, discardPile, numberOfCardsToDraw, rnd);
    }
    @Override
    public List<Card> draw(List<Card> drawingPile, List<Card> discardPile, int numberOfCardsToDraw, Random rnd) {
        List<Card> draw = new ArrayList<>();
        while(numberOfCardsToDraw > 0) {
            if(drawingPile.isEmpty()) {
                Collections.shuffle(discardPile, rnd);
                drawingPile.addAll(discardPile);
                discardPile = new ArrayList<>();
            }
            draw.add(drawingPile.get(0));
            drawingPile.remove(0);
            numberOfCardsToDraw--;
        }
        return draw;
    }
}

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DrawingStrategy2 implements IDrawingStrategy{
    @Override
    public List<Card> discardAndDraw(List<Card> discard, List<Card> drawingPile, List<Card> discardPile) {
        int numberOfCardsToDraw = discard.size();
        List<Card> draw = draw(drawingPile, discardPile, numberOfCardsToDraw);
        discardPile.addAll(discard);
        return draw;
    }

    @Override
    public List<Card> draw(List<Card> drawingPile, List<Card> discardPile, int numberOfCardsToDraw) {
        if(drawingPile.size() < numberOfCardsToDraw) {
            Collections.shuffle(discardPile);
            drawingPile.addAll(discardPile);
            discardPile.clear();
        }
        List<Card> draw = new ArrayList<>();
        while(numberOfCardsToDraw > 0) {
            draw.add(drawingPile.get(1));
            drawingPile.remove(1);
            numberOfCardsToDraw--;
        }
        return draw;
    }
}

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DrawingStrategy1 implements IDrawingStrategy{

    public List<Card> discardAndDraw(List<Card> discard, List<Card> drawingPile, List<Card> discardPile) {
        discardPile.addAll(discard);
        int numberOfCardsToDraw = discard.size();
        return draw(drawingPile, discardPile, numberOfCardsToDraw);
    }
    @Override
    public List<Card> draw(List<Card> drawingPile, List<Card> discardPile, int numberOfCardsToDraw) {
        List<Card> draw = new ArrayList<>();
        while(numberOfCardsToDraw > 0) {
            if(drawingPile.isEmpty()) {
                Collections.shuffle(discardPile);
                drawingPile.addAll(discardPile);
                discardPile = new ArrayList<>();
            }
            draw.add(drawingPile.get(1));
            drawingPile.remove(1);
            numberOfCardsToDraw--;
        }
        return draw;
    }
}

package tests;

import game.Card;
import game.CardType;
import game.DrawingStrategy1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DrawingStrategy1Tests {

    void testDiscardAndDraw() {
        DrawingStrategy1 strategy = new DrawingStrategy1();

        List<Card> discard = new ArrayList<>();
        discard.add(new Card(CardType.Number, 1));
        discard.add(new Card(CardType.Number, 2));
        discard.add(new Card(CardType.Number, 3));

        List<Card> drawingPile = new ArrayList<>();
        drawingPile.add(new Card(CardType.Number, 4));
        drawingPile.add(new Card(CardType.Number, 5));

        List<Card> discardPile = new ArrayList<>();
        discardPile.add(new Card(CardType.Number, 6));
        discardPile.add(new Card(CardType.Number, 7));
        discardPile.add(new Card(CardType.Number, 8));

        List<Card> newDrawPile = new ArrayList<>();
        newDrawPile.addAll(discardPile);
        newDrawPile.addAll(discard);

        Collections.shuffle(newDrawPile,new Random(1));

        List<Card> expectedDraw = new ArrayList<>();
        expectedDraw.add(new Card(CardType.Number, 4));
        expectedDraw.add(new Card(CardType.Number, 5));
        expectedDraw.add(newDrawPile.get(0));

        List<Card> actualDraw = strategy.discardAndDraw(discard, drawingPile, discardPile, new Random(1));

        if(!expectedDraw.equals(actualDraw)) {
            System.err.println("    [FAILED] testDiscardAndDraw1");
        } else {
            System.out.println("    [PASSED] testDiscardAndDraw1");
        }
    }

    public void runTests() {
        System.out.println("DrawingStrategy1Tests:");
        testDiscardAndDraw();
    }
}

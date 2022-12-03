package tests;

import game.Card;
import game.CardType;
import game.DrawingStrategy2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DrawingStrategy2Tests {
    void testDiscardAndDraw() {
        DrawingStrategy2 strategy = new DrawingStrategy2();

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
        newDrawPile.addAll(drawingPile);
        List<Card> oldDiscardPile = new ArrayList<>();
        oldDiscardPile.addAll(discardPile);
        Collections.shuffle(oldDiscardPile, new java.util.Random(1));
        newDrawPile.addAll(oldDiscardPile);
        List<Card> expectedDraw = new ArrayList<>();
        expectedDraw.add(newDrawPile.get(0));
        expectedDraw.add(newDrawPile.get(1));
        expectedDraw.add(newDrawPile.get(2));

        List<Card> actualDraw = strategy.discardAndDraw(discard, drawingPile, discardPile, new Random(1));

        if(!expectedDraw.equals(actualDraw)) {
            System.err.println("[FAILED] testDiscardAndDraw2");
        } else {
            System.out.println("[PASSED] testDiscardAndDraw2");
        }
    }
    public void runTests() {
        testDiscardAndDraw();
    }

}

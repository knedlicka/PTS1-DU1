package tests;

import game.Card;
import game.DrawingStrategy2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static tests.TestUtils.makeListOfNumberedCards;

public class DrawingStrategy2Tests {
    void testDiscardAndDraw() {
        DrawingStrategy2 strategy = new DrawingStrategy2();

        List<Card> discard = makeListOfNumberedCards(List.of(1, 2, 3));
        List<Card> drawingPile = makeListOfNumberedCards(List.of(4, 5));
        List<Card> discardPile = makeListOfNumberedCards(List.of(6, 7, 8));

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
            System.err.println("    [FAILED] testDiscardAndDraw2");
        } else {
            System.out.println("    [PASSED] testDiscardAndDraw2");
        }
    }
    void testDiscardAndDrawEmpty(){
        DrawingStrategy2 strategy = new DrawingStrategy2();

        List<Card> discard = makeListOfNumberedCards(List.of(1, 2, 3));
        List<Card> drawingPile = new ArrayList<>();
        List<Card> discardPile = makeListOfNumberedCards(List.of(6, 7, 8));

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
            System.err.println("    [FAILED] testDiscardAndDraw2 with empty drawing pile");
        } else {
            System.out.println("    [PASSED] testDiscardAndDraw2 with empty drawing pile");
        }
    }

    void testDiscardAndDrawEnoughCards() {
        DrawingStrategy2 strategy = new DrawingStrategy2();

        List<Card> discard = makeListOfNumberedCards(List.of(1, 2, 3));
        List<Card> drawingPile = makeListOfNumberedCards(List.of(4, 5, 6, 7));
        List<Card> discardPile = makeListOfNumberedCards(List.of(6, 7, 8));

        List<Card> expectedDraw = new ArrayList<>();
        expectedDraw.add(drawingPile.get(0));
        expectedDraw.add(drawingPile.get(1));
        expectedDraw.add(drawingPile.get(2));

        List<Card> actualDraw = strategy.discardAndDraw(discard, drawingPile, discardPile, new Random(1));

        if(!expectedDraw.equals(actualDraw)) {
            System.err.println("    [FAILED] testDiscardAndDraw2 with enough cards");
        } else {
            System.out.println("    [PASSED] testDiscardAndDraw2 with enough cards");
        }
    }

    void testDiscardAndDrawWithJustEnoughCards() {
        DrawingStrategy2 strategy = new DrawingStrategy2();

        List<Card> discard = makeListOfNumberedCards(List.of(1, 2, 3));
        List<Card> drawingPile = makeListOfNumberedCards(List.of(4, 5, 6));
        List<Card> discardPile = makeListOfNumberedCards(List.of(6, 7, 8));

        List<Card> expectedDraw = new ArrayList<>();
        expectedDraw.add(drawingPile.get(0));
        expectedDraw.add(drawingPile.get(1));
        expectedDraw.add(drawingPile.get(2));

        List<Card> actualDraw = strategy.discardAndDraw(discard, drawingPile, discardPile, new Random(1));

        if(!expectedDraw.equals(actualDraw)) {
            System.err.println("    [FAILED] testDiscardAndDraw2 with just enough cards");
        } else {
            System.out.println("    [PASSED] testDiscardAndDraw2 with just enough cards");
        }
    }
    public void runTests() {
        System.out.println("DrawingStrategy2Tests:");
        testDiscardAndDraw();
        testDiscardAndDrawEmpty();
        testDiscardAndDrawEnoughCards();
        testDiscardAndDrawWithJustEnoughCards();
    }

}

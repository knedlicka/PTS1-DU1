package tests;

import game.Card;
import game.CardType; // REVIEW: unused import
import game.DrawingStrategy1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static tests.TestUtils.makeListOfNumberedCards;

public class DrawingStrategy1Tests {

    void testDiscardAndDrawWith2Cards() {
        DrawingStrategy1 strategy = new DrawingStrategy1();

        List<Card> discard = makeListOfNumberedCards(List.of(1, 2, 3));
        List<Card> drawingPile = makeListOfNumberedCards(List.of(4, 5));
        List<Card> discardPile = makeListOfNumberedCards(List.of(6, 7, 8));

        List<Card> newDrawPile = new ArrayList<>();
        newDrawPile.addAll(discardPile);
        newDrawPile.addAll(discard);

        Collections.shuffle(newDrawPile,new Random(1));

        List<Card> expectedDraw = makeListOfNumberedCards(List.of(4, 5));
        expectedDraw.add(newDrawPile.get(0));

        List<Card> actualDraw = strategy.discardAndDraw(discard, drawingPile, discardPile, new Random(1));

        if(!expectedDraw.equals(actualDraw)) {
            System.err.println("    [FAILED] testDiscardAndDraw1 with 2 cards in drawing pile");
        } else {
            System.out.println("    [PASSED] testDiscardAndDraw1 with 2 cards in drawing pile");
        }
    }

    void testDiscardAndDrawEmpty(){
        DrawingStrategy1 strategy = new DrawingStrategy1();

        List<Card> discard = makeListOfNumberedCards(List.of(1, 2, 3));
        List<Card> drawingPile = new ArrayList<>();
        List<Card> discardPile = makeListOfNumberedCards(List.of(6, 7, 8));

        List<Card> newDrawPile = new ArrayList<>();
        newDrawPile.addAll(discardPile);
        newDrawPile.addAll(discard);

        Collections.shuffle(newDrawPile,new Random(1));

        List<Card> expectedDraw = new ArrayList<>();
        expectedDraw.add(newDrawPile.get(0));
        expectedDraw.add(newDrawPile.get(1));
        expectedDraw.add(newDrawPile.get(2));

        List<Card> actualDraw = strategy.discardAndDraw(discard, drawingPile, discardPile, new Random(1));

        if(!expectedDraw.equals(actualDraw)) {
            System.err.println("    [FAILED] testDiscardAndDraw1 with empty drawing pile");
        } else {
            System.out.println("    [PASSED] testDiscardAndDraw1 with empty drawing pile");
        }
    }

    void testDiscardAndDrawEnoughCards() {
        DrawingStrategy1 strategy = new DrawingStrategy1();

        List<Card> discard = makeListOfNumberedCards(List.of(1, 2, 3));
        List<Card> drawingPile = makeListOfNumberedCards(List.of(4, 5, 6, 7));
        List<Card> discardPile = makeListOfNumberedCards(List.of(6, 7, 8));

        List<Card> expectedDraw = makeListOfNumberedCards(List.of(4, 5, 6));

        List<Card> actualDraw = strategy.discardAndDraw(discard, drawingPile, discardPile, new Random(1));

        if(!expectedDraw.equals(actualDraw)) {
            System.err.println("    [FAILED] testDiscardAndDraw1 with enough cards in drawing pile");
        } else {
            System.out.println("    [PASSED] testDiscardAndDraw1 with enough cards in drawing pile");
        }
    }

    void testDiscardAndDrawWithJustEnoughCards() {
        DrawingStrategy1 strategy = new DrawingStrategy1();

        List<Card> discard = makeListOfNumberedCards(List.of(1, 2, 3));
        List<Card> drawingPile = makeListOfNumberedCards(List.of(4, 5, 6));
        List<Card> discardPile = makeListOfNumberedCards(List.of(6, 7, 8));

        List<Card> expectedDraw = makeListOfNumberedCards(List.of(4, 5, 6));

        List<Card> actualDraw = strategy.discardAndDraw(discard, drawingPile, discardPile, new Random(1));
        if(!expectedDraw.equals(actualDraw)) {
            System.err.println("    [FAILED] testDiscardAndDraw1 with just enough cards in drawing pile");
        } else {
            System.out.println("    [PASSED] testDiscardAndDraw1 with just enough cards in drawing pile");
        }
    }

    public void runTests() {
        System.out.println("DrawingStrategy1Tests:");
        testDiscardAndDrawWith2Cards();
        testDiscardAndDrawEmpty();
        testDiscardAndDrawEnoughCards();
        testDiscardAndDrawWithJustEnoughCards();
    }
}

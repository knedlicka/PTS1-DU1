package tests;

import game.*;

import java.util.ArrayList;
import java.util.List;

public class EvaluateNumberedCardsTests {

    EvaluateNumberedCards evaluateNumberedCards;

    void createEvaluateNumberedCards() {
        List<Card> drawingPile = new ArrayList<>();
        for(int i = 10; i < 20; i++) {
            drawingPile.add(new Card(CardType.Number, i));
        }
        List<Card> discardPile = new ArrayList<>();
        IDrawingStrategy drawingStrategy = new DrawingStrategy1();
        IDrawingAndTrashPile drawingAndTrashPile = new DrawingAndTrashPile(drawingPile, discardPile, drawingStrategy);
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardType.Number, 1));
        for(int i = 1; i <= 5; i++) {
            cards.add(new Card(CardType.Number, i));
        }
        IHand hand = new Hand(0, cards, drawingAndTrashPile);
        evaluateNumberedCards = new EvaluateNumberedCards(hand);
    }

    private void testPlay1And2And3() {
        createEvaluateNumberedCards();
        List<Card> cards = new ArrayList<>();
        List<IPosition> positions = new ArrayList<>();
        for(int i = 1; i <= 3; i++) {
            cards.add(new Card(CardType.Number, i));
            positions.add(new HandPosition(i, 0));
        }
        boolean playedSuccessfully = evaluateNumberedCards.play(cards, positions);
        if (playedSuccessfully) {
            System.out.println("    [PASSED] testPlay1And2And3");
        } else {
            System.out.println("    [FAILED] testPlay1And2And3");
        }
    }

    private void testPlay1And2() {
        createEvaluateNumberedCards();
        List<Card> cards = new ArrayList<>();
        List<IPosition> positions = new ArrayList<>();
        for(int i = 1; i <= 2; i++) {
            cards.add(new Card(CardType.Number, i));
            positions.add(new HandPosition(i, 0));
        }
        boolean playedSuccessfully = evaluateNumberedCards.play(cards, positions);
        if (!playedSuccessfully) {
            System.out.println("    [PASSED] testPlay1And2");
        } else {
            System.out.println("    [FAILED] testPlay1And2");
        }
    }

    private void testPlay1And1() {
        createEvaluateNumberedCards();
        List<Card> cards = new ArrayList<>();
        List<IPosition> positions = new ArrayList<>();
        for(int i = 0; i <= 1; i++) {
            cards.add(new Card(CardType.Number, 1));
            positions.add(new HandPosition(i, 0));
        }
        boolean playedSuccessfully = evaluateNumberedCards.play(cards, positions);
        if (playedSuccessfully) {
            System.out.println("    [PASSED] testPlay1And2And3");
        } else {
            System.out.println("    [FAILED] testPlay1And2And3");
        }
    }

    private void testPlay() {
        testPlay1And2And3();
        testPlay1And2();
        testPlay1And1();
    }


    public void runTests() {
        System.out.println("EvaluateNumberedCardsTests");
        testPlay();
    }
}

package tests;

import game.*;

import java.util.*;

public class HandTests {
    private Hand hand;

    boolean mapContainsAnotherMap(Map<HandPosition, Card> a, Map<HandPosition, Card> b) {
        for(Map.Entry<HandPosition, Card> entryA : a.entrySet()) {
            boolean contains = false;
            for(Map.Entry<HandPosition, Card> entryB: b.entrySet()) {
                if(entryA.getKey().equals(entryB.getKey()) && entryA.getValue().equals(entryB.getValue())) {
                    contains = true;
                    break;
                }
            }
            if(!contains) {
                return false;
            }
        }
        return true;
    }

    boolean containsAll(Map<HandPosition, Card> a, Map<HandPosition, Card> b) {
        return mapContainsAnotherMap(a, b) && mapContainsAnotherMap(b, a);
    }

    void createHand() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardType.Number, 1));
        cards.add(new Card(CardType.Number, 2));
        cards.add(new Card(CardType.Number, 3));
        cards.add(new Card(CardType.Number, 4));
        cards.add(new Card(CardType.Number, 5));

        List<Card> drawingPile = new ArrayList<>();
        drawingPile.add(new Card(CardType.Number, 6));
        drawingPile.add(new Card(CardType.Number, 7));
        drawingPile.add(new Card(CardType.Number, 8));
        drawingPile.add(new Card(CardType.Number, 9));

        List<Card> discardPile = new ArrayList<>();
        discardPile.add(new Card(CardType.Number, 10));

        IDrawingAndTrashPile drawingAndTrashPile = new DrawingAndTrashPile(drawingPile, discardPile, new DrawingStrategy1());
        hand = new Hand(0, cards, drawingAndTrashPile);
    }
    void testPickCards() {
        createHand();
        List<HandPosition> positions = new ArrayList<>();
        positions.add(new HandPosition(0, 0));
        positions.add(new HandPosition(2, 0));
        positions.add(new HandPosition(4, 0));
        Optional<List<Card>> actual = hand.pickCards(positions);
        List<Card> expected = new ArrayList<>();
        expected.add(new Card(CardType.Number, 1));
        expected.add(new Card(CardType.Number, 3));
        expected.add(new Card(CardType.Number, 5));
        if(!actual.isPresent() || !actual.get().equals(expected)) {
            System.err.println("    [FAILED] testPickCards");
        } else {
            System.out.println("    [PASSED] testPickCards");
        }
    }

    void testRemovePickedCardsAndRedraw() {
        createHand();
        List<HandPosition> positions = new ArrayList<>();
        positions.add(new HandPosition(0, 0));
        positions.add(new HandPosition(2, 0));
        positions.add(new HandPosition(4, 0));
        hand.pickCards(positions);
        Map<HandPosition, Card> actual = hand.removePickedCardsAndRedraw();
        Map<HandPosition, Card> expected = new java.util.HashMap<>();
        expected.put(new HandPosition(0, 0), new Card(CardType.Number, 6));
        expected.put(new HandPosition(1, 0), new Card(CardType.Number, 2));
        expected.put(new HandPosition(2, 0), new Card(CardType.Number, 7));
        expected.put(new HandPosition(3, 0), new Card(CardType.Number, 4));
        expected.put(new HandPosition(4, 0), new Card(CardType.Number, 8));

        if(!(containsAll(actual, expected))) {
            System.err.println("    [FAILED] testRemovePickedCardsAndRedraw");
        } else {
            System.out.println("    [PASSED] testRemovePickedCardsAndRedraw");
        }
    }

    void testHasCardOfType() {
        createHand();
        Optional<HandPosition> actual1 = hand.hasCardOfType(CardType.Number);
        Optional<HandPosition> actual2 = hand.hasCardOfType(CardType.King);

        if(actual1.isEmpty() || !actual2.isEmpty()) {
            System.err.println("    [FAILED] testHasCardOfType");
        } else {
            System.out.println("    [PASSED] testHasCardOfType");
        }
    }
    public void runTests() {
        System.out.println("Running HandTests:");
        testPickCards();
        testRemovePickedCardsAndRedraw();
        testHasCardOfType();
    }

}

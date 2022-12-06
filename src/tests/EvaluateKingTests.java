package tests;

import game.*;

import javax.swing.text.Position;
import java.util.ArrayList;
import java.util.List;

import static tests.TestUtils.containsAll;

public class EvaluateKingTests {
    private IEvaluateKing evaluateKing;
    private IQueenCollection sleepingQueens;
    private IQueenCollection awokenQueens;
    private IHand hand;
    private IDrawingAndTrashPile drawingAndTrashPile;

    private void createAwokeQueens() {
        awokenQueens = new AwokenQueens(0);
        awokenQueens.addQueen(new Queen(1));
        awokenQueens.addQueen(new Queen(2));
    }
    private void createSleepingQueens() {
        List<Queen> queens = new ArrayList<>();
        queens.add(new Queen(3));
        queens.add(new Queen(4));
        sleepingQueens = new SleepingQueens(queens);
    }
    private void createPiles(){
        List<Card> drawingPile = new ArrayList<>();
        for(int i = 1; i < 10; i++) {
            drawingPile.add(new Card(CardType.Number, i));
        }
        List<Card> discardPile = new ArrayList<>();
        IDrawingStrategy drawingStrategy = new DrawingStrategy1();
        drawingAndTrashPile = new DrawingAndTrashPile(drawingPile, discardPile, drawingStrategy);
    }
    private void createHand() {
        createPiles();
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardType.King, 0));
        cards.add(new Card(CardType.Number, 1));
        cards.add(new Card(CardType.Number, 2));
        cards.add(new Card(CardType.Number, 3));
        cards.add(new Card(CardType.Number, 4));
        hand = new Hand(0, cards, drawingAndTrashPile);
    }
    private void createEvaluateKing() {
        createAwokeQueens();
        createSleepingQueens();
        createHand();
        evaluateKing = new EvaluateKing(awokenQueens, hand, sleepingQueens);
    }

    private void testPlay() {
        createEvaluateKing();
        HandPosition handPosition = new HandPosition(0, 0);
        SleepingQueenPosition sleepingPosition = new SleepingQueenPosition(0);
        boolean actual = evaluateKing.play(handPosition, sleepingPosition);
        List<Queen> listSleepingQueens = new ArrayList<>();
        listSleepingQueens.add(new Queen(4));
        SleepingQueens expectedSleepingQueens = new SleepingQueens(listSleepingQueens);
        AwokenQueens expectedAwokenQueens = new AwokenQueens(0);
        expectedAwokenQueens.addQueen(new Queen(1));
        expectedAwokenQueens.addQueen(new Queen(2));
        expectedAwokenQueens.addQueen(new Queen(3));
        if(!actual || !containsAll(expectedAwokenQueens.getQueens(), awokenQueens.getQueens())|| !containsAll(expectedSleepingQueens.getQueens(), sleepingQueens.getQueens())) {
            System.err.println("    [FAILED] testPlay");
        } else {
            System.out.println("    [PASSED] testPlay");
        }
    }

    public void runTests() {
        System.out.println("EvaluateKingTests:");
        testPlay();
    }
}

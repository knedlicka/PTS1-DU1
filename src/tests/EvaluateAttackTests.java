package tests;

import game.*;

import java.util.ArrayList;
import java.util.List;

import static tests.TestUtils.containsAll;

public class EvaluateAttackTests {
    private IHand hand;
    private EvaluateAttack evaluateAttack;
    private IDrawingAndTrashPile drawingAndTrashPile;
    private Integer playerIndex;
    private List<AwokenQueens> awokenQueensOfPlayers;
    private SleepingQueens sleepingQueens;
    private IMoveQueen moveQueen;

    void createDrawingAndTrashPile() {
        List<Card> drawingPile = new ArrayList<>();
        drawingPile.add(new Card(CardType.King, 0));
        drawingPile.add(new Card(CardType.Number, 2));
        drawingPile.add(new Card(CardType.Number, 3));

        List<Card> trashPile = new ArrayList<>();
        trashPile.add(new Card(CardType.Number, 1));
        trashPile.add(new Card(CardType.Number, 4));

        drawingAndTrashPile = new DrawingAndTrashPile(drawingPile, trashPile, new DrawingStrategy1());
    }

    void createHand() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardType.MagicWand, 0));
        cards.add(new Card(CardType.King, 0));
        cards.add(new Card(CardType.Knight, 0));
        cards.add(new Card(CardType.SleepingPotion, 0));
        cards.add(new Card(CardType.Dragon, 0));
        hand = new Hand(playerIndex, cards, drawingAndTrashPile);
    }

    void createAwokenQueens() {
        awokenQueensOfPlayers = new ArrayList<>();
        AwokenQueens awokenQueens0 = new AwokenQueens(0);
        awokenQueens0.addQueen(new Queen(1));
        awokenQueens0.addQueen(new Queen(2));
        awokenQueensOfPlayers.add(awokenQueens0);
        AwokenQueens awokenQueens1 = new AwokenQueens(1);
        awokenQueens1.addQueen(new Queen(3));
        awokenQueens1.addQueen(new Queen(4));
        awokenQueensOfPlayers.add(awokenQueens1);
    }

    void createSleepingQueens() {
        List<Queen> queens = new ArrayList<>();
        queens.add(new Queen(5));
        queens.add(new Queen(6));
        sleepingQueens = new SleepingQueens(queens);

    }

    void createMoveQueen(CardType type) {
        createAwokenQueens();
        createSleepingQueens();
        moveQueen = new MoveQueen(awokenQueensOfPlayers, sleepingQueens, playerIndex, type);
    }

    void createEvaluateAttack(CardType cardType, CardType defenseType) {
        playerIndex = 0;
        createDrawingAndTrashPile();
        createHand();
        createMoveQueen(CardType.Knight);
        this.evaluateAttack = new EvaluateAttack(hand,cardType, defenseType, moveQueen);
    }

    void testPlayKnight() {
        createEvaluateAttack(CardType.Knight, CardType.Dragon);
        boolean playedSuccessfully = evaluateAttack.play(new AwokenQueenPosition(0, 0), 0);
        AwokenQueens expectedAwokenQueensOfPlayer0 = new AwokenQueens(0);
        expectedAwokenQueensOfPlayer0.addQueen(new Queen(2));
        AwokenQueens expectedAwokenQueensOfPlayer1 = new AwokenQueens(1);
        expectedAwokenQueensOfPlayer1.addQueen(new Queen(3));
        expectedAwokenQueensOfPlayer1.addQueen(new Queen(4));
        expectedAwokenQueensOfPlayer1.addQueen(new Queen(1));
        if (
                playedSuccessfully &&
                        containsAll(
                                awokenQueensOfPlayers.get(0).getQueens(),
                                expectedAwokenQueensOfPlayer0.getQueens()
                        ) &&
                        containsAll(
                                awokenQueensOfPlayers.get(1).getQueens(),
                                expectedAwokenQueensOfPlayer1.getQueens()
                        )
        ) {
            System.err.println("    [FAILED] testPlay Knight");
        } else {
            System.out.println("    [PASSED] testPlay Knight");
        }
    }

    void testPlaySleepingPotion() {
        createEvaluateAttack(CardType.SleepingPotion, CardType.MagicWand);
        boolean playedSuccessfully = evaluateAttack.play(new AwokenQueenPosition(0, 0), 0);
        AwokenQueens expectedAwokenQueensOfPlayer0 = new AwokenQueens(0);
        expectedAwokenQueensOfPlayer0.addQueen(new Queen(2));
        AwokenQueens expectedAwokenQueensOfPlayer1 = new AwokenQueens(1);
        expectedAwokenQueensOfPlayer1.addQueen(new Queen(3));
        expectedAwokenQueensOfPlayer1.addQueen(new Queen(4));
        List<Queen> sleeping = new ArrayList<>();
        sleeping.add(new Queen(5));
        sleeping.add(new Queen(6));
        sleeping.add(new Queen(1));
        SleepingQueens expectedSleepingQueens = new SleepingQueens(sleeping);

        if (
                playedSuccessfully &&
                        containsAll(
                                awokenQueensOfPlayers.get(0).getQueens(),
                                expectedAwokenQueensOfPlayer0.getQueens()
                        ) &&
                        containsAll(
                                awokenQueensOfPlayers.get(1).getQueens(),
                                expectedAwokenQueensOfPlayer1.getQueens()
                        ) &&
                        containsAll(
                                sleepingQueens.getQueens(),
                                expectedSleepingQueens.getQueens()
                        )
        ) {
            System.err.println("    [FAILED] testPlay SleepingPotion");
        } else {
            System.out.println("    [PASSED] testPlay SleepingPotion");
        }
    }
    public void runTests() {
        System.out.println("Running EvaluateAttackTests:");
        testPlayKnight();
        testPlaySleepingPotion();
    }
}

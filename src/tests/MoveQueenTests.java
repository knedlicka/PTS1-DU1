package tests;

import game.*;

import java.util.ArrayList;
import java.util.List;

public class MoveQueenTests {
    private List<AwokenQueens> awokenQueensOfEachPlayer;
    private SleepingQueens sleepingQueens;
    private MoveQueen moveQueen;
    private Integer playerIdx;
    void createAwokenQueens() {
        AwokenQueens awokenQueens0 = new AwokenQueens(0);
        awokenQueens0.addQueen(new Queen(5));
        awokenQueens0.addQueen(new Queen(6));
        AwokenQueens awokenQueens1 = new AwokenQueens(1);
        awokenQueens1.addQueen(new Queen(3));
        awokenQueens1.addQueen(new Queen(4));
        awokenQueensOfEachPlayer = new ArrayList<>();
        awokenQueensOfEachPlayer.add(awokenQueens0);
        awokenQueensOfEachPlayer.add(awokenQueens1);
    }

    void createSleepingQueens() {
        List<Queen> queens = new ArrayList<>();
        queens.add(new Queen(1));
        queens.add(new Queen(2));
        sleepingQueens = new SleepingQueens(queens);

    }

    void createMoveQueen(CardType causedBy) {
        moveQueen = new MoveQueen(awokenQueensOfEachPlayer, sleepingQueens, playerIdx, causedBy);
    }
    void testPlayKing() {
        createAwokenQueens();
        createSleepingQueens();
        this.playerIdx = 1;
        createMoveQueen(CardType.King);
        SleepingQueenPosition targetPosition = new SleepingQueenPosition(0);
        boolean actual = moveQueen.play(targetPosition);

        AwokenQueens expectedAwokenQueens = new AwokenQueens(1);
        expectedAwokenQueens.addQueen(new Queen(3));
        expectedAwokenQueens.addQueen(new Queen(4));
        expectedAwokenQueens.addQueen(new Queen(1));

        SleepingQueens expectedSleepingQueens = new SleepingQueens(new ArrayList<>());
        expectedSleepingQueens.addQueen(new Queen(2));

        if(!actual && !awokenQueensOfEachPlayer.get(1).equals(expectedAwokenQueens) && !sleepingQueens.equals(expectedSleepingQueens)) {
            System.err.println("    [FAILED] testPlay King");
        } else {
            System.out.println("    [PASSED] testPlay King");
        }
    }

    void testPlayKnight(){
        createAwokenQueens();
        createSleepingQueens();
        this.playerIdx = 1;
        createMoveQueen(CardType.Knight);
        AwokenQueenPosition targetPosition = new AwokenQueenPosition(0, 0);
        boolean actual = moveQueen.play(targetPosition);

        AwokenQueens expectedAwokenQueensOfPlayer1 = new AwokenQueens(1);
        expectedAwokenQueensOfPlayer1.addQueen(new Queen(3));
        expectedAwokenQueensOfPlayer1.addQueen(new Queen(4));
        expectedAwokenQueensOfPlayer1.addQueen(new Queen(5));

        AwokenQueens expectedAwokenQueensOfPlayer0 = new AwokenQueens(0);
        expectedAwokenQueensOfPlayer0.addQueen(new Queen(6));

        SleepingQueens expectedSleepingQueens = new SleepingQueens(new ArrayList<>());
        expectedSleepingQueens.addQueen(new Queen(1));
        expectedSleepingQueens.addQueen(new Queen(2));

        if(!actual &&
                !awokenQueensOfEachPlayer.get(1).equals(expectedAwokenQueensOfPlayer1) &&
                !awokenQueensOfEachPlayer.get(0).equals(expectedAwokenQueensOfPlayer0) &&
                !sleepingQueens.equals(expectedSleepingQueens)) {
            System.err.println("    [FAILED] testPlay Knight");
        } else {
            System.out.println("    [PASSED] testPlay Knight");
        }
    }

    void testPlaySleepingPotion() {
        createAwokenQueens();
        createSleepingQueens();
        this.playerIdx = 1;
        createMoveQueen(CardType.SleepingPotion);
        AwokenQueenPosition targetPosition = new AwokenQueenPosition(0, 0);
        boolean actual = moveQueen.play(targetPosition);

        AwokenQueens expectedAwokenQueensOfPlayer0 = new AwokenQueens(0);
        expectedAwokenQueensOfPlayer0.addQueen(new Queen(6));

        AwokenQueens expectedAwokenQueensOfPlayer1 = new AwokenQueens(1);
        expectedAwokenQueensOfPlayer1.addQueen(new Queen(3));
        expectedAwokenQueensOfPlayer1.addQueen(new Queen(4));

        SleepingQueens expectedSleepingQueens = new SleepingQueens(new ArrayList<>());
        expectedSleepingQueens.addQueen(new Queen(1));
        expectedSleepingQueens.addQueen(new Queen(2));
        expectedSleepingQueens.addQueen(new Queen(5));

        if(!actual &&
                !awokenQueensOfEachPlayer.get(1).equals(expectedAwokenQueensOfPlayer1) &&
                !awokenQueensOfEachPlayer.get(0).equals(expectedAwokenQueensOfPlayer0) &&
                !sleepingQueens.equals(expectedSleepingQueens)) {
            System.err.println("    [FAILED] testPlay SleepingPotion");
        } else {
            System.out.println("    [PASSED] testPlay SleepingPotion");
        }
    }
    public void runTests() {
        System.out.println("Running MoveQueenTests:");
        testPlayKing();
        testPlayKnight();
        testPlaySleepingPotion();
    }
}

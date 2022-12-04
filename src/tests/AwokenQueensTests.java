package tests;

import game.AwokenQueenPosition;
import game.AwokenQueens;
import game.IPosition;
import game.Queen;

import java.util.Map;
import java.util.Optional;

import static tests.TestUtils.containsAll;

public class AwokenQueensTests {
    private AwokenQueens awokenQueens;

    void createAwokenQueens() {
        awokenQueens = new AwokenQueens(0);
        awokenQueens.addQueen(new Queen(1));
        awokenQueens.addQueen(new Queen(2));
    }
    void testremoveQueen() {
        createAwokenQueens();
        Optional<Queen> actual = awokenQueens.removeQueen(new AwokenQueenPosition(0, 0));
        Optional<Queen> expected = Optional.of(new Queen(1));
        if(!actual.equals(expected)) {
            System.err.println("    [FAILED] testremoveQueen");
        } else {
            System.out.println("    [PASSED] testremoveQueen");
        }
    }
    void testGetQueens() {
        createAwokenQueens();
        Map<IPosition, Queen> actual = awokenQueens.getQueens();
        Map<IPosition, Queen> expected = new java.util.HashMap<>();
        expected.put(new AwokenQueenPosition(0, 0), new Queen(1));
        expected.put(new AwokenQueenPosition(1, 0), new Queen(2));
        if(!containsAll(actual, expected)) {
            System.err.println("    [FAILED] testGetQueens");
        } else {
            System.out.println("    [PASSED] testGetQueens");
        }
    }
    public void runTests() {
        System.out.println("Running AwokenQueensTests:");
        testremoveQueen();
        testGetQueens();
    }
}

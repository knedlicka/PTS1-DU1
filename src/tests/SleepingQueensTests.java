package tests;

import game.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SleepingQueensTests {
    SleepingQueens sleepingQueens;
    void createSleepingQueens() {
        List<Queen> queens = new ArrayList<>();
        queens.add(new Queen(1));
        queens.add(new Queen(2));
        queens.add(new Queen(3));
        queens.add(new Queen(4));
        sleepingQueens = new SleepingQueens(queens);
    }

    boolean mapContainsAnotherMap(Map<Position, Queen> a, Map<Position, Queen> b) {
        for(Map.Entry<Position, Queen> entryA : a.entrySet()) {
            boolean contains = false;
            for(Map.Entry<Position, Queen> entryB: b.entrySet()) {
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

    boolean containsAll(Map<Position, Queen> a, Map<Position, Queen> b) {
        return mapContainsAnotherMap(a, b) && mapContainsAnotherMap(b, a);
    }
    void testRemoveQueen() {
        createSleepingQueens();
        Position position = new SleepingQueenPosition(1);
        Optional<Queen> actual = sleepingQueens.removeQueen(position);
        Optional<Queen> expected = Optional.of(new Queen(2));
        if(!actual.equals(expected)) {
            System.err.println("    [FAILED] testRemoveQueen");
        } else {
            System.out.println("    [PASSED] testRemoveQueen");
        }
    }

    void testGetQueens() {
        createSleepingQueens();
        Map<Position, Queen> actual = sleepingQueens.getQueens();
        Map<Position, Queen> expected = new java.util.HashMap<>();
        expected.put(new SleepingQueenPosition(0), new Queen(1));
        expected.put(new SleepingQueenPosition(1), new Queen(2));
        expected.put(new SleepingQueenPosition(2), new Queen(3));
        expected.put(new SleepingQueenPosition(3), new Queen(4));

        if(!containsAll(expected, actual)) {
            System.err.println("    [FAILED] testGetQueens");
        } else {
            System.out.println("    [PASSED] testGetQueens");
        }
    }
    public void runTests() {
        System.out.println("Running SleepingQueensTests:");
        testRemoveQueen();
        testGetQueens();
    }
}

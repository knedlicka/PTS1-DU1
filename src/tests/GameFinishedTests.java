package tests;

import game.AwokenQueens;
import game.GameFinished;
import game.IQueenCollection;
import game.Queen;

import java.util.List;
import java.util.Optional;

public class GameFinishedTests {
    private GameFinished gameFinished;
    private void createGameFinished(Integer neededPoints, Integer neededQueens) {
        IQueenCollection awokenQueensOfPlayer0 = new AwokenQueens(0);
        awokenQueensOfPlayer0.addQueen(new Queen(20));
        awokenQueensOfPlayer0.addQueen(new Queen(15));
        awokenQueensOfPlayer0.addQueen(new Queen(10));
        IQueenCollection awokenQueensOfPlayer1 = new AwokenQueens(1);
        awokenQueensOfPlayer1.addQueen(new Queen(15));
        List<IQueenCollection> awokenQueensOfPlayers = List.of(awokenQueensOfPlayer0, awokenQueensOfPlayer1);
        gameFinished = new GameFinished(awokenQueensOfPlayers, 2, neededPoints, neededQueens);
    }
    private void testIsFinished40And4(){
        createGameFinished(40,  4);
        Optional<Integer> actual = gameFinished.isFinished();
        if(actual.isEmpty() || actual.get() != 0) {
            System.err.println("    [FAILED] testIsFinished40And4");
        } else {
            System.out.println("    [PASSED] testIsFinished40And4");
        }
    }
    private void testIsFinished60And3(){
        createGameFinished(60,  3);
        Optional<Integer> actual = gameFinished.isFinished();
        if(actual.isEmpty() || actual.get() != 0) {
            System.err.println("    [FAILED] testIsFinished60And3");
        } else {
            System.out.println("    [PASSED] testIsFinished60And3");
        }
    }
    public void runTests(){
        GameFinishedTests gameFinishedTests = new GameFinishedTests();
        gameFinishedTests.testIsFinished40And4();
        gameFinishedTests.testIsFinished60And3();
    }
}

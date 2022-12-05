package tests;

import game.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameTests {

    IGame game;

    private void createGame() {
        List<Card> drawingPile = new ArrayList<>();
        for(int i = 1; i <= 50; i++) {
            drawingPile.add(new Card(CardType.Number, i));
        }
        List<Queen> queensSleeping = new ArrayList<>();
        queensSleeping.add(new Queen(6));
        IDrawingStrategy drawingStrategy = new DrawingStrategy1();
        this.game = new Game(4, drawingPile, queensSleeping, drawingStrategy);
    }

    private void testGamePlay() {
        createGame();
        List<IPosition> positions = new ArrayList<>();
        positions.add(new HandPosition(0, 0));
        Optional<GameState> gameState = game.play(0, positions);
        if(gameState.isPresent()) {
            System.out.println("    [PASSED] testGamePlay");
        } else {
            System.out.println("    [FAILED] testGamePlay");
        }
    }

    private void testGetWinner() {
        createGame();
        if(game.getWinner().isPresent()) {
            System.out.println("    [FAILED] testGetWinner");
        } else {
            System.out.println("    [PASSED] testGetWinner");
        }
    }

    public void runTests() {
        System.out.println("GameTests:");
        testGamePlay();
        testGetWinner();
    }
}

package tests;

import game.*;

import java.util.ArrayList;
import java.util.List;

public class GameAdaptorTests {
    private class MockedDealer implements IDealer {
        @Override
        public List<Card> getDrawingPile() {
            List<Card> cards = new ArrayList<>();
            cards.add(new Card(CardType.King, 0));
            cards.add(new Card(CardType.Knight, 0));
            for(int i = 1; i <= 10; i++){
                cards.add(new Card(CardType.Number, i));
            }
            return cards;
        }

        @Override
        public List<Queen> getQueensSleeping() {
            List<Queen> queens = new ArrayList<>();
            for(int i = 2; i < 7; i++) {
                queens.add(new Queen(i));
            }
            return queens;
        }
    }
    GameAdaptor gameAdaptor;
    private void createGameAdaptor() {
        List<String> players = List.of("player1", "player2");
        gameAdaptor = new GameAdaptor(2, players, new GameObservable(), new DrawingStrategy1(), new MockedDealer());
    }
    private void testPlay() {
        createGameAdaptor();
        String gameAdaptorResponse = gameAdaptor.play("player1", "h0 s1");
        String expectedRespnse = "Optional[onTurn: 1\n" +
                "numberOfPlayers: 2\n" +
                "sleepingQueens: [SleepingQueenPosition{cardIndex=1}, SleepingQueenPosition{cardIndex=3}, SleepingQueenPosition{cardIndex=2}, SleepingQueenPosition{cardIndex=0}]\n" +
                "cards: {HandPosition{cardIndex=0, playerIndex=1}=Optional[Card{type=Knight, value=0}], HandPosition{cardIndex=1, playerIndex=1}=Optional[Card{type=Number, value=5}], HandPosition{cardIndex=0, playerIndex=0}=Optional[Card{type=Number, value=9}], HandPosition{cardIndex=1, playerIndex=0}=Optional[Card{type=Number, value=4}], HandPosition{cardIndex=0, playerIndex=4}=Optional[Card{type=Number, value=3}], HandPosition{cardIndex=0, playerIndex=2}=Optional[Card{type=Number, value=1}], HandPosition{cardIndex=0, playerIndex=3}=Optional[Card{type=Number, value=2}], HandPosition{cardIndex=1, playerIndex=2}=Optional[Card{type=Number, value=6}], HandPosition{cardIndex=1, playerIndex=3}=Optional[Card{type=Number, value=7}], HandPosition{cardIndex=1, playerIndex=4}=Optional[Card{type=Number, value=8}]}\n" +
                "awokenQueens: {AwokenQueenPosition{cardIndex=0, playerIndex=0}=null}\n" +
                "cardsDiscardedLastTurn: []\n" +
                "]";
        if(gameAdaptorResponse.equals(expectedRespnse)) {
            System.out.println("    [PASSED] testPlay");
        } else {
            System.err.println("    [FAILED] testPlay");
        }
    }

    public void runTests() {
        System.out.println("Running gameAdaptorTests");
        testPlay();
    }
}

package game;

import java.util.*;

public class GameState {
    private Integer numberOfPlayers;
    private Integer onTurn;
    private Set<SleepingQueenPosition> sleepingQueens;
    private Map<HandPosition, Optional<Card>> cards;
    private Map<AwokenQueenPosition, Queen> awokenQueens;
    private List<Card> cardsDiscardedLastTurn;

    public GameState(
            Integer numberOfPlayers,
            Integer onTurn,
            Set<SleepingQueenPosition> sleepingQueens,
            Map<HandPosition, Optional<Card>> cards,
            Map<AwokenQueenPosition, Queen> awokenQueens,
            List<Card> cardsDiscardedLastTurn
    ) {
        this.numberOfPlayers = numberOfPlayers;
        this.onTurn = onTurn;
        this.sleepingQueens = sleepingQueens;
        this.cards = cards;
        this.awokenQueens = awokenQueens;
        this.cardsDiscardedLastTurn = cardsDiscardedLastTurn == null ? new ArrayList<>() : cardsDiscardedLastTurn;
    }
    public Integer getOnTurn() {
        return onTurn;
    }
    public Set<SleepingQueenPosition> getSleepingQueens() {
        return sleepingQueens;
    }
    public Map<HandPosition, Optional<Card>> getCards(Integer playerIdx) {
        Map<HandPosition, Optional<Card>> playerCards = new HashMap<>();
        for(HandPosition pos: cards.keySet()) {
            if(pos.getPlayerIndex().equals(playerIdx)) {
                playerCards.put(pos, cards.get(pos));
            }
        }
        return playerCards;
    }
    public Map<AwokenQueenPosition, Queen> getAwokenQueens(Integer playerIdx) {
        Map<AwokenQueenPosition, Queen> playerAwokenQueens = new HashMap<>();
        for(AwokenQueenPosition pos: awokenQueens.keySet()) {
            if(pos.getPlayerIndex().equals(playerIdx)) {
                playerAwokenQueens.put(pos, awokenQueens.get(pos));
            }
        }
        return playerAwokenQueens;
    }

    @Override
    public String toString() {
        String message = "";
        message += "onTurn: " + onTurn.toString() + "\n";
        message += "numberOfPlayers: " + numberOfPlayers.toString() + "\n";
        message += "sleepingQueens: " + sleepingQueens.toString() + "\n";
        message += "cards: " + cards.toString() + "\n";
        message += "awokenQueens: " + awokenQueens.toString() + "\n";
        message += "cardsDiscardedLastTurn: " + cardsDiscardedLastTurn.toString() + "\n";
        return message;
    }
}

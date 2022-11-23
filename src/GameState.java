import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

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
        this.cardsDiscardedLastTurn = cardsDiscardedLastTurn;
    }
}

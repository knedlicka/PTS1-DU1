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

    public GameState(Integer nP, Integer onTurn) {

    }
}

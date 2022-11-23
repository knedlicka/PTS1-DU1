import java.util.Map;
import java.util.Optional;

public class PlayerState {
    private final Map<Integer, Optional<Card>> cards;
    private final Map<Integer, Queen> awokenQueens;

    public PlayerState(Map<Integer, Optional<Card>> cards, Map<Integer, Queen> awokenQueens) {
        this.cards = cards;
        this.awokenQueens = awokenQueens;
    }
}

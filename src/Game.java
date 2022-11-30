import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Game implements IGame {
    private final int numberOfPlayers;
    private List<Player> players;
    private DrawingAndTrashPile drawingAndTrashPile;
    private final SleepingQueens sleepingQueens;
    public Game(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        List<Card> cards = new ArrayList<>();

        // Add Kings
        for(int i  = 0; i < 8; i++) {
            cards.add(new Card(CardType.King, 1)); // TODO zistit value karty
        }

        // Add Knights
        // TODO add knights and the rest

        List<List<Card>> playerCards = new ArrayList<>();
        for(int i = 0; i < numberOfPlayers; i++) {

        }

        sleepingQueens = new SleepingQueens();

        for(int i = 0; i < numberOfPlayers; i++) {
            Hand hand = new Hand()
            Player player = new Player()
        }
    }

    @Override
    public Optional<GameState> play(Integer playerIdx, List<Position> cards) {
        return Optional.empty();
    }
}

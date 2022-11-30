import java.util.List;
import java.util.Optional;

public class Game implements IGame {
    private int numberOfPlayers;

    public Game(int numberOfPlayers) {

    }

    @Override
    public Optional<GameState> play(Integer playerIdx, List<Position> cards) {
        return Optional.empty();
    }
}

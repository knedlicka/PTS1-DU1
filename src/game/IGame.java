package game;

import java.util.List;
import java.util.Optional;

public interface IGame {
    public Optional<GameState> play(Integer playerIdx, List<Position> cards);
}

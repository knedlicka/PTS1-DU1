package game;

import java.util.List;
import java.util.Optional;

public interface IGame {
    Optional<GameState> play(Integer playerIdx, List<IPosition> cards);
    Optional<Integer> getWinner();
}

package game;

import java.util.Optional;

public interface IGameFinishedStrategy {
    Optional<Integer> isFinished();
}

package game;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IQueenCollection {
    void addQueen(Queen queen);
    Optional<Queen> removeQueen(IPosition position);
    List<Queen> getListOfQueens();
    abstract Map<IPosition, Queen> getQueens();
}

package game;

import java.util.*;

public abstract class QueenCollection implements IQueenCollection{
    protected List<Queen> queens;

    public QueenCollection() {
        this.queens = new ArrayList<>();
    }

    public void addQueen(Queen queen) {
        queens.add(queen);
    }

    public Optional<Queen> removeQueen(IPosition position) {
        int cardIndex = position.getCardIndex();
        if(0 <= cardIndex && queens.size() > cardIndex) {
            Queen queen = queens.get(cardIndex);
            queens.remove(cardIndex);
            return Optional.of(queen);
        }
        return Optional.empty();
    }

    public List<Queen> getListOfQueens() {
        return queens;
    }
    public abstract Map<IPosition, Queen> getQueens();
}

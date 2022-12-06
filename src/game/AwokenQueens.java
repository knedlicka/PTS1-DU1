package game;

import java.util.HashMap;
import java.util.Map;

public class AwokenQueens extends QueenCollection {

    private int playerIdx;

    public AwokenQueens(int playerIdx) {
        super();
        this.playerIdx = playerIdx;
    }
    @Override
    public Map<IPosition, Queen> getQueens() {
        Map<IPosition, Queen> queenMap = new HashMap<>();
        for(int i = 0; i < queens.size(); i++) {
            AwokenQueenPosition sleepingQueenPosition = new AwokenQueenPosition(i, playerIdx);
            queenMap.put(sleepingQueenPosition, queens.get(i));
        }
        return queenMap;
    }
}

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SleepingQueens extends QueenCollection {
    public SleepingQueens(List<Queen> queens) {
        this.queens = queens;
    }
    @Override
    public Map<Position, Queen> getQueens() {
        Map<Position, Queen> queenMap = new HashMap<>();
        for(int i = 0; i < queens.size(); i++) {
            SleepingQueenPosition sleepingQueenPosition = new SleepingQueenPosition(i);
            queenMap.put(sleepingQueenPosition, queens.get(i));
        }
        return queenMap;
    }
}

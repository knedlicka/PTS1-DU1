import java.util.HashMap;
import java.util.Map;

public class SleepingQueens extends QueenCollection {

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

import java.util.HashMap;
import java.util.Map;

public class SleepingQueens extends QueenCollection {
    public SleepingQueens() {
        super();
        for(int i = 0; i < 12; i++) {
            queens.add(new Queen(1)); // TODO zistit ake body maju mat kralovne
        }
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

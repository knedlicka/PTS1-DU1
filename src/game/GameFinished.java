package game;

import java.util.List;
import java.util.Optional;

public class GameFinished implements GameFinishedStrategy{
    private final List<AwokenQueens> awokenQueensOfPlayers;
    private final Integer numberOfPlayers;
    private final Integer neededPoints;
    private final Integer neededQueens;

    public GameFinished(
            List<AwokenQueens> awokenQueensOfPlayers,
            Integer numberOfPlayers,
            Integer neededPoints,
            Integer neededQueens
    ) {
        this.awokenQueensOfPlayers = awokenQueensOfPlayers;
        this.numberOfPlayers = numberOfPlayers;
        this.neededQueens = neededQueens;
        this.neededPoints = neededPoints;
    }
    @Override
    public Optional<Integer> isFinished() {
        int numberOfAwokenQueens = 0;
        int maxPoints = 0;
        int playerIndex = 0;
        for(int i = 0; i < numberOfPlayers; i++) {
            List<Queen> awokenQueens = awokenQueensOfPlayers.get(i).getListOfQueens();
            int points = 0;
            for(Queen queen : awokenQueens) {
                points += queen.getPoints();
                numberOfAwokenQueens++;
            }
            if(maxPoints < points) {
                maxPoints = points;
                playerIndex = i;
            }
            if(points >= neededPoints || awokenQueens.size() > neededQueens) {
                return Optional.of(i);
            }
        }
        if(numberOfAwokenQueens == 12) {
            return Optional.of(playerIndex);
        }
        return Optional.empty();
    }
}

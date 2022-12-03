package game;

import java.util.List;

public class MoveQueen implements IMoveQueen{
    private static List<AwokenQueens> awokenQueensOfPlayers;
    private static SleepingQueens sleepingQueens;
    private final int playerIndex;
    private final CardType causedBy;

    public MoveQueen(
            List<AwokenQueens> awokenQueensOfPlayers,
            SleepingQueens sleepingQueens,
            int playerIndex,
            CardType causedBy
    ) {
        MoveQueen.awokenQueensOfPlayers = awokenQueensOfPlayers;
        MoveQueen.sleepingQueens = sleepingQueens;
        this.playerIndex = playerIndex;
        this.causedBy = causedBy;
    }

    private boolean moveQueen(Position targetQueen, QueenCollection from, QueenCollection to) {
        Queen queen;
        try {
            queen = from.getQueens().get(targetQueen);
        } catch (Exception e) {
            return false;
        }
        if(from.removeQueen(targetQueen).isEmpty()) {
            return false;
        }
        to.addQueen(queen);
        return true;
    }

    public boolean play(Position targetQueen) {
        if(targetQueen instanceof SleepingQueenPosition) {
            if(causedBy != CardType.King) {
                throw new IllegalArgumentException("Not a king");
            }
            return moveQueen(targetQueen, sleepingQueens, awokenQueensOfPlayers.get(playerIndex));
        }
        if(targetQueen instanceof AwokenQueenPosition) {
            if(causedBy == CardType.SleepingPotion) {
                return moveQueen(targetQueen, awokenQueensOfPlayers.get(playerIndex), sleepingQueens);
            } else if(causedBy == CardType.Knight) {
                return moveQueen(
                    targetQueen,
                    awokenQueensOfPlayers.get(((AwokenQueenPosition) targetQueen).getPlayerIndex()),
                    awokenQueensOfPlayers.get(playerIndex)
                );
            }
        }
        throw new IllegalArgumentException(
            "Illegal combination of causedBy and QueenPosition"
        );
    }
}

public class MoveQueen implements IMoveQueen{
    private AwokenQueens awokenQueens;
    private static SleepingQueens sleepingQueens;
    private Integer playerIndex;

    public MoveQueen(AwokenQueens awokenQueens, SleepingQueens sleepingQueens, Integer playerIndex) {
        this.awokenQueens = awokenQueens;
        MoveQueen.sleepingQueens = sleepingQueens;
        this.playerIndex = playerIndex;
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

    public boolean play(SleepingQueenPosition targetQueen) {
        return moveQueen(targetQueen, sleepingQueens, awokenQueens);
    }

    public boolean play(AwokenQueenPosition targetQueen) {
        return moveQueen(targetQueen, awokenQueens, sleepingQueens);
    }
}

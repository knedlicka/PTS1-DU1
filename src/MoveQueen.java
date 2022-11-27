public class MoveQueen implements IMoveQueen{
    private AwokenQueens awokenQueens;
    private static SleepingQueens sleepingQueens;
    private Integer playerIndex;

    public MoveQueen(AwokenQueens awokenQueens, SleepingQueens sleepingQueens, Integer playerIndex) {
        this.awokenQueens = awokenQueens;
        MoveQueen.sleepingQueens = sleepingQueens;
        this.playerIndex = playerIndex;
    }

    public boolean play(SleepingQueenPosition targetQueen) {
        Queen queen;
        try {
            queen = sleepingQueens.getQueens().get(targetQueen);
        } catch (Exception e) {
            return false;
        }
        if(sleepingQueens.removeQueen(targetQueen).isEmpty()) {
            return false;
        }
        awokenQueens.addQueen(queen);
        return true;
    }

    public boolean play(AwokenQueenPosition targetQueen) {
        Queen queen;
        try {
            queen = awokenQueens.getQueens().get(targetQueen);
        } catch (Exception e) {
            return false;
        }
        if(awokenQueens.removeQueen(targetQueen).isEmpty()) {
            return false;
        }
        sleepingQueens.addQueen(queen);
        return true;
    }
}

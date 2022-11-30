import java.util.List;

public interface IPlayer {
    public boolean play(List<Position> cards);
    public PlayerState getPlayerState();
}

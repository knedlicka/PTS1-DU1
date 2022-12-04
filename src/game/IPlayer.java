package game;

import java.util.List;

public interface IPlayer {
    boolean play(List<IPosition> cards);
    PlayerState getPlayerState();
    IHand getHand();

    Integer getPlayerIndex();

    IQueenCollection getAwokenQueens();
}

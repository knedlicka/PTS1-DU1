package game;

import java.util.List;

public interface IDealer {
    public List<Card> getDrawingPile();
    public List<Queen> getQueensSleeping();
}

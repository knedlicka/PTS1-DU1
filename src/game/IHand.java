package game;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IHand {
    public Optional<List<Card>> pickCards(List<IPosition> positions);
    public Map<IPosition, Card> removePickedCardsAndRedraw();
    public List<Card> returnPickedCards();
    public Optional<IPosition> hasCardOfType(CardType type);
    public List<Card> getCards();
}

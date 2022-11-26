import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IHand {
    public Optional<List<Card>> pickCards(List<HandPosition> positions);
    public Map<HandPosition, Card> removePickedCardsAndRedraw();
    public List<Card> returnPickedCards();
    public Optional<HandPosition> hasCardOfType(CardType type);
    public List<Card> getCards();
}

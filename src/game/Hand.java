package game;

import java.util.*;

public class Hand implements IHand {
    private final Integer playerIdx;
    private static IDrawingAndTrashPile piles;
    private final List<Card> cards;

    private List<Card> pickedCards;
    private List<IPosition> positionsOfPickedCards;

    public Hand(Integer playerIdx, List<Card> cards, IDrawingAndTrashPile piles) {
        this.playerIdx = playerIdx;
        this.cards = cards;
        this.pickedCards = new ArrayList<>();
        this.positionsOfPickedCards = new ArrayList<>();
        Hand.piles = piles;
    }

    @Override
    public Optional<List<Card>> pickCards(List<IPosition> positions) {
        List<Card> newlyPickedCards = new ArrayList<>();
        for(IPosition x : positions) {
            int index = x.getCardIndex();
            try {
                Card c = cards.get(index);
                newlyPickedCards.add(c);
            } catch (IndexOutOfBoundsException e) {
                return Optional.empty();
            }
        }
        positionsOfPickedCards.addAll(positions);
        pickedCards.addAll(newlyPickedCards);
        return Optional.of(newlyPickedCards);
    }

    @Override
    public Map<IPosition, Card> removePickedCardsAndRedraw() {
        List<Card> draw = piles.discardAndDraw(pickedCards);
        int drawIndex = 0;
        for(IPosition x : positionsOfPickedCards) {
            int index = x.getCardIndex();
            cards.set(index, draw.get(drawIndex));
            drawIndex++;
        }
        pickedCards = new ArrayList<>();
        positionsOfPickedCards = new ArrayList<>();

        Map<IPosition, Card> cardArr = new HashMap<>();
        for(int i = 0; i < cards.size(); i++) {
            HandPosition handPosition = new HandPosition(i, playerIdx);
            cardArr.put(handPosition, cards.get(i));
        }
        return cardArr;
    }

    public List<Card> returnPickedCards() {
        return pickedCards;
    }

    @Override
    public Optional<IPosition> hasCardOfType(CardType type) {
        HandPosition hp;
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getType().equals(type)) {
                hp = new HandPosition(i, playerIdx);
                return Optional.of(hp);
            }
        }
        return Optional.empty();
    }

    public List<Card> getCards() {
        return cards;
    }
}

import java.util.*;

public class Player implements IPlayer {
    private Integer playerIndex;
    private final IHand hand;
    private final AwokenQueens awokenQueens;
    private final List<Map<CardType, EvaluateAttack>> evaluateAttackTable;
    private final EvaluateKing evaluateKing;
    private final EvaluateNumberedCards evaluateNumberedCards;

    enum PlayType {
        PlaySleepingPotion,
        PlayKnight,
        PlayKing,
        PlayNumber,
        Invalid
    }

    public Player(
            Hand hand,
            AwokenQueens awokenQueens,
            SleepingQueens sleepingQueens,
            List<Map<CardType, EvaluateAttack>> evaluateAttackTable
    ) {
        this.hand = hand;
        this.awokenQueens = awokenQueens;
        this.evaluateAttackTable = evaluateAttackTable;
        this.evaluateKing = new EvaluateKing(awokenQueens, hand);
        this.evaluateNumberedCards = new EvaluateNumberedCards(hand, playerIndex);
    }

    PlayType determinePlayType(List<Position> cards) {
        boolean containsSleepingPotion = false;
        boolean containsKnight = false;
        boolean containsKing = false;
        boolean containsNumber = false;
        boolean containsSleepingQueen = false;
        boolean containsAwokenQueen = false;

        List<Card> myHandCards = hand.getCards();
        for(Position p: cards) {
            if(p instanceof HandPosition) {
                Card card = myHandCards.get(p.getCardIndex());
                CardType cardType = card.getType();
                if(cardType == CardType.SleepingPotion) {
                    containsSleepingPotion = true;
                } else if(cardType == CardType.Knight) {
                    containsKnight = true;
                } else if(cardType == CardType.King) {
                    containsKing = true;
                } else if(cardType == CardType.Number) {
                    containsNumber = true;
                } else {
                    throw new IllegalArgumentException("Unsupported card choice");
                }
            } else if(p instanceof AwokenQueenPosition) {
                containsAwokenQueen = true;
            } else if(p instanceof SleepingQueenPosition) {
                containsSleepingQueen = true;
            } else {
                throw new IllegalArgumentException("Unsupported position type");
            }
        }
        ArrayList<Boolean> presentTypes = new ArrayList<>();
        presentTypes.add(containsSleepingPotion);
        presentTypes.add(containsKnight);
        presentTypes.add(containsKing);
        presentTypes.add(containsSleepingQueen);
        presentTypes.add(containsAwokenQueen);
        presentTypes.add(containsNumber);
        int typesCnt = 0;
        for(Boolean containsType: presentTypes) {
            if(containsType) {
                typesCnt++;
            }
        }
        if(containsSleepingPotion && containsAwokenQueen && typesCnt == 2) {
            return PlayType.PlaySleepingPotion;
        }
        if(containsKnight && containsAwokenQueen && typesCnt == 2) {
            return PlayType.PlayKnight;
        }
        if(containsKing && containsSleepingQueen && typesCnt == 2) {
            return PlayType.PlayKing;
        }
        if(containsNumber && typesCnt == 1) {
            return PlayType.PlayNumber;
        }
        return PlayType.Invalid;
    }

    @Override
    public boolean play(List<Position> cards) {
        PlayType playType = determinePlayType(cards);

        int opponentIndex = -1;
        Position targetQueen = new AwokenQueenPosition(-1, -1); //nevadi ze toto je AwokenQueenPosition aj ked potom sa tam priradi SleepingQueenPosition
        HandPosition handPosition = new HandPosition(-1, -1);
        List<Card> numberedCards = new ArrayList<>();
        List<Card> cardsOnHand = hand.getCards();
        for(Position pos: cards) {
           if(pos instanceof AwokenQueenPosition) {
               opponentIndex = ((AwokenQueenPosition) pos).getPlayerIndex();
               targetQueen = pos;
           }
           else if(pos instanceof SleepingQueenPosition) {
               targetQueen = pos;
           }
           else if(pos instanceof HandPosition) {
               handPosition = (HandPosition)pos;
               Card currentCard = cardsOnHand.get(pos.getCardIndex());
               if(currentCard.getType() == CardType.Number) {
                   numberedCards.add(currentCard);
               }
           }
        }
        switch (playType) {
            case Invalid:
                return false;
            case PlaySleepingPotion:
                return evaluateAttackTable
                    .get(opponentIndex)
                    .get(CardType.SleepingPotion)
                    .play(targetQueen, opponentIndex);
            case PlayKnight:
                return evaluateAttackTable
                        .get(opponentIndex)
                        .get(CardType.Knight)
                        .play(targetQueen, opponentIndex);
            case PlayKing:
                if (targetQueen instanceof SleepingQueenPosition) {
                    return evaluateKing.play(handPosition, (SleepingQueenPosition)targetQueen);
                }
            case PlayNumber:
                return evaluateNumberedCards.play(numberedCards);
            default:
                return false;
        }
    }

    @Override
    public PlayerState getPlayerState() {
        Map<Integer, Optional<Card>> playerCards = new HashMap<>();
        Map<Integer, Queen> playerAwokenQueens = new HashMap<>();
        int index = 0;
        for(Card card : hand.getCards()) {
            playerCards.put(index, Optional.of(card));
            index++;
        }
        List<Queen> queens = awokenQueens.getListOfQueens();
        for(int i = 0; i < queens.size(); i++) {
            playerAwokenQueens.put(i, queens.get(i));
        }
        return new PlayerState(playerCards, playerAwokenQueens);
    }
}

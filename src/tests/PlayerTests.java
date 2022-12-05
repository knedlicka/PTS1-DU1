package tests;

import game.*;

import java.util.*;

public class PlayerTests {

   private class MockedHand implements IHand {
       private List<Card> cards;
       private List<Integer> pickedCardsIndexes;
       public MockedHand(List<Card> cards) {
           this.cards = cards;
           this.pickedCardsIndexes = new ArrayList<>();
       }

       @Override
       public Optional<List<Card>> pickCards(List<IPosition> positions) {
           pickedCardsIndexes = new ArrayList<>();
           List<Card> pickedCards = new ArrayList<>();
           for(IPosition x : positions) {
                int index = x.getCardIndex();
                pickedCardsIndexes.add(index);
                pickedCards.add(cards.get(index));
            }
           return Optional.of(pickedCards);
       }

       @Override
       public Map<IPosition, Card> removePickedCardsAndRedraw() {
           Map<IPosition, Card> newCards = new HashMap<>();
           for(Integer cardIdx: pickedCardsIndexes) {
               Card drawnCard = new Card(CardType.Number, 10);
               newCards.put(new HandPosition(cardIdx, 0), drawnCard);
               cards.set(cardIdx, drawnCard);
           }
           return newCards;
       }

       @Override
       public List<Card> returnPickedCards() {
           List<Card> pickedCards = new ArrayList<>();
           for(Integer cardIdx: pickedCardsIndexes) {
               pickedCards.add(cards.get(cardIdx));
           }
           return pickedCards;
       }

       @Override
       public Optional<IPosition> hasCardOfType(CardType type) {
           for(int cardIdx = 0; cardIdx < cards.size(); cardIdx++) {
               if(cards.get(cardIdx).getType().equals(type)) {
                   return Optional.of(new HandPosition(cardIdx, 0));
               }
           }
           return Optional.empty();
       }

       @Override
       public List<Card> getCards() {
           return cards;
       }
   }

   private class MockedAwokenQueenPosition implements IPosition {
       private Integer cardIdx;

       public MockedAwokenQueenPosition(Integer cardIdx) {
           this.cardIdx = cardIdx;
       }

       @Override
       public Integer getCardIndex() {
           return cardIdx;
       }
   }

    private class MockedAwokenQueens implements IQueenCollection {
       private List<Queen> queens;
        @Override
        public void addQueen(Queen queen) {
            queens.add(queen);
        }

        @Override
        public Optional<Queen> removeQueen(IPosition position) {
            if(queens.size() > position.getCardIndex()) {
                Queen queen = queens.get(position.getCardIndex());
                queens.remove(position.getCardIndex());
                return Optional.of(queen);
            }
            return Optional.empty();
        }

        @Override
        public List<Queen> getListOfQueens() {
            return queens;
        }

        @Override
        public Map<IPosition, Queen> getQueens() {
            Map<IPosition, Queen> queensMap = new HashMap<>();
            for(int i = 0; i < queens.size(); i++) {
                queensMap.put(new MockedAwokenQueenPosition(i), queens.get(i));
            }
            return queensMap;
        }
    }

    private class MockedEvaluateAttack implements IEvaluateAttack {
        private IHand targetHand;
        private CardType defenseCardType;
        public MockedEvaluateAttack(IHand targetHand, CardType defenseCardType) {
            this.targetHand = targetHand;
            this.defenseCardType = defenseCardType;
        }

        public boolean play(IPosition targetQueen, Integer targetPlayerIndex) {
            if(targetPlayerIndex != 1) {
                throw new IllegalArgumentException("Target player index must be 1");
            }
            Optional<IPosition> defenseCardPosition = targetHand.hasCardOfType(defenseCardType);
            if(defenseCardPosition.isEmpty()) {
                return true;
            }
            List<IPosition> pick = new ArrayList<>();
            pick.add(defenseCardPosition.get());
            targetHand.pickCards(pick);
            targetHand.removePickedCardsAndRedraw();
            return false;
        }
    }

    private class MockedEvaluateNumberedCards implements IEvaluateNumberedCards {
        @Override
        public boolean play(List<Card> cards, List<IPosition> handPositions) {
            for(IPosition handPosition: handPositions) {
                if(!cards.get(handPosition.getCardIndex()).getType().equals(CardType.Number)) {
                    return false;
                }
            }
            return true;
        }
    }

    private class MockedEvaluateKing implements IEvaluateKing {
        @Override
        public boolean play(HandPosition handPosition, SleepingQueenPosition sleepingQueenPosition) {
            return handPosition.getCardIndex() == 0;
        }
    }

    private Player player;

    private void createPlayer(List<Card> playerCards, List<Card> opponentCards) {
        MockedHand hand = new MockedHand(playerCards);
        MockedHand opponentHand = new MockedHand(opponentCards);
        MockedAwokenQueens awokenQueens = new MockedAwokenQueens();
        List<Map<CardType, IEvaluateAttack>> evaluateAttackTable = List.of(
                Map.of(
                        CardType.Knight, new MockedEvaluateAttack(hand, CardType.Dragon),
                        CardType.SleepingPotion, new MockedEvaluateAttack(hand, CardType.MagicWand)
                ),
                Map.of(
                        CardType.Knight, new MockedEvaluateAttack(opponentHand, CardType.Dragon),
                        CardType.SleepingPotion, new MockedEvaluateAttack(opponentHand, CardType.MagicWand)
                )
        );
        MockedEvaluateKing evaluateKing = new MockedEvaluateKing();
        MockedEvaluateNumberedCards evaluateNumberedCards = new MockedEvaluateNumberedCards();
        player = new Player(hand, awokenQueens, evaluateAttackTable, evaluateKing, evaluateNumberedCards);
    }

    private void testPlayAttack(List<Card> attackingCards, List<Card> opponentCards, boolean expectedAttackSuccessful) {
        createPlayer(attackingCards, opponentCards);
        boolean attackSuccesful = player.play(
                List.of(
                        new HandPosition(0, 0),
                        new AwokenQueenPosition(0, 1)
                )
        );
        String testName = "testPlayAttack " + attackingCards.get(0).getType().name() + " vs " + opponentCards.get(0).getType().name();
        if(attackSuccesful == expectedAttackSuccessful) {
            System.out.println("    [PASSED] " + testName);
        } else {
            System.err.println("    [FAILED] " + testName);
        }
    }

    private void testAttacks() {
        // Test attacks
        ArrayList<Card> attackingCards = new ArrayList<>();
        ArrayList<Card> defendingCards = new ArrayList<>();
        attackingCards.add(new Card(CardType.Knight, 10));
        defendingCards.add(new Card(CardType.Knight, 10));
        testPlayAttack(attackingCards, defendingCards, true);

        defendingCards = new ArrayList<>();
        defendingCards.add(new Card(CardType.Number, 10));
        testPlayAttack(attackingCards, defendingCards, true);

        defendingCards = new ArrayList<>();
        defendingCards.add(new Card(CardType.Dragon, 10));
        testPlayAttack(attackingCards, defendingCards, false);

        attackingCards = new ArrayList<>();
        attackingCards.add(new Card(CardType.SleepingPotion, 10));
        defendingCards = new ArrayList<>();
        defendingCards.add(new Card(CardType.Knight, 10));
        testPlayAttack(attackingCards, defendingCards, true);

        defendingCards = new ArrayList<>();
        defendingCards.add(new Card(CardType.Number, 10));
        testPlayAttack(attackingCards, defendingCards, true);

        defendingCards = new ArrayList<>();
        defendingCards.add(new Card(CardType.MagicWand, 10));
        testPlayAttack(attackingCards, defendingCards, false);
    }

    private void testNumberedCardsSuccess() {
        ArrayList<Card> playerCards = new ArrayList<>();
        for(int i = 1; i <= 3; i++) {
            playerCards.add(new Card(CardType.Number, i));
        }
        createPlayer(playerCards, new ArrayList<>());
        boolean playedSuccessfully = player.play(List.of(
                new HandPosition(0, 0),
                new HandPosition(1, 0),
                new HandPosition(2, 0)
        ));
        if(!playedSuccessfully) {
            System.err.println("    [FAILED] testNumberedCardsSuccess");
        } else {
            System.out.println("    [PASSED] testNumberedCardsSuccess");
        }
    }

    private void testNumberedCardsFail() {
        ArrayList<Card> playerCards = new ArrayList<>();
        for(int i = 1; i <= 2; i++) {
            playerCards.add(new Card(CardType.Number, i));
        }
        playerCards.add(new Card(CardType.Knight, 10));
        createPlayer(playerCards, new ArrayList<>());
        boolean playedSuccessfully = player.play(List.of(
                new HandPosition(0, 0),
                new HandPosition(1, 0),
                new HandPosition(2, 0)
        ));
        if(playedSuccessfully) {
            System.err.println("    [FAILED] testNumberedCardsFail");
        } else {
            System.out.println("    [PASSED] testNumberedCardsFail");
        }
    }

    private void testNumberedCards() {
        testNumberedCardsSuccess();
        testNumberedCardsFail();
    }

    private void testKingSuccess() {
        ArrayList<Card> playerCards = new ArrayList<>();
        playerCards.add(new Card(CardType.King, 10));
        createPlayer(playerCards, new ArrayList<>());
        boolean playedSuccessfully = player.play(List.of(
                new HandPosition(0, 0),
                new SleepingQueenPosition(0)
        ));
        if(!playedSuccessfully) {
            System.err.println("    [FAILED] testKingSuccess");
        } else {
            System.out.println("    [PASSED] testKingSuccess");
        }
    }

    private void testKingFail() {
        ArrayList<Card> playerCards = new ArrayList<>();
        playerCards.add(new Card(CardType.King, 10));
        playerCards.add(new Card(CardType.Knight, 10));
        createPlayer(playerCards, new ArrayList<>());
        boolean playedSuccessfully = player.play(List.of(
                new HandPosition(1, 0),
                new SleepingQueenPosition(0)
        ));
        if(playedSuccessfully) {
            System.err.println("    [FAILED] testKingFail");
        } else {
            System.out.println("    [PASSED] testKingFail");
        }
    }

    private void testKing() {
        testKingSuccess();
        testKingFail();
    }

    public void runTests() {
        System.out.println("Running PlayerTests:");
        testAttacks();
        testNumberedCards();
        testKing();
    }
}

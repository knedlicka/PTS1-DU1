package game;

import java.util.*;

public class Game implements IGame {
    private final int numberOfPlayers;
    private final List<IPlayer> players;
    private final IDrawingAndTrashPile drawingAndTrashPile;
    private final IQueenCollection sleepingQueens;
    private int playerOnTurn;

    public Game(int numberOfPlayers, List<Card> drawingPile, List<Queen> queensSleeping, IDrawingStrategy drawingStrategy) {
        this.numberOfPlayers = numberOfPlayers;
        playerOnTurn = 0;
        drawingAndTrashPile = new DrawingAndTrashPile(drawingPile, new ArrayList<>(), drawingStrategy);
        sleepingQueens = new SleepingQueens(queensSleeping);
        List<IHand> hands = new ArrayList<>();
        for (int playerIndex = 0; playerIndex < numberOfPlayers; playerIndex++) {
            List<Card> handCards = drawingAndTrashPile.drawCards(5);
            IHand hand = new Hand(playerIndex, handCards, drawingAndTrashPile);
            hands.add(hand);
        }

        List<IQueenCollection> allPlayersAwokenQueens = new ArrayList<>();
        for (int playerIdx = 0; playerIdx < numberOfPlayers; playerIdx++) {
            allPlayersAwokenQueens.add(new AwokenQueens(playerIdx));
        }

        List<Map<CardType, IEvaluateAttack>> playersEvaluateAttackTable = new ArrayList<>();
        for (int myIndex = 0; myIndex < numberOfPlayers; myIndex++) {
            List<List<CardType>> attackWithDefenseCardDoubles = List.of(
                    List.of(CardType.SleepingPotion, CardType.MagicWand),
                    List.of(CardType.Knight, CardType.Dragon)
            );
            Map<CardType, IEvaluateAttack> attackIthPlayer = new HashMap<>();
            for (int opponentIdx = 0; opponentIdx < numberOfPlayers; opponentIdx++) {
                for (List<CardType> attackWithDefenseCardDouble : attackWithDefenseCardDoubles) {
                    CardType attackCardType = attackWithDefenseCardDouble.get(0);
                    CardType defenseCardType = attackWithDefenseCardDouble.get(1);
                    IMoveQueen moveQueen = new MoveQueen(
                            allPlayersAwokenQueens, sleepingQueens, opponentIdx, attackCardType
                    );
                    attackIthPlayer.put(
                            attackCardType,
                            new EvaluateAttack(
                                    hands.get(opponentIdx),
                                    attackCardType,
                                    defenseCardType,
                                    moveQueen
                            )
                    );
                }
            }
            playersEvaluateAttackTable.add(attackIthPlayer);
        }

        this.players = new ArrayList<>();
        for (int playerIdx = 0; playerIdx < numberOfPlayers; playerIdx++) {
            IEvaluateKing evaluateKing = new EvaluateKing(allPlayersAwokenQueens.get(playerIdx), hands.get(playerIdx), sleepingQueens);
            IEvaluateNumberedCards evaluateNumberedCards = new EvaluateNumberedCards(hands.get(playerIdx), playerIdx);
            players.add(new Player(
                    hands.get(playerIdx),
                    allPlayersAwokenQueens.get(playerIdx),
                    playersEvaluateAttackTable,
                    evaluateKing,
                    evaluateNumberedCards
            ));
        }
    }

    GameState getGameState(List<Card> cardsDiscardedLastTurn){
        Set<SleepingQueenPosition> sleepingQueenPositions = new HashSet<>();
        for(IPosition p: sleepingQueens.getQueens().keySet()) {
            sleepingQueenPositions.add((SleepingQueenPosition) p);
        }
        Map<HandPosition, Optional<Card>> handCards = new HashMap<>();
        for(IPlayer p: players) {
            for(int i = 0; i < p.getHand().getCards().size(); i++) {
                handCards.put(new HandPosition(p.getPlayerIndex(), i), Optional.of(p.getHand().getCards().get(i)));
            }
        }
        Map<AwokenQueenPosition, Queen> awokenQueens = new HashMap<>();
        for(IPlayer p: players) {
            for(IPosition pos: p.getAwokenQueens().getQueens().keySet()) {
                awokenQueens.put((AwokenQueenPosition) pos, p.getAwokenQueens().getQueens().get(pos));
            }
        }
        return new GameState(
                numberOfPlayers,
                playerOnTurn,
                sleepingQueenPositions,
                handCards,
                awokenQueens,
                cardsDiscardedLastTurn
        );
    }

    @Override
    public Optional<GameState> play(Integer playerIdx, List<IPosition> cards) {
        if (playerIdx != playerOnTurn) {
            return Optional.empty();
        }
        IPlayer player = players.get(playerIdx);
        if(player.play(cards)){
            playerOnTurn = (playerOnTurn + 1) % numberOfPlayers;
            return Optional.of(getGameState(drawingAndTrashPile.getCardsDiscardedThisTurn()));
        }
        return Optional.empty();
    }


}

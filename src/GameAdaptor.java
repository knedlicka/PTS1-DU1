import java.util.*;

public class GameAdaptor implements IGamePlayer{
    private final IGame game;
    private final Map<String, Integer> playerNames;
    private final GameObservable observable;
    public GameAdaptor(Integer numberOfPlayers, List<String> playerNames, GameObservable observable, IDrawingStrategy drawingStrategy) {
        IDealer dealer = new Dealer();
        game = new Game(numberOfPlayers, dealer.getDrawingPile(), dealer.getQueensSleeping(), drawingStrategy);
        this.playerNames = new HashMap<>();
        for(int i = 0; i < playerNames.size(); i++){
            this.playerNames.put(playerNames.get(i), i);
        }
        this.observable = observable;
    }

    @Override
    public String play(String player, String cards) {
        if(!playerNames.containsKey(player)){
            return "Player not found";
        }
        List<Position> positions = new ArrayList<>();
        for(int i = 0; i < cards.length(); i++){
            if(cards.charAt(i) == 'h' && i + 1 < cards.length() && Character.isDigit(cards.charAt(i + 1))){
                positions.add(new HandPosition(playerNames.get(player), Integer.parseInt(cards.charAt(i + 1) + "")));
                i++;
            }
            else if(cards.charAt(i) == 's' && i + 1 < cards.length() && Character.isDigit(cards.charAt(i + 1))){
                positions.add(new SleepingQueenPosition(Integer.parseInt(cards.charAt(i + 1) + "")));
                i++;
            }
            else if(cards.charAt(i) == 'a' && i + 1 < cards.length() && Character.isDigit(cards.charAt(i + 1))){
                positions.add(new AwokenQueenPosition(playerNames.get(player), Integer.parseInt(cards.charAt(i + 1) + "")));
                i++;
            }
            else if(cards.charAt(i) == ' ') {
                continue;
            }
            else{
                return "Invalid input";
            }
        }

        Optional<GameState> gameState = game.play(playerNames.get(player), positions);
        gameState.ifPresent(observable::notifyAll);
        return gameState.toString();
    }
}

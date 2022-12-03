import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameAdaptor implements IGamePlayer{
    private final IGame game;
    private final Map<String, Integer> playerNames;
    public GameAdaptor(Integer numberOfPlayers, List<String> playerNames){
        IDealer dealer = new Dealer();
        game = new Game(numberOfPlayers, dealer.getDrawingPile(), dealer.getQueensSleeping());
        this.playerNames = new HashMap<>();
        for(int i = 0; i < playerNames.size(); i++){
            this.playerNames.put(playerNames.get(i), i);
        }
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
            }
            else if(cards.charAt(i) == 's' && i + 1 < cards.length() && Character.isDigit(cards.charAt(i + 1))){
                positions.add(new SleepingQueenPosition(Integer.parseInt(cards.charAt(i + 1) + "")));
            }
            else if(cards.charAt(i) == 'a' && i + 1 < cards.length() && Character.isDigit(cards.charAt(i + 1))){
                positions.add(new AwokenQueenPosition(playerNames.get(player), Integer.parseInt(cards.charAt(i + 1) + "")));
            }
            else if(cards.charAt(i) == ' ') {
                continue;
            }
            else{
                return "Invalid input";
            }
        }
        return game.play(playerNames.get(player), positions).toString();
    }
}

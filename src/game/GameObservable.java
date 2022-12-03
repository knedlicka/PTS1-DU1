package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameObservable {
    private final List<IGameObserver> observers = new ArrayList<>();
    private final Map<Integer, IGameObserver> playerObservers = new HashMap<>();

    public void addObserver(IGameObserver observer) {
        observers.add(observer);
    }

    public void notifyAll(GameState message) {
        for(IGameObserver observer : observers) {
            observer.notify(message.toString());
        }
        String newMessage = "";
        for(Integer index: playerObservers.keySet()) {
            newMessage += message.getOnTurn().toString();
            newMessage += " ";
            newMessage += message.getSleepingQueens().toString();
            newMessage += " ";
            newMessage += message.getCards(index).toString();
            newMessage += " ";
            newMessage += message.getAwokenQueens(index).toString();
            newMessage += " ";
            playerObservers.get(index).notify(newMessage);
        }
    }

    public void addPlayer(Integer playerIndex, IGameObserver observer) {
        playerObservers.put(playerIndex, observer);
        observers.add(playerIndex, observer);
    }

    public void remove(IGameObserver observer) {
        observers.remove(observer);
    }
}

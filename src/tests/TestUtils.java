package tests;

import game.Card;
import game.CardType;
import game.IPosition;
import game.Queen;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestUtils {
    public static boolean mapContainsAnotherMap(Map<IPosition, Queen> a, Map<IPosition, Queen> b) {
        for(Map.Entry<IPosition, Queen> entryA : a.entrySet()) {
            boolean contains = false;
            for(Map.Entry<IPosition, Queen> entryB: b.entrySet()) {
                if(entryA.getKey().equals(entryB.getKey()) && entryA.getValue().equals(entryB.getValue())) {
                    contains = true;
                    break;
                }
            }
            if(!contains) {
                return false;
            }
        }
        return true;
    }

    public static boolean containsAll(Map<IPosition, Queen> a, Map<IPosition, Queen> b) {
        return mapContainsAnotherMap(a, b) && mapContainsAnotherMap(b, a);
    }

    public static ArrayList<Card> makeListOfNumberedCards(List<Integer> values) {
        ArrayList<Card> cards = new ArrayList<>();
        for (Integer value : values) {
            cards.add(new Card(CardType.Number, value));
        }
        return cards;
    }
}

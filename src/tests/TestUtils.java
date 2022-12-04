package tests;

import game.Position;
import game.Queen;

import java.util.Map;

public class TestUtils {
    public static boolean mapContainsAnotherMap(Map<Position, Queen> a, Map<Position, Queen> b) {
        for(Map.Entry<Position, Queen> entryA : a.entrySet()) {
            boolean contains = false;
            for(Map.Entry<Position, Queen> entryB: b.entrySet()) {
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

    public static boolean containsAll(Map<Position, Queen> a, Map<Position, Queen> b) {
        return mapContainsAnotherMap(a, b) && mapContainsAnotherMap(b, a);
    }
}

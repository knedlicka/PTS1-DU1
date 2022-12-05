package tests;

import game.GameObservable;
import game.GameState;
import game.IGameObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GameObservableTests {

    private class MockedObserver implements IGameObserver {
        public boolean wasNotified = false;

        @Override
        public void notify(String message) {
            wasNotified = true;
        }

        public boolean notifyWasCalled() {
            return wasNotified;
        }
    }

    private void testAddObserver() {
        GameObservable observable = new GameObservable();
        MockedObserver observer = new MockedObserver();
        observable.addObserver(observer);
        observable.notifyAll(new GameState(0, 0, new HashSet<>(), new HashMap<>(), new HashMap<>(), new ArrayList<>()));
        if(observer.notifyWasCalled()) {
            System.out.println("    [PASSED] testAddObserver");
        } else {
            System.out.println("    [FAILED] testAddObserver");
        }
    }

    private void testRemoveObserver() {
        GameObservable observable = new GameObservable();
        MockedObserver observer = new MockedObserver();
        observable.addObserver(observer);
        observable.remove(observer);
        observable.notifyAll(new GameState(0, 0, new HashSet<>(), new HashMap<>(), new HashMap<>(), new ArrayList<>()));
        if(!observer.notifyWasCalled()) {
            System.out.println("    [PASSED] testAddObserver");
        } else {
            System.out.println("    [FAILED] testAddObserver");
        }
    }

    public void runTests() {
        System.out.println("Running GameObservableTests");
        testAddObserver();
        testRemoveObserver();
    }

}

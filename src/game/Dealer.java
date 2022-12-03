package game;

import java.util.ArrayList;
import java.util.List;

public class Dealer implements IDealer {
    private final List<Card> drawingPile;
    private final List<Queen> queensSleeping;

    public Dealer() {
        drawingPile = new ArrayList<>();
        // Add Kings
        for(int i = 0; i < 8; i++) {
            drawingPile.add(new Card(CardType.King, 0));
        }
        // Add Knights
        for(int i = 0; i < 4; i++) {
            drawingPile.add(new Card(CardType.Knight, 0));
        }
        // Add SleepingPotions
        for(int i = 0; i < 4; i++) {
            drawingPile.add(new Card(CardType.SleepingPotion, 0));
        }
        // Add MagicWands
        for(int i = 0; i < 3; i++) {
            drawingPile.add(new Card(CardType.MagicWand, 0));
        }
        // Add Dragons
        for(int i = 0; i < 3; i++) {
            drawingPile.add(new Card(CardType.Dragon, 0));
        }
        // Add Numbers
        for(int i = 1; i < 11; i++) {
            for(int j = 0; j < 4; j++) {
                drawingPile.add(new Card(CardType.Number, i));
            }
        }

        queensSleeping = new ArrayList<>();
        // Add Queens
        for(int i = 0; i < 4; i++) {
            queensSleeping.add(new Queen(5));
        }
        for (int i = 0; i < 4; i++) {
            queensSleeping.add(new Queen(10));
        }
        for (int i = 0; i < 3; i++) {
            queensSleeping.add(new Queen(15));
        }
        queensSleeping.add(new Queen(20));
    }

    public List<Card> getDrawingPile() {
        return drawingPile;
    }

    public List<Queen> getQueensSleeping() {
        return queensSleeping;
    }
}

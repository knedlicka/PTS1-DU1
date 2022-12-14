package tests;

public class TestRunner {
    public static void runTests() {
        DrawingStrategy1Tests drawingStrategy1Tests = new DrawingStrategy1Tests();
        drawingStrategy1Tests.runTests();
        DrawingStrategy2Tests drawingStrategy2Tests = new DrawingStrategy2Tests();
        drawingStrategy2Tests.runTests();
        HandTests handTests = new HandTests();
        handTests.runTests();
        SleepingQueensTests sleepingQueensTests = new SleepingQueensTests();
        sleepingQueensTests.runTests();
        AwokenQueensTests awokenQueensTests = new AwokenQueensTests();
        awokenQueensTests.runTests();
        MoveQueenTests moveQueenTests = new MoveQueenTests();
        moveQueenTests.runTests();
        EvaluateAttackTests evaluateAttackTests = new EvaluateAttackTests();
        evaluateAttackTests.runTests();
        PlayerTests playerTests = new PlayerTests();
        playerTests.runTests();
        EvaluateNumberedCardsTests evaluateNumberedCardsTests = new EvaluateNumberedCardsTests();
        evaluateNumberedCardsTests.runTests();
        GameTests gameTests = new GameTests();
        gameTests.runTests();
        EvaluateKingTests evaluateKingTests = new EvaluateKingTests();
        evaluateKingTests.runTests();
        GameFinishedTests gameFinishedTests = new GameFinishedTests();
        gameFinishedTests.runTests();
        GameAdaptorTests gameAdaptorTests = new GameAdaptorTests();
        gameAdaptorTests.runTests();
        GameObservableTests gameObservableTests = new GameObservableTests();
        gameObservableTests.runTests();
    }
}

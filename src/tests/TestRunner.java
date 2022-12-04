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
    }
}

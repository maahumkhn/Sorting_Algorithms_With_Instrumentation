// Maahum Khan — Assignment 3 — SOFT 437
// Testing the overhead of Instrumentation class.

public class TestOverhead {
    private static Instrumentation instance = Instrumentation.Instance();
    
    // Testing the overhead of the instrumentation class itself
    public static void main(String[] args) {
        int cuteLizard = 1;
        int cuteCats = 10;
        instance.activate(true);
        instance.comment("Instrumentation Overhead Test\n");

        // Calculate execution time in this function and compare to
        // startTiming & stopTiming for approx. overhead calculation
        long startFirstStart, startMain, endMain, startLoop, endLoop;
        
        // Do random stuff to measure overhead of instrumentation class
        startFirstStart = System.nanoTime();
        instance.startTiming("Main");
        startMain = System.nanoTime();

        instance.startTiming("Loop");
        startLoop = System.nanoTime();

        for (int i = 1; i < 10000; i++) {
            cuteLizard = i + cuteCats;
            cuteCats = cuteLizard + 5*i;

            cuteLizard = cuteLizard % 25654;
            cuteCats = cuteCats - i%33333;
        }

        instance.stopTiming("Loop");
        endLoop = System.nanoTime();
        instance.stopTiming("Main");
        endMain = System.nanoTime();

        instance.dump("overheadTest.log");

        // System time calculations
        float startTimingCall = (float)((startMain - startFirstStart)/1e6);
        float mainTime = (float)((endMain - startMain)/1e6);
        float loopTime = (float)((endLoop - startLoop)/1e6);
        float stopTimingCall = (float)((endMain - endLoop)/1e6);

        System.out.println("Time calculated for a startTiming call: " + startTimingCall + " ms");
        System.out.println("Time calculated for a stopTiming call: " + stopTimingCall + " ms");
        System.out.println("Time calculated for main: " + mainTime + " ms");
        System.out.println("Time calculated for loop: " + loopTime + " ms");
    }

}

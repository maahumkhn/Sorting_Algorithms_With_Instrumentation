// Maahum Khan — Assignment 3 — SOFT 437
// Testing the original and instrumented
// versions of bubble sort and quick sort.

import java.util.Random;


public class TestSorting {
    public static Instrumentation instance = Instrumentation.Instance();

    public static int[] populateArray(int size) {
        int[] array = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(99999) + 1;
        }
        
        return array;
    }

    // Bubble sort without instrumentation in sort class
    public static int[] bubbleSortTest(int[] array) {
        instance.startTiming("Bubble Sort");
        BubbleSort.bubbleSort(array, array.length);
        instance.stopTiming("Bubble Sort");

        return array;
    }

    // Quick sort without instrumentation in sort class
    public static int[] quickSortTest(int[] array) {
        instance.startTiming("Quick Sort");
        QuickSort.quickSort(array, 0, (array.length - 1));
        instance.stopTiming("Quick Sort");

        return array;
    }

    public static void main(String[] args) {

        // Since sorting time value results were quite different for 
        // small VS large array sizes, create logs for both 
        // examples. Results discussed in report.
        int[] numElements = {20, 10000};

        for (int i : numElements) {

            instance.activate(true);
            instance.comment("Test for Array Sizes of " + i + "\n");
            instance.startTiming("MAIN");

            // Get running time for populateArray()
            instance.startTiming("Populate Array");
            int[] popTestArray = populateArray(i);
            instance.stopTiming("Populate Array");

            // Get normal bubble sort time
            int[] arrayB = populateArray(i);
            arrayB = bubbleSortTest(arrayB);

            // Get normal quick sort time
            int[] arrayQ = populateArray(i);
            arrayQ = quickSortTest(arrayQ);

            // Get instrumented bubble sort time
            int[] arrayInsB = populateArray(i);
            InstrumentedBubbleSort.bubbleSort(arrayInsB, arrayInsB.length);

            // Get instrumented quick sort time
            int[] arrayInsQ = populateArray(i);
            InstrumentedQuickSort.doQuickSort(arrayInsQ, 0, (arrayInsQ.length - 1));

            instance.stopTiming("MAIN");
            instance.dump("sortTests" + i + ".log");

            // Reset instance so that 10000 test's log doesn't include 20's log
            if (i == 20) instance.reset();
        }
    }
}
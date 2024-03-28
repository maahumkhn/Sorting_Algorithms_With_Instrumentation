// Maahum Khan — Assignment 3 — SOFT 437
// Instrumented Bubble Sort class 
 
import java.io.*;
 
public class InstrumentedBubbleSort {
    public static Instrumentation instance = Instrumentation.Instance();
     
    // An optimized version of Bubble Sort
    public static void bubbleSort(int arr[], int n)
    {
        instance.startTiming("Instrumented Bubble Sort");
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                     
                    // Swap arr[j] and arr[j+1]
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
 
            // If no two elements were
            // swapped by inner loop, then break
            if (swapped == false)
                break;
        }

        instance.stopTiming("Instrumented Bubble Sort");
    }
}
 
// This code is contributed
// by Nikita Tiwari.
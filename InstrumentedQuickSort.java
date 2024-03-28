// Maahum Khan — Assignment 3 — SOFT 437
// Instrumented Quicksort class 

public class InstrumentedQuickSort {
    public static Instrumentation instance = Instrumentation.Instance();

    // A utility function to swap two elements
    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
 
    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
 
        int i = (low - 1);
 
        for (int j = low; j <= high - 1; j++) {
 
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }
 
    // The main function that implements QuickSort
    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
 
            // pi is partitioning index
            int pi = partition(arr, low, high);
 
            // Separately sort elements before partition and after
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    static void doQuickSort(int[] arr, int low, int high) {
        instance.startTiming("Instrumented Quick Sort");
        quickSort(arr, low, high);
        instance.stopTiming("Instrumented Quick Sort");
    }
}

// This code is contributed by Ayush Choudhary
// Improved by Ajay Virmoti
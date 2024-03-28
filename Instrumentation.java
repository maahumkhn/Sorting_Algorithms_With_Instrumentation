// Maahum Khan — 20232476 — SOFT437 Assignment 3
// Instrumentation class

import java.util.Stack;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


public class Instrumentation {
    private static boolean isActive;
    private Stack<Long> startTimes = new Stack<Long>();
    private Stack<String> tabs = new Stack<String>();
    private ArrayList<String> dumpLog = new ArrayList<String>();
    private long initialTime = 0;

    private static Instrumentation instance = new Instrumentation();
    
    // Class constructor. Sets active status to false as default
    private Instrumentation() {
        isActive = false;
    }

    // Instantiation function. Returns instance of the class.
    public static Instrumentation Instance() {
        return instance;
    }

    // Change activation status
    public void activate(boolean active) {
        isActive = active;
    }

    // Get active status for use of this class in other classes
    public boolean getActiveStatus() {
        return isActive;
    }
    
    // Starting timer for specified activity (comment)
    public void startTiming(String comment) {
        if (isActive == true) {
            // Add start time to stack as type long
            long startTime = System.nanoTime();
            startTimes.push(startTime);

            // Add tabs for dump output format
            String tempTab = "";
            int iters = startTimes.size();
            for (int i = 1; i < iters; i++) {
                tempTab = tempTab + "|  ";
            }
            tabs.push(tempTab);
            dumpLog.add(tempTab + "START TIMING: " + comment);

            // If first time starting timer, store value for total time calculation
            if (iters == 1) 
                initialTime = startTimes.elementAt(0);
        }
    }

    // End timer for specified activity (comment)
    public void stopTiming(String comment) {
        if (isActive && !startTimes.isEmpty()) {
            // Calculate time difference, subtract started time from current end time, in ms
             long endTime = System.nanoTime();
             float timeDiff = (float)((endTime - startTimes.pop()) / 1e6);
             dumpLog.add(tabs.pop() + "STOP TIMING: " + comment + " " + timeDiff + " ms");
        }
    }

    // Add a comment to log
    public void comment(String comment) {
        if (isActive) {
            dumpLog.add("COMMENT: " + comment);
        }
    }

    // Dump everything into log file 
    public void dump(String filename) {
        if (isActive) {
            long finalTime = System.nanoTime();
            // Create output file 
            if (filename == "NULL" || filename == "null" || filename == null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("ddyyMMddHHmmss");
                String formattedDate = dateFormat.format(new Date());
                filename = "instrumentation" + formattedDate + ".log";
            }

            File file = new File(filename);

            try {
                PrintWriter logWriter = new PrintWriter(file);
                for (int i = 0; i < dumpLog.size(); i++) {
                    // Print log to output file
                    logWriter.println(dumpLog.get(i));
                }

                // Add total time at end
                float totalTime = (float)((finalTime - initialTime) / 1e6);
                logWriter.println("TOTAL TIME: " + totalTime + " ms");

                // Close file
                logWriter.close();
            } catch (IOException e) {
                System.out.println("ERROR: File I/O Error");
            }
        } else {
            System.out.println("ERROR DUMPING: Not active");
        }
    }

    // Reset the state of the instrumentation for a new test iteration
    public void reset() {
        isActive = false;
        startTimes.clear();
        tabs.clear();
        dumpLog.clear();
        initialTime = 0;
    }

} // End of Instrumentation class
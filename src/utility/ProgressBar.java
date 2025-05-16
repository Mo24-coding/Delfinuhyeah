package utility;

import java.awt.*;

// Funny Funny stuff - loading bar
public class ProgressBar {

        // Simple method with a loop that generates a loading bar
        public static void runProgressBar(int totalSteps) throws InterruptedException {

            for (int current = 0; current <= totalSteps; current++) {
                int progress = (current * 100) / totalSteps;
                String bar = "■".repeat(progress / 2);
                String progressLine = Colors.red("Progress: ") + "[" + Colors.green(bar) + "] " +
                        Colors.yellow(progress + "%") + "\r";
                System.out.print(progressLine);
                Thread.sleep(25); // Adjust speed between 1-100
            }
            System.out.println();
        }
}

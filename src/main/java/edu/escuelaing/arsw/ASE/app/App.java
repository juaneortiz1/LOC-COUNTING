package edu.escuelaing.arsw.ASE.app;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class App {

    /**
     * Main method to run the application.
     *
     * @param args Command line arguments: option (phy or loc) and file/pattern.
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("How to use it: java -jar LOC-COUNTING.jar (phy or loc) (file or pattern)");
            return;
        }

        String option = args[0];
        String filePathPattern = args[1];

        try {
            FileProcessor fileProcessor = new FileProcessor();
            List<Path> files = fileProcessor.findFiles(filePathPattern);

            int totalLines = 0;

            for (Path file : files) {
                int count = 0;
                if (option.equals("phy")) {
                    count = LineCounter.countPhysicalLines(file);
                } else if (option.equals("loc")) {
                    count = LineCounter.countLogicalLines(file);
                } else {
                    System.out.println("Invalid option: " + option);
                    return;
                }
                totalLines += count;
                System.out.println(file + ": " + count + " lines");
            }

            System.out.println("Total " + ((option.equals("phy")) ? "Physic" : "Logic") + " Lines: " + totalLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


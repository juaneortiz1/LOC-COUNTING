package edu.escuelaing.arsw.ASE.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
/**
 * Class for counting lines in a file.
 */
public class LineCounter {
    /**
     * Counts the physical lines in the given file.
     *
     * @param file Path to the file.
     * @return Number of physical lines.
     * @throws IOException If an I/O error occurs.
     */
    public static int countPhysicalLines(Path file) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            return (int) reader.lines().count();
        }
    }

    /**
     * Counts the logical lines in the given file (excluding comments and empty lines).
     *
     * @param file Path to the file.
     * @return Number of logical lines.
     * @throws IOException If an I/O error occurs.
     */
    public static int countLogicalLines(Path file) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            return (int) reader.lines()
                    .filter(line -> !line.trim().isEmpty() && !line.trim().startsWith("//") && !line.trim().startsWith("/*") && !line.trim().startsWith("*"))
                    .count();
        }
    }
}
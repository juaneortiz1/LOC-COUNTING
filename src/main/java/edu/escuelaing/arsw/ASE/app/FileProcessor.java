package edu.escuelaing.arsw.ASE.app;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class for processing files and directories.
 */
public class FileProcessor {

    /**
     * Finds files matching the given pattern, and also reformats path to avoid inconvinients in windows.
     *
     * @param pattern Glob pattern to match files.
     * @return List of matching file paths.
     * @throws IOException If an I/O error occurs.
     */
    public List<Path> findFiles(String pattern) throws IOException {
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern.replace("\\", "/"));
        Path startingDir = Paths.get(".").toAbsolutePath().normalize();

        try (Stream<Path> paths = Files.walk(startingDir)) {
            return paths.filter(Files::isRegularFile)
                    .filter(path -> matcher.matches(startingDir.relativize(path).normalize()))
                    .collect(Collectors.toList());
        }
    }
}




package edu.escuelaing.arsw.ASE.app;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FileProcessorTest {

    @Test
    public void testFindFilesWithSingleFile() throws IOException {
        FileProcessor fileProcessor = new FileProcessor();
        List<Path> files = fileProcessor.findFiles("src/test/resources/testFile.java");

        assertEquals(1, files.size());
        assertEquals(Paths.get("src/test/resources/testFile.java").toAbsolutePath().normalize(), files.get(0).toAbsolutePath().normalize());
    }

    @Test
    public void testFindFilesWithWildcardPattern() throws IOException {
        FileProcessor fileProcessor = new FileProcessor();
        List<Path> files = fileProcessor.findFiles("src/test/resources/*.java");

        assertEquals(1, files.size());
        assertEquals(Paths.get("src/test/resources/testFile.java").toAbsolutePath().normalize(), files.get(0).toAbsolutePath().normalize());
    }

    @Test
    public void testFindFilesRecursively() throws IOException {
        FileProcessor fileProcessor = new FileProcessor();
        List<Path> files = fileProcessor.findFiles("src/test/**/*.java");

        assertEquals(4, files.size());
        assertEquals(Paths.get("src/test/resources/testFile.java").toAbsolutePath().normalize(), files.get(3).toAbsolutePath().normalize());
    }
}


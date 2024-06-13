package edu.escuelaing.arsw.ASE.app;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class AppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testAppWithPhysicalLines() throws IOException {
        String[] args = {"phy", "src/test/resources/testFile.java"};
        App.main(args);

        String expectedOutput = Paths.get("src/test/resources/testFile.java").toAbsolutePath().normalize() + ": 8 lines\n" +
                "Total Physic Lines: 8\n";
        assertEquals(expectedOutput, outContent.toString().replaceAll("\r", ""));
    }

    @Test
    public void testAppWithLogicalLines() throws IOException {
        String[] args = {"loc", "src/test/resources/testFile.java"};
        App.main(args);

        String expectedOutput = Paths.get("src/test/resources/testFile.java").toAbsolutePath().normalize() + ": 5 lines\n" +
                "Total Logic Lines: 5\n";
        assertEquals(expectedOutput, outContent.toString().replaceAll("\r", ""));
    }

    @Test
    public void testAppWithInvalidOption() throws IOException {
        String[] args = {"invalid", "src/test/resources/testFile.java"};
        App.main(args);

        String expectedOutput = "Invalid option: invalid\n";
        assertEquals(expectedOutput, outContent.toString().replaceAll("\r", ""));
    }
}

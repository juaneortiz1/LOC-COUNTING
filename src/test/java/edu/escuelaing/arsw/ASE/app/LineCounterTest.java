package edu.escuelaing.arsw.ASE.app;

import org.junit.Test;
import java.nio.file.Paths;
import static org.junit.Assert.assertEquals;

public class LineCounterTest {

    @Test
    public void testCountPhysicalLines() throws Exception {
        int count = LineCounter.countPhysicalLines(Paths.get("src/test/resources/testFile.java"));
        assertEquals(8, count);
    }
    @Test
    public void testCountLogicalLines() throws Exception {
        int count = LineCounter.countLogicalLines(Paths.get("src/test/resources/testFile.java"));
        assertEquals(5, count);
    }
}

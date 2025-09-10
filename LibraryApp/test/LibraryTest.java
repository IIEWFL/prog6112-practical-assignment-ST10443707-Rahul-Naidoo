import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LibraryTest {

    @Test
    public void testAddAndCount() {
        Library lib = new Library();
        int initial = lib.getAllItems().size();
        lib.addItem(new Book(9999, "Test Book", 2021, "Me", "TestGenre"));
        assertEquals(initial + 1, lib.getAllItems().size());
    }

    @Test
    public void testBorrowAndReturn() {
        Library lib = new Library();
        Item item = lib.getAllItems().get(0);
        int id = item.getId();
        assertFalse(item.isBorrowed());
        boolean b = lib.borrowItem(id);
        assertTrue(b);
        assertTrue(item.isBorrowed());
        boolean r = lib.returnItem(id);
        assertTrue(r);
        assertFalse(item.isBorrowed());
    }

    @Test
    public void testGenerateReportOutputsHeader() {
        Library lib = new Library();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(baos));
        try {
            lib.generateReport();
        } finally {
            System.setOut(original);
        }
        String output = baos.toString();
        assertTrue(output.contains("LIBRARY REPORT"));
        assertTrue(output.contains("Total items:"));
    }
}

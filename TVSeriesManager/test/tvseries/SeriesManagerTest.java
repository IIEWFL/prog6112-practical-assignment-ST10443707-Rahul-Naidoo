package tvseries;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class SeriesManagerTest {

    @Before
    public void setUp() {
        SeriesManager.clearSeries();
        SeriesManager.addSeries(new Series(101, "Extreme Sports", 12, 10));
    }

    @Test
    public void TestSearchSeries() {
        Series found = null;
        for (Series s : SeriesManager.getSeriesList()) {
            if (s.getId() == 101) found = s;
        }
        assertNotNull(found);
        assertEquals("Extreme Sports", found.getName());
    }

    @Test
    public void TestSearchSeries_SeriesNotFound() {
        Series found = null;
        for (Series s : SeriesManager.getSeriesList()) {
            if (s.getId() == 999) found = s;
        }
        assertNull(found);
    }

    @Test
    public void TestUpdateSeries() {
        Series s = SeriesManager.getSeriesList().get(0);
        s.setAgeRestriction(18);
        assertEquals(18, s.getAgeRestriction());
    }

    @Test
    public void TestDeleteSeries() {
        Series s = SeriesManager.getSeriesList().get(0);
        SeriesManager.getSeriesList().remove(s);
        assertTrue(SeriesManager.getSeriesList().isEmpty());
    }

    @Test
    public void TestDeleteSeries_SeriesNotFound() {
        boolean removed = SeriesManager.getSeriesList().removeIf(x -> x.getId() == 999);
        assertFalse(removed);
    }

    @Test
    public void TestSeriesAgeRestriction_AgeValid() {
        Series s = new Series(102, "Family Show", 10, 8);
        assertTrue(s.getAgeRestriction() >= 0 && s.getAgeRestriction() <= 18);
    }

    @Test
    public void TestSeriesAgeRestriction_SeriesAgeInValid() {
        Series s = new Series(103, "Mature Show", 25, 12);
        assertFalse(s.getAgeRestriction() >= 0 && s.getAgeRestriction() <= 18);
    }
}

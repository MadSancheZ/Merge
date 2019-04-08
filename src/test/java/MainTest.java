import model.Price;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    void mergeIfNewPricesNull() throws ParseException, CloneNotSupportedException {
        List<Price> actual = new ArrayList<>();
        actual.add(new Price(1, "122856", 1, 1, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("01.01.2013 00:00:00"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("31.01.2013 23:59:59"), 11000));
        actual.add(new Price(2, "122856", 2, 1, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("10.01.2013 00:00:00"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("20.01.2013 23:59:59"), 99000));
        actual.add(new Price(1, "6654", 1, 2, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("01.01.2013 00:00:00"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("31.01.2013 00:00:00"), 5000));

        List<Price> expected = new Main().mergePrices(actual, null);

        assertEquals(expected, actual);

    }

    @Test
    void mergeIfOldPricesNull() throws CloneNotSupportedException, ParseException {
        List<Price> actual = new ArrayList<>();
        actual.add(new Price(1, "122856", 1, 1, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("01.01.2013 00:00:00"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("31.01.2013 23:59:59"), 11000));
        actual.add(new Price(2, "122856", 2, 1, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("10.01.2013 00:00:00"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("20.01.2013 23:59:59"), 99000));
        actual.add(new Price(1, "6654", 1, 2, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("01.01.2013 00:00:00"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("31.01.2013 00:00:00"), 5000));

    List<Price> expected = new Main().mergePrices(null, actual);

    assertEquals(expected, actual);
    }

    @Test
    void mergeIfNewPricesEmpty() throws ParseException, CloneNotSupportedException {
        List<Price> actual = new ArrayList<>();
        actual.add(new Price(1, "122856", 1, 1, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("01.01.2013 00:00:00"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("31.01.2013 23:59:59"), 11000));
        actual.add(new Price(2, "122856", 2, 1, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("10.01.2013 00:00:00"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("20.01.2013 23:59:59"), 99000));
        actual.add(new Price(1, "6654", 1, 2, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("01.01.2013 00:00:00"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("31.01.2013 00:00:00"), 5000));

        List<Price> expected = new Main().mergePrices(actual, new ArrayList<>());

        assertEquals(expected, actual);

    }

    @Test
    void mergeIfOldPricesEmpty() throws CloneNotSupportedException, ParseException {
        List<Price> actual = new ArrayList<>();
        actual.add(new Price(1, "122856", 1, 1, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("01.01.2013 00:00:00"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("31.01.2013 23:59:59"), 11000));
        actual.add(new Price(2, "122856", 2, 1, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("10.01.2013 00:00:00"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("20.01.2013 23:59:59"), 99000));
        actual.add(new Price(1, "6654", 1, 2, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("01.01.2013 00:00:00"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("31.01.2013 00:00:00"), 5000));

        List<Price> expected = new Main().mergePrices(new ArrayList<>(), actual);

        assertEquals(expected, actual);
    }

    @Test
    void mergeIfNull() throws CloneNotSupportedException{
        List<Price> expected = new Main().mergePrices(null, null);
        assertNull(expected);
    }

    @Test
    void mergeIfEmpty() throws CloneNotSupportedException{
        List<Price> expected = new Main().mergePrices(new ArrayList<>(), new ArrayList<>());
        assertNotNull(expected);
    }

    @Test
    void mergeNewBigger() throws ParseException, CloneNotSupportedException {
        List<Price> actual = new ArrayList<>();
        actual.add(new Price(1, "1", 1, 1, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("01.01.2013 00:00:00"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("31.01.2013 23:59:59"), 1));
        List<Price> oldPrices = new ArrayList<>();
        oldPrices.add(new Price(1, "1", 1, 1, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("10.01.2013 00:00:00"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("20.01.2013 23:59:59"), 2));
        List<Price> expected = new Main().mergePrices(oldPrices, actual);
        assertEquals(expected, actual);
    }

    @Test
    void mergeOldBigger() throws CloneNotSupportedException, ParseException {
        List<Price> oldPrices = new ArrayList<>();
        oldPrices.add(new Price(1, "1", 1, 1, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("01.01.2013 00:00:00"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("31.01.2013 23:59:59"), 1));
        List<Price> newPrices = new ArrayList<>();
        newPrices.add(new Price(1, "1", 1, 1, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("10.01.2013 00:00:00"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("20.01.2013 23:59:59"), 2));
        List<Price> expected = new Main().mergePrices(oldPrices, newPrices);
        List<Price> actual = new ArrayList<>();
        actual.add(new Price(1, "1", 1, 1, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("01.01.2013 00:00:00"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("10.01.2013 00:00:00"), 1));
        actual.add(new Price(1, "1", 1, 1, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("10.01.2013 00:00:00"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("20.01.2013 23:59:59"), 2));
        actual.add(new Price(1, "1", 1, 1, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("20.01.2013 23:59:59"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("31.01.2013 23:59:59"), 1));
        assertEquals(expected, actual);
    }

    @Test
    void mergeOldEarlier() throws ParseException, CloneNotSupportedException {
        List<Price> oldPrices = new ArrayList<>();
        oldPrices.add(new Price(1, "1", 1, 1, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("01.01.2013 00:00:00"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("20.01.2013 23:59:59"), 1));
        List<Price> newPrices = new ArrayList<>();
        newPrices.add(new Price(1, "1", 1, 1, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("10.01.2013 00:00:00"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("31.01.2013 23:59:59"), 2));
        List<Price> expected = new Main().mergePrices(oldPrices, newPrices);
        List<Price> actual = new ArrayList<>();
        actual.add(new Price(1, "1", 1, 1, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("01.01.2013 00:00:00"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("10.01.2013 00:00:00"), 1));
        actual.add(new Price(1, "1", 1, 1, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("10.01.2013 00:00:00"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("31.01.2013 23:59:59"), 2));
        assertEquals(expected, actual);
    }

    @Test
    void mergeNewEarlier() throws ParseException, CloneNotSupportedException {
        List<Price> oldPrices = new ArrayList<>();
        oldPrices.add(new Price(1, "1", 1, 1, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("10.01.2013 00:00:00"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("31.01.2013 23:59:59"), 1));
        List<Price> newPrices = new ArrayList<>();
        newPrices.add(new Price(1, "1", 1, 1, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("01.01.2013 00:00:00"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("20.01.2013 23:59:59"), 2));
        List<Price> expected = new Main().mergePrices(oldPrices, newPrices);
        List<Price> actual = new ArrayList<>();
        actual.add(new Price(1, "1", 1, 1, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("01.01.2013 00:00:00"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("20.01.2013 23:59:59"), 2));
        actual.add(new Price(1, "1", 1, 1, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("20.01.2013 23:59:59"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("31.01.2013 23:59:59"), 1));
        assertEquals(expected, actual);
    }

    @Test
    void mergeDifPrices() throws ParseException, CloneNotSupportedException {
        List<Price> oldPrices = new ArrayList<>();
        oldPrices.add(new Price(1, "1", 1, 1, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("01.01.2013 00:00:00"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("31.01.2013 23:59:59"), 1));
        List<Price> newPrices = new ArrayList<>();
        newPrices.add(new Price(1, "1", 2, 1, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("10.01.2013 00:00:00"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("20.01.2013 23:59:59"), 2));
        List<Price> expected = new Main().mergePrices(oldPrices, newPrices);
        List<Price> actual = new ArrayList<>();
        actual.add(new Price(1, "1", 1, 1, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("01.01.2013 00:00:00"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("31.01.2013 23:59:59"), 1));
        actual.add(new Price(1, "1", 2, 1, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("10.01.2013 00:00:00"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("20.01.2013 23:59:59"), 2));
        assertEquals(expected, actual);
    }
}

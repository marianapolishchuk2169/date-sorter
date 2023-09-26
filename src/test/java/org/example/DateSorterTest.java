package org.example;

import exception.DataProcessingException;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateSorterTest {
    private static final List<LocalDate> UNSORTED_DATES = Arrays.asList(
            LocalDate.of(2004, 7, 1),
            LocalDate.of(2005, 1, 2),
            LocalDate.of(2007, 1, 1),
            LocalDate.of(2032, 5, 3)
    );
    private static final List<LocalDate> EXPECTED_SORTED_DATES = Arrays.asList(
            LocalDate.of(2005, 1, 2),
            LocalDate.of(2007, 1, 1),
            LocalDate.of(2032, 5, 3),
            LocalDate.of(2004, 7, 1)
    );
    private static final List<LocalDate> UNSORTED_MORE_DATES = Arrays.asList(
            LocalDate.of(2004, 7, 1),
            LocalDate.of(2005, 1, 2),
            LocalDate.of(2007, 1, 1),
            LocalDate.of(2032, 5, 3),
            LocalDate.of(2022, 8, 15),
            LocalDate.of(2023, 11, 20)
    );
    private static final List<LocalDate> EXPECTED_MORE_SORTED_DATES = Arrays.asList(
            LocalDate.of(2005, 1, 2),
            LocalDate.of(2007, 1, 1),
            LocalDate.of(2023, 11, 20),
            LocalDate.of(2032, 5, 3),
            LocalDate.of(2022, 8, 15),
            LocalDate.of(2004, 7, 1)
    );
    private static final List<LocalDate> SINGLE_DATE = Arrays.asList(
            LocalDate.of(2023, 6, 7)
    );
    private static final List<LocalDate> EXPECTED_SORTED_SINGLE_DATE = Arrays.asList(
            LocalDate.of(2023, 6, 7)
    );

    private final DateSorter dateSorter = new DateSorter();

    @Test
    void sortDates_ok() {
        List<LocalDate> actualSortedDates = (List<LocalDate>) dateSorter.sortDates(UNSORTED_DATES);

        assertEquals(EXPECTED_SORTED_DATES, actualSortedDates);
    }
    @Test
    void sortMoreDates_ok() {
        List<LocalDate> actualSortedDates = (List<LocalDate>) dateSorter.sortDates(UNSORTED_MORE_DATES);

        assertEquals(EXPECTED_MORE_SORTED_DATES, actualSortedDates);
    }

    @Test
    void sortEmptyDates_ok() {
        List<LocalDate> emptyDates = (List<LocalDate>) dateSorter.sortDates(new ArrayList<>());

        assertTrue(emptyDates.isEmpty());
    }

    @Test
    void sortSingleDate_ok() {
        List<LocalDate> actualSortedDate = (List<LocalDate>) dateSorter.sortDates(SINGLE_DATE);

        assertEquals(EXPECTED_SORTED_SINGLE_DATE, actualSortedDate);
    }

    @Test
    void sortNullDates_notOk() {
        assertThrows(DataProcessingException.class, () -> dateSorter.sortDates(null));
    }

    @Test
    void sortDatesWithNull_notOk() {
        List<LocalDate> unsortedDatesWithNull = new ArrayList<>(UNSORTED_DATES);
        unsortedDatesWithNull.add(null);

        assertThrows(DataProcessingException.class, () -> dateSorter.sortDates(unsortedDatesWithNull));
    }
}

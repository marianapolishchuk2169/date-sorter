package org.example;


import exception.DataProcessingException;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class DateSorter {
    private final String sortParameter;
    private static final String PROPERTIES_FILENAME = "application.properties";
    public DateSorter() {
        PropertiesConfiguration config = new PropertiesConfiguration();
        try {
            config.load(PROPERTIES_FILENAME);
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
        sortParameter = config.getString("sort.parameter");
    }

    public Collection<LocalDate> sortDates(List<LocalDate> unsortedDates) {
        if (unsortedDates == null || unsortedDates.contains(null)) {
            throw new DataProcessingException("Null date can't be processed");
        }

        List<LocalDate> sortedDatesWithSortParameter = unsortedDates.stream()
                .filter(date -> isContainsSortParameter(date.getMonth()))
                .sorted()
                .toList();

        List<LocalDate> sortedDatesWithoutSortParameter = unsortedDates.stream()
                .filter(date -> !isContainsSortParameter(date.getMonth()))
                .sorted(Comparator.reverseOrder())
                .toList();

        return Stream.of(sortedDatesWithSortParameter, sortedDatesWithoutSortParameter)
                .flatMap(Collection::stream)
                .toList();
    }

    private boolean isContainsSortParameter(Month month) {
        return month.toString().toLowerCase().contains(sortParameter.toLowerCase());
    }
}

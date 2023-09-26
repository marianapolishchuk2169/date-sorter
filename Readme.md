# Date Sorter for Palanquin
This test case implements the method sortDates(). The implementation of this method sorts dates. The output should be in the following order:

- dates with an 'r' in the month sorted ascending (first to last);
- then dates without an 'r' in the month sorted descending (last to first).

For example, October dates would come before May dates because October has an 'r' in it.
Thus: (2004-07-01, 2005-01-02, 2007-01-01, 2032-05-03)
will sort to
(2005-01-02, 2007-01-01, 2032-05-03, 2004-07-01).

@param unsortedDates - an unsorted list of dates <br/>
@return the collection of dates now sorted as per the spec

## How it works
For the universality of the solution, the `application.properties` file was used, which stores the value of the sorting parameter. This allows the sort parameter to be easily changed without affecting the rest of the code if the sorting condition changes.
The work of the method is as follows:
- checking input data for null;
- filtering the original collection and creating a new collection with dates containing the sort parameter;
- ascending sorting;
- filtering the original collection and creating a new collection with dates that do not contain a sort parameter;
- descending sorting;
- returning a collection that combines two newly created collections.

## Project structure
- `exception` package contains a class DataProcessingException with a custom exception;
- `org.example` package contains a class DateSorter with sortDates() implementation;
- `resources` package contains application.properties file, which stores the value of the sorting parameter
- `test/java/org.example` path contains a class DateSorterTest with unit tests.

## Description of tests
- `sortDates_ok()` - method sorts the data from the condition;
- `sortMoreDates_ok()` - method sorts a greater amount of dates;
- `sortEmptyDates_ok()` - method sorts an empty collection;
- `sortSingleDate_ok()` - method sorts the collection with single date;
- `sortNullDates_notOk()` - method checks whether an exception is thrown if the input is null;
- `sortDatesWithNull_notOk()` - method checks whether an exception is thrown if the source collection contains null.
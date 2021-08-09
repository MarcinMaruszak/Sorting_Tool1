package sorting;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LineSorter extends Sorter<String> {

    public LineSorter(List<String> entries, String sortType) {
        super(entries, sortType);
    }

    @Override
    public void add(String s) {
        getEntries().add(s);
    }

    @Override
    public String getMaxValue() {
        return getEntries().stream().max(Comparator.comparing(String::length)).get();
    }

    @Override
    public void sort() {
        getEntries().sort(Comparator.comparing(String::length));
    }

    @Override
    public String getDataByCount() {
        return getEntries().stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .sorted(Map.Entry.comparingByValue())
                .map(e -> e.getKey() + ": " + e.getValue() + " time(s), "
                        + (e.getValue() * 100 / getEntries().size() + "%"))
                .collect(Collectors.joining("\n"));
    }

}

package sorting;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LongSorter extends Sorter<Long> {

    public LongSorter(List<Long> entries, String sortType) {
        super(entries, sortType);
    }

    @Override
    public void add(Long value) {
        getEntries().add(value);
    }

    @Override
    public Long getMaxValue() {
        return getEntries().stream().max(Long::compareTo).get();
    }

    public void sort() {
        if (getSortType().equals("natural")) {
            Collections.sort(getEntries());
        }
    }


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

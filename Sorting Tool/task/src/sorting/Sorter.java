package sorting;

import java.util.Collections;
import java.util.List;

public abstract class Sorter <T>{
    private List <T> entries;
    private String sortType;

    public Sorter(List<T> entries, String sortType) {
        this.entries = entries;
        this.sortType = sortType;
    }

    public List<T> getEntries() {
        return entries;
    }

    public void setEntries(List<T> entries) {
        this.entries = entries;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public abstract void add(T t);

    public abstract T getMaxValue();

    final long getTimes() {
        Object max = getMaxValue();
        return entries.stream().filter(e -> e.equals(max)).count();
    }

    public abstract void sort();

    public abstract String getDataByCount();


}

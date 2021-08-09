package sorting;

import java.util.ArrayList;

public class SorterFactory {

    public static Sorter<?> createSorter(String type, String sortType){
        switch (type){
            case "long":
                return new LongSorter(new ArrayList<>(), sortType);
            case "line":
                return new LineSorter(new ArrayList<>(), sortType);
            default:
                return new WordSorter(new ArrayList<>(), sortType);
        }
    }
}

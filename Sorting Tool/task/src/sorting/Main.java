package sorting;

import java.util.*;

public class Main {
    public static void main(final String[] args) {
        UserInterface userInterface = new UserInterface(new Scanner(System.in), "word", "natural");
        userInterface.parseArguments(args);
        userInterface.createSorter();
        userInterface.getInput();
        userInterface.outputData();
    }
}

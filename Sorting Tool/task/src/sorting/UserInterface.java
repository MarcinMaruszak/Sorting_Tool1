package sorting;

import java.io.*;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UserInterface {
    private Scanner scanner;
    private String dataType;
    private String sortType;
    private Sorter sorter;
    private String inputFile;
    private String outputFile;

    public UserInterface(Scanner scanner, String dataType, String sortType) {
        this.scanner = scanner;
        this.dataType = dataType;
        this.sortType = sortType;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public Sorter getSorter() {
        return sorter;
    }

    public void setSorter(Sorter sorter) {
        this.sorter = sorter;
    }

    public void parseArguments(String[] args) {
        int i = 0;
        while (i < args.length) {
            if (args[i].equals("-dataType")) {
                try {
                    if (args[i + 1].equals("long") || args[i + 1].equals("word") || args[i + 1].equals("line")) {
                        dataType = args[i + 1];
                        i += 2;
                    } else {
                        System.out.println("No data type defined!");
                        i++;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("No data type defined!");
                    break;
                }
            } else if (args[i].equals("-sortingType")) {
                try {
                    if (args[i + 1].equals("byCount") || args[i + 1].equals("natural")) {
                        sortType = args[i + 1];
                        i += 2;
                    } else {
                        System.out.println("No sorting type defined!");
                        i++;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("No sorting type defined!");
                    break;
                }
            } else if (args[i].equals("-inputFile")) {
                try {
                    inputFile = args[i + 1];
                    i += 2;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("No input file name provided");
                    break;
                }
            } else if (args[i].equals("-outputFile")) {
                try {
                    outputFile = args[i + 1];
                    i += 2;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("No output file name provided");
                    break;
                }
            } else if (args[i].startsWith("-")) {
                System.out.println(args[i] + " is not a valid parameter. It will be skipped.");
                i++;
            } else {
                i++;
            }
        }
    }

    public void createSorter() {
        sorter = SorterFactory.createSorter(dataType, sortType);
    }

    public void getInput() {
        try {
            if (inputFile == null) {
                scanner = new Scanner(System.in);
            } else {
                scanner = new Scanner(new File(inputFile));
            }
            String input;
            while (scanner.hasNext()) {
                switch (dataType) {
                    case "long":
                        input = scanner.next();
                        try {
                            long number = Long.parseLong(input);
                            sorter.add(number);
                        } catch (Exception e) {
                            System.out.println("\"" + input + "\" is not a long. It will be skipped.");
                        }
                        break;
                    case "word":
                        input = scanner.next();
                        sorter.add(input);
                        break;
                    case "line":
                        input = scanner.nextLine();
                        sorter.add(input);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }finally {
            scanner.close();
        }
    }


    public void outputData() {
        if (outputFile == null) {
            System.out.println("Total number: " + sorter.getEntries().size() + ".");
            if (sortType.equals("byCount")) {
                System.out.println(sorter.getDataByCount());
            } else {
                sorter.sort();
                System.out.println(sorter.getEntries().stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(" ")));
            }
        }else {
            saveToFile();
        }

    }

    private void saveToFile() {
        try(FileWriter writer = new FileWriter(outputFile)){
            String output;
            if (sortType.equals("byCount")) {
                output = sorter.getDataByCount();

            } else {
                sorter.sort();
                output = sorter.getEntries().stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(" ")).toString();
            }
            writer.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

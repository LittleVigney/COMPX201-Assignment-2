import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class DictionaryLookup {
    public static void main(String[] args){
        DictionaryBST dataBST = readData();

        Scanner scanner = new Scanner(System.in);
        int op = 0;

        // run program until user input 6
        do{
            System.out.println("--------------------------------------------");
            System.out.println("Menu:");
            System.out.println("1. Search for a word/phrase in the dictionary");
            System.out.println("2. Print a word/phrase and it's definition");
            System.out.println("3. Add a word/phrase and definition to dictionary");
            System.out.println("4. Remove a word/phrase and definition from dictionary");
            System.out.println("5. Print all of words/phrases and definitions in alphabetical order");
            System.out.println("6. Exit");
            System.out.print("Input your option: ");
            try{
                op = scanner.nextInt();
                scanner.nextLine();
            }
            catch (java.util.InputMismatchException err) {
                System.out.println("Please input valid number.");
                scanner.nextLine();
                continue;
            }
            
            switch (op){
                case 1:
                    searchWord(dataBST, scanner);
                    break;
                case 2:
                    printWord(dataBST, scanner);
                    break;
                case 3:
                    addWord(dataBST, scanner);
                    break;
                case 4:
                    removeWord(dataBST, scanner);
                    break;
                case 5:
                    printDic(dataBST);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid number. Please input number from 1 to 6.");
            }
        } while (op != 6);

        scanner.close();

        System.out.println("Program exits.");

        System.exit(0);
    }

    // load data from txt file
    public static DictionaryBST readData(){
        DictionaryBST dataBST = new DictionaryBST();

        String filePath = "CS-Dictionary-small-shuffled.txt";
        // String filePath = "CS-Dictionary-full-shuffled.txt";

        try{
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            for (String line : lines) {
                line = line.trim();
                if (line.isEmpty()){
                    continue;
                }

                int idx = line.indexOf(':');
                if (idx == -1){
                    continue;
                }

                // split key and value
                String key = line.substring(0, idx).trim();
                String value = line.substring(idx + 1).trim();
                
                // insert key to dictionary
                dataBST.insert(key, value);
            }
        } 
        catch (IOException err){
            throw new RuntimeException("Failed to read data.", err);
        }

        return dataBST;
    }

    // search a word or phrase in dictionary
    public static void searchWord(DictionaryBST dataBST, Scanner scanner){
        System.out.println("--------------------");
        System.out.println("Please input the word/phrase you want to search: ");

        String goal = scanner.nextLine();

        if (dataBST.search(goal) == null){
            System.out.println("Goal is not in dictionary.");
        }
        else{
            System.out.println("Goal is in dictionary.");
        }
    }

    // print a word or phrase and its defination
    public static void printWord(DictionaryBST dataBST, Scanner scanner){
        System.out.println("--------------------");
        System.out.println("Please input the word/phrase you want to print: ");

        String goal = scanner.nextLine();

        dataBST.printDictionaryItem(goal);
    }

    // add word or phrase to doctionary
    public static void addWord(DictionaryBST dataBST, Scanner scanner){
        System.out.println("--------------------");
        System.out.println("Please input the word/phrase and its defination you want to add: ");

        String goal = scanner.nextLine();

        String goalValue = scanner.nextLine();

        dataBST.insert(goal, goalValue);

        System.out.println("Word/phrase has been added.");
    }

    // remove word or phrase to doctionary
    public static void removeWord(DictionaryBST dataBST, Scanner scanner){
        System.out.println("--------------------");
        System.out.println("Please input the word/phrase you want to remove: ");

        String goal = scanner.nextLine();

        dataBST.remove(goal);

        System.out.println("Word/phrase has been removed.");
    }

    // print all word and phrase in dictionary
    public static void printDic(DictionaryBST dataBST){
        System.out.println("--------------------");
        System.out.println("Printing all items in dictionary...");

        dataBST.printDictionary();

        System.out.println("Printing finished.");
    }
}

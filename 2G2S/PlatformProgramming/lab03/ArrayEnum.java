import java.util.Scanner;

public class ArrayEnum {
    public static void main(String[] args) {

        int[] values = new int[100];

        int index = 0;

        final Scanner scanner = new Scanner(System.in);

        while (true) {
            final Command command = getCommand(scanner); // Command is enum
            if (command == Command.QUIT) {
                System.out.println("Bye!");
                break;
            }
            switch (command) {
                case ADD:
                    final int newValue = getValue(scanner);
                    values[index] = newValue;
                    index++;
                    break;
                case LIST:
                    printList(values, index);
                    break;
                case SUM:
                    System.out.println(getSum(values, index));
                    break;
                case INVALID:
                    System.out.println("Invalid Command");
                default:
                    break;
            }
        }
        scanner.close();
    }

    private enum Command {
        ADD, LIST, SUM, INVALID, QUIT
    }

    private static Command getCommand(Scanner inputWord) {
        String singleWord = inputWord.next();
        singleWord = singleWord.toUpperCase();

        Command singleCommand = Command.INVALID;

        switch (singleWord) {
            case "ADD":
                singleCommand = Command.ADD;
                break;

            case "LIST":
                singleCommand = Command.LIST;
                break;

            case "SUM":
                singleCommand = Command.SUM;
                break;

            case "INVALID":
                singleCommand = Command.INVALID;
                break;

            case "QUIT":
                singleCommand = Command.QUIT;
                break;

            default:
                break;
        }

        return singleCommand;
    }

    private static int getValue(Scanner inputInteger) {
        int singleInteger = inputInteger.nextInt();
        return singleInteger;
    }

    private static void printList(int[] valueList, int valueIndex) {
        for (int i = 0; i < valueIndex; i++) {

            System.out.print(valueList[i]);

            if (i != valueIndex) {
                System.out.print(" ");

            }
        }
        System.out.println("");
    }

    private static int getSum(int[] valueList, int valueIndex) {
        int sum = 0;

        for (int i = 0; i <= valueIndex; i++) {
            sum += valueList[i];
        }

        return sum;
    }
}
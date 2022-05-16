import java.util.Scanner;

enum StringCommand {
    ADD, REMOVE, CLEAN, QUIT, INVALID
};

public class StringSetManager {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String[] stringSet = new String[100];

        while (true) {
            final StringCommand command = getCommand(scanner);

            if (command == StringCommand.QUIT) {
                System.out.println("BYE!");
                break;
            }

            switch (command) {
                case ADD: {
                    final String str = getString(scanner);
                    executeAdd(stringSet, str);
                    break;
                }

                case REMOVE: {
                    final String str = getString(scanner);
                    executeRemove(stringSet, str);
                    break;
                }

                case CLEAN: {
                    executeClear(stringSet);
                    break;
                }

                default:
                    System.out.println("Unknown Command!");
                    break;
            }
            executePrint(stringSet);
        }
    }

    static int stringNum = 0;
    static int lastIndex = -1;

    private static StringCommand getCommand(final Scanner inputCommand) {
        final String inputString = inputCommand.next().toUpperCase();
        StringCommand execCommand;

        try {
            execCommand = StringCommand.valueOf(inputString);
        } catch (final Exception e) {
            execCommand = StringCommand.INVALID;
        }

        return execCommand;
    }

    private static String getString(final Scanner inputString) {
        return inputString.next();
    }

    private static void executeAdd(final String[] addStringSet, final String addString) {
        boolean stringRepeatChecker = false;
        int j = 0;
        for (int k = 0; k < stringNum; j++) {
            if (addStringSet[j] != null) {
                if (addStringSet[j].equals(addString)) {
                    stringRepeatChecker = true;
                    break;
                }
                k++;
            }
        }

        if (!stringRepeatChecker) {
            for (int i = 0; i < 100; i++) {
                if (addStringSet[i] == null) {
                    addStringSet[i] = addString;
                    stringNum++;

                    if (i >= lastIndex) {
                        lastIndex = i;
                    }
                    break;
                }
            }
        }
    }

    private static void executeRemove(final String[] removeStringSet, final String removeString) {
        for (int i = 0; i < 100; i++) {
            if (removeStringSet[i] == null)
                continue;

            if (removeStringSet[i].equals(removeString)) {
                removeStringSet[i] = null;
                stringNum--;
                break;
            }
        }
    }

    private static void executeClear(final String[] clearStringSet) {
        for (int i = 0; i <= lastIndex; i++) {
            clearStringSet[i] = null;
        }
        stringNum = 0;
        lastIndex = -1;
    }

    private static void executePrint(final String[] printStringSet) {
        System.out.printf("Element Size: %d, Values = ", stringNum);
        int j = 0;
        for (int i = 0; i < stringNum; j++) {

            if (printStringSet[j] == null) {
                continue;
            }

            System.out.print(printStringSet[j]);
            i++;
            if (i < stringNum) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
}
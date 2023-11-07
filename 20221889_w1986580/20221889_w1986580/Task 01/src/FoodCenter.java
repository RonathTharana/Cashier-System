/*Student Name : S.W.Ronath Tharana Wijayin
  UoW Number : w1986580
  IIT Number : 20221889*/


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class FoodCenter {

    // Creating a max burger count in the burger stock.
    private static final int maxBurgerStock = 50;

    // Creating a stock that can add or remove burgers.
    private static int burgerStock = maxBurgerStock;

    // Creating a array to store queues data.
    // https://www.w3schools.com/java/java_arrays_multi.asp
    private static String[][] queues = new String[3][5];

    // Initializing a scanner to get inputs.
    private static Scanner getInput = new Scanner(System.in);


    public static void main(String[] args) {
        String line = "=====";
        String blank = "     ";
        boolean options;
        System.out.println("\n" + line.repeat(20));
        System.out.println(blank.repeat(6) + "Welcome to Foodies Fave Food Center");

        // Creating the menu options and printing the menu options.
        options = true;

        while (options) {
            System.out.println("\n" + line.repeat(20));
            System.out.println("Please select an option: ");
            System.out.println("100 or VFQ: View all Queues.");
            System.out.println("101 or VEQ: View all Empty Queues.");
            System.out.println("102 or ACQ: Add customer to a Queue.");
            System.out.println("103 or RCQ: Remove a customer from a Queue. (From a specific location)");
            System.out.println("104 or PCQ: Remove a served customer.");
            System.out.println("105 or VCS: View Customers Sorted in alphabetical order.");
            System.out.println("106 or SPD: Store Program Data into file.");
            System.out.println("107 or LPD: Load Program Data from file.");
            System.out.println("108 or STK: View Remaining burgers Stock.");
            System.out.println("109 or AFS: Add burgers to Stock.");
            System.out.println("999 or EXT: Exit the Program.");
            System.out.println(line.repeat(20));

            // Getting the menu option input.
            String choice = readString("Enter your option: ");

            // Using switch case to give the output according to the input.
            https://www.w3schools.com/java/java_switch.asp

            switch (choice) {
                case "100":
                case "VFQ":
                    // This option shows the occupied and not occupied queue positions.
                    viewAllQueues();
                    break;

                case "101":
                case "VEQ":
                    // This option shows that queue is full or empty.
                    viewAllEmptyQueues();
                    break;

                case "102":
                case "ACQ":
                    // This option add a customer to the queue.
                    addCustomerToQueue();
                    break;

                case "103":
                case "RCQ":
                    // This option will remove a customer from a selected position.
                    removeCustomerFromAQueue();
                    break;

                case "104":
                case "PCQ":
                    // This option will remove a served customer from a queue.
                    removeServedCustomer();
                    break;

                case "105":
                case "VCS":
                    // This option will show the customer names in the alphabetical order.
                    viewCustomersSorted();
                    break;

                case "106":
                case "SPD":
                    // This option will save customer details in to a file.
                    storeProgramData();
                    break;

                case "107":
                case "LPD":
                    // This option will read customer details from the save file.
                    loadProgramData();
                    break;

                case "108":
                case "STK":
                    // This option will show the remaining burgers in the stock.
                    remainingBurgersStock();
                    break;

                case "109":
                case "AFS":
                    // This option will add burgers to the stock.
                    addBurgersToStock();
                    break;

                case "999":
                case "EXT":
                    // This option will end the program and exit.
                    options = false;
                    System.out.println("Exiting the program...");
                    break;

                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    /**
     * Method to print the queues occupied and not occupied positions.
     */
    private static void viewAllQueues() {
        System.out.println("******************");
        System.out.println("     Cashiers     ");
        System.out.println("******************");

        // Creating a copy of the queues array.
        // https://www.programiz.com/java-programming/copy-arrays

        String[][] queuesCopy = new String[3][5];
        for (int i = 0; i < queues.length; i++) {
            System.arraycopy(queues[i], 0, queuesCopy[i], 0, queues[i].length);
        }

        // Using ternary operator to maintain each queue length.
        // https://www.w3schools.com/java/java_conditions_shorthand.asp

        for (int i = 0; i < queuesCopy.length; i++) {

            int maxCapacity = (i == 2) ? 5 : i + 2;
            for (int j = 0; j < queuesCopy[i].length; j++) {
                if (j < maxCapacity) {
                    if (queuesCopy[i][j] == null) {
                        queuesCopy[i][j] = "X";
                    } else {
                        queuesCopy[i][j] = "O";
                    }
                } else {
                    queuesCopy[i][j] = " ";
                }
            }
        }

        for (int i = 0; i < queuesCopy.length - 2; i++) {
            for (int j = 0; j < queuesCopy[i].length; j++) {
                System.out.println("    " + queuesCopy[i][j] + "   " + queuesCopy[i + 1][j] + "   " + queuesCopy[i + 2][j]);
            }
        }
        System.out.println("O - Occupied \nX - Not Occupied");
    }

    /**
     * Method to show the queues condition.
     */
    private static void viewAllEmptyQueues() {
        viewAllQueues(); // printing the queues empty and occupied positions.

        System.out.println("================");
        System.out.println("---- Queues ----");
        System.out.println("================");


        for (int i = 0; i < queues.length; i++) {

            int occupiedPositions = 0;
            int maxCapacity = (i == 2) ? 5 : i + 2; // Using ternary operator to maintain each queue length.
            for (int j = 0; j < maxCapacity; j++) {
                if (queues[i][j] != null) {
                    occupiedPositions++;
                }
            }

            String status = "";
            if (occupiedPositions == maxCapacity) {
                status = "Full";
            } else {
                status = "Empty";
            }
            System.out.println("Queue " + (i + 1) + ": " + status);
        }
    }

    /**
     * Method to add a new customer to the selected queue.
     */
    private static void addCustomerToQueue() {
        String customerName = readString("Enter customer name: ");
        int queueNumber = selectQueue();
        String capitalizeCustomerName = customerName.substring(0, 1).toUpperCase() + customerName.substring(1); // Capitalize the first letter in customer name.

        int queueIndex = queueNumber - 1;
        int positions = availablePosition(queueIndex);

        // Using ternary operator to maintain each queue length.
        int maxPositions = (queueIndex == 0) ? 2 : (queueIndex == 1 ? 3 : 5);

        if (positions >= 0 && positions < maxPositions) {
            queues[queueIndex][positions] = capitalizeCustomerName;
            System.out.println("customer " + capitalizeCustomerName + " added to queue " + queueNumber + " .");
        }
        else {
            System.out.println("Queue " + queueNumber + " is full. Customer can not added.");
        }
    }

    /**
     * Method to remove a customer from a selected position in a queue.
     */
    private static void removeCustomerFromAQueue() {
        int queueNumber = selectQueue();
        int queueIndex = queueNumber - 1;

        int position = readInteger("Enter customer position in the queue: ");

        int positionIndex = position - 1;

        // Using ternary operator to maintain each queue length.
        int positionLimit = (queueIndex == 0) ? 2 : (queueIndex == 1 ? 3 : 5);

        if (position >= 1 && position <= positionLimit) {

            if (queues[queueIndex][positionIndex] != null) {
                String customerName = queues[queueIndex][positionIndex];
                queues[queueIndex][positionIndex] = null;
                System.out.println("Customer " + customerName + " removed from queue " + queueNumber + ".");

                // Move customers forward to fill the empty position in the queue.
                for (int i = positionIndex + 1; i < positionLimit; i++) {
                    if (queues[queueIndex][i] != null) {
                        queues[queueIndex][i - 1] = queues[queueIndex][i];
                        queues[queueIndex][i] = null;
                    }
                }
            } else {
                System.out.println("No customer found at position " + position + " in queue " + queueNumber + ".");
            }
        } else {
            System.out.println("Invalid customer position. Please try again.");
        }
    }

    /**
     * Method to remove a served customer from the queue.
     */
    private static void removeServedCustomer() {
        int queueNumber = selectQueue();
        int queueIndex = queueNumber - 1;

        int positionIndex = -1;
        for (int i = 0; i < queues[queueIndex].length; i++) {
            if (queues[queueIndex][i] != null) {
                positionIndex = i;
               break;
            }
        }

        // Using ternary operator to maintain each queue length.
        int positionLimit = (queueIndex == 0) ? 2 : (queueIndex == 1 ? 3 : 5);

        if (positionIndex == 0) {
            String customerName = queues[queueIndex][positionIndex];
            queues[queueIndex][positionIndex] = null;

            // Move customers forward to fill the empty position in the queue.
            for (int j = positionIndex + 1; j < positionLimit; j++) {
                if (queues[queueIndex][j] != null) {
                    queues[queueIndex][j - 1] = queues[queueIndex][j];
                    queues[queueIndex][j] = null;
                }
            }

            System.out.println("Customer " + customerName + " removed from queue " + queueNumber + ".");

            // Remove burgers from burger stock and show a warning if the burger count is low.
            burgerStock -= 5;
            if (burgerStock <= 10 && burgerStock > 0) {
                System.out.println("\nWarning!!! Low burgers in stock \nRemaining burgers: " + burgerStock);
            }
            if (burgerStock <= 0) { // When the burger count reach zero ask to add burgers to the stock.
                System.out.println("\nNo burgers in the stock!");
                question();
            }
        } else {
            System.out.println("No served customer found in queue " + queueNumber + ".");
        }
    }

    /**
     * Method to sort the customer names in alphabetical order and print the names by the order.
     */
    private static void viewCustomersSorted() {
        System.out.println("=================================");
        System.out.println("----- Sorted Customer Names -----");
        System.out.println("=================================");

        String[] customers = allCustomers();

        if (customers.length == 0) {
            System.out.println("No customers found in queues.");
        }
        else {
            // https://stackoverflow.com/questions/18689672/how-to-sort-a-string-array-alphabetically-without-using-compareto-or-arrays-sor

            for (int i = 0; i < customers.length; i++) {
                for (int j = 0; j < customers.length - 1 - i; j++) {
                    if (compareCustomerNames(customers[j], customers[j + 1]) > 0) {
                        String tempName = customers[j];
                        customers[j] = customers[j + 1];
                        customers[j + 1] = tempName;
                    }
                }
            }
        }

        for (String customerName : customers) {
            System.out.println(customerName);
        }
    }

    /**
     * Method to save customer data in to a file.
     */
    // https://www.w3schools.com/java/java_files_create.asp
    private static void storeProgramData() {
        try {
            // Creating date and time format and get the live date and time.
            // https://www.javatpoint.com/java-get-current-date#:~:text=Get%20Current%20Date%20%26%20Time%3A%20java,the%20current%20date%20and%20time.
            DateTimeFormatter liveDateTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime live = LocalDateTime.now();

            File detailFile = new File("FoodCenter.txt");

            if (detailFile.createNewFile()) {
                System.out.println("File created : " + detailFile.getName());
                System.out.println("Path : " + detailFile.getAbsolutePath());
            } else {
                System.out.println("File exist : " + detailFile.getName());
                System.out.println("Path : " + detailFile.getAbsolutePath());
            }

            FileWriter writeDetails = new FileWriter("FoodCenter.txt");

            writeDetails.write("Last saved at: " + liveDateTime.format(live) + "\n\n");

            for (int i = 0; i < queues.length; i++) {
                for (int j = 0; j < queues[i].length; j++) {
                    String customerName = queues[i][j];
                    if (customerName != null) {
                        writeDetails.write("Customer name: " + customerName + "\nQueue no: " + (i + 1) + "\nPosition no: " + (j + 1) + "\n\n");
                    }
                }
            }
            writeDetails.close();
            System.out.println("\nAll the details have been saved into the file successfully.");

        } catch (IOException error) {
            System.out.println("An error occurred. \nPlease try again.");
            error.printStackTrace();
        }
    }

    /**
     * Method to read customer data from the save file.
     */
    // https://www.w3schools.com/java/java_files_read.asp
    private static void loadProgramData() {
        try {
            File detailFile = new File("FoodCenter.txt");
            Scanner readDetails = new Scanner(detailFile);
            System.out.println();
            while (readDetails.hasNextLine()) {
                String details = readDetails.nextLine();
                System.out.println(details);
            }
            readDetails.close();
            System.out.println("\nAll the details have load successfully.");
        }
        catch (FileNotFoundException error) {
            System.out.println("An error occurred. \nPlease try again.");
           error.printStackTrace();
        }
    }

    /**
     * Method to print the remaining burgers in the stock.
     */
    private static void remainingBurgersStock() {
        System.out.println("Remaining burgers in the stock: " + burgerStock);
    }

    /**
     * Method to add burgers to the stock.
     */
    private static void addBurgersToStock() {
        int addBurgers = readInteger("Enter the number of burgers that need to add: ");

        if (addBurgers >= 0 && addBurgers <= 50) {
            if ((addBurgers + burgerStock) >= 0 && (addBurgers + burgerStock) <= maxBurgerStock) {  // Check that when add burgers to the stock the max burger count pass the limit.
                burgerStock += addBurgers;
                System.out.println("Burgers added to the stock. \nBurgers in the stock: " + burgerStock);
            } else {
                System.out.println("\nThere can be maximum number of 50 burgers in the stock. \nNumber of burgers in the stock: " + burgerStock);
                System.out.println("Maximum number of burgers that can be add to the stock: " + (maxBurgerStock - burgerStock));
            }
        } else {
            System.out.println("Incorrect number of burgers! Please try again.");
        }
    }

    /**
     * Printing the queue options and get the input.
     * @return the selected queue number
     */
    private static int selectQueue() {
        System.out.println("\nSelect Queue:");
        System.out.println("1: Queue 01");
        System.out.println("2: Queue 02");
        System.out.println("3: Queue 03");
        int number = readInteger("Enter queue number: ");
        if (number <= queues.length && number >= 1) {
            return number;
        } else {
            System.out.println("Invalid queue number. Please Try again");
            return selectQueue();
        }
    }

    /**
     * Check the queues positions are occupied or not.
     * @param queueIndex the selected queue array index
     * @return array position index
     */
    private static int availablePosition(int queueIndex) {
        for (int i = 0; i < queues[queueIndex].length; i++) {
            if (queues[queueIndex][i] == null) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Create an array and add all the customers names in to the array.
     * @return the array that contain all the customers names
     */
    private static String[] allCustomers() {
        int totalCustomers = 0;

        for (int i = 0; i < queues.length; i++) {
            for (int j = 0; j < queues[i].length; j++) {
                if (queues[i][j] != null) {
                    totalCustomers++;
                }
            }
        }

        String[] customers = new String[totalCustomers]; // Creating an array and select the array length.
        int customerIndex = 0;

        for (int i = 0; i < queues.length; i++) {
            for (int j = 0; j < queues[i].length; j++) {
                if (queues[i][j] != null) {
                    customers[customerIndex] = queues[i][j];
                    customerIndex++;
                }
            }
        }

        return customers;
    }

    /**
     * Asking a question when burger stock reach zero.
     */
    private static void question() {
        int number = readInteger("Would you like to add burgers to the stock \n1 : Yes \n2 : No \nEnter your option: ");

        if (number == 1) {
            System.out.println("Preparing to add burgers to stock...\n");
            addBurgersToStock();
        } else if (number == 2) {
            System.out.println("Returning to the menu...");
        } else {
            System.out.println("Invalid number. Please Try again");
        }
    }

    /**
     * Get two customers names and check for the largest size name.
     * @param customer1 the name of the first selected customer.
     * @param customer2 the name of the second selected customer.
     * @return the difference between selected two names letters or names length.
     */
    // https://stackoverflow.com/questions/18689672/how-to-sort-a-string-array-alphabetically-without-using-compareto-or-arrays-sor
    private static int compareCustomerNames(String customer1, String customer2) {
        int smallLengthName = Math.min(customer1.length(), customer2.length()); // Selecting the lowest length name length.

        for (int i = 0; i < smallLengthName; i++) {
            char letter1 = customer1.charAt(i);
            char letter2 = customer2.charAt(i);

            if (letter1 != letter2) {
                return letter1 - letter2;
            }
        }
        return customer1.length() - customer2.length();
    }

    /**
     * Reading a string using scanner.
     * @param massage the statement that ask for an input.
     * @return the selected string type input.
     */
    private static String readString(String massage) {
        System.out.println(massage);
        try {
            String input = getInput.next();
            getInput.nextLine();
            return input;

        }
        catch (Exception error) {
            getInput.nextLine();
            System.out.println("Invalid response. Please try again.");
            return readString(massage);
        }
    }

    /**
     * Reading an integer using scanner.
     * @param massage the statement that ask for an input.
     * @return the selected integer type input.
     */
    private static int readInteger(String massage) {
        System.out.println(massage);
        try {
            int input = getInput.nextInt();
            getInput.nextLine();
            return input;

        }
        catch (Exception error) {
            getInput.nextLine();
            System.out.println("Invalid response. Please try again.");
            return readInteger(massage);
        }
    }
}
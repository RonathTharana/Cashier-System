/*Student Name : S.W.Ronath Tharana Wijayin
  UoW Number : w1986580
  IIT Number : 20221889*/

package FoodCenter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    // Creating a max burger count in the burger stock.
    private static final int maxBurgerStock = 50;

    // Creating a stock that can add or remove burgers.
    private static int burgerStock = maxBurgerStock;

    // Initializing a scanner to get inputs.
    private static Scanner getInput = new Scanner(System.in);

    // Creating an arraylist to store food queue class objects.
    // https://www.w3schools.com/java/java_arraylist.asp
    private static ArrayList<FoodQueue> queues = new ArrayList<>();

    // Calculate all the served burger count in queue 1.
    private static int queue1ServedBurgers = 0;

    // Calculate all the served burger count in queue 2.
    private static int queue2ServedBurgers = 0;

    // Calculate all the served burger count in queue 3.
    private static int queue3ServedBurgers = 0;


    public static void main(String[] args) {

        // Creating four objects in food queue class.
        FoodQueue foodQueue1 = new FoodQueue(2);
        FoodQueue foodQueue2 = new FoodQueue(3);
        FoodQueue foodQueue3 = new FoodQueue(5);

        FoodQueue foodWaitingQueue = new FoodQueue(50);

        // Add created four objects to the arraylist.
        queues.add(foodQueue1);
        queues.add(foodQueue2);
        queues.add(foodQueue3);
        queues.add(foodWaitingQueue);

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
            System.out.println("110 or IFQ: Income of each queue.");
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

                case "110":
                case "IFQ":
                    // This option will show the income of each queue.
                    incomeOfQueues();
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

        String[][] allQueues = addCustomerNames();

        // Using ternary operator to maintain each queue length.
        // https://www.w3schools.com/java/java_conditions_shorthand.asp
        for (int i = 0; i < allQueues.length; i++) {
            int maxPositions = (i == 2) ? 5 : i + 2;
            for (int j = 0; j < allQueues[i].length; j++) {
                if (j < maxPositions) {
                    if (allQueues[i][j] == null) {
                        allQueues[i][j] = "X";
                    }
                    else {
                        allQueues[i][j] = "O";
                    }
                }
                else {
                    allQueues[i][j] = " ";
                }
            }
        }
        for (int i = 0; i < allQueues.length - 2; i++) {
            for (int j = 0; j < allQueues[i].length; j++) {
                System.out.println("    " + allQueues[i][j] + "   " + allQueues[i + 1][j] + "   " + allQueues[i + 2][j]);
            }
        }
        System.out.println("O - Occupied \nX - Not Occupied");
    }

    /**
     * Method to show the queues condition.
     */
    private static void viewAllEmptyQueues() {

        System.out.println("================");
        System.out.println("---- Queues ----");
        System.out.println("================");

        ArrayList<Customer> queue1 = queues.get(0).getCustomers();
        ArrayList<Customer> queue2 = queues.get(1).getCustomers();
        ArrayList<Customer> queue3 = queues.get(2).getCustomers();


        if (queue1.size() == 2) {
            System.out.println(" queue 1 : Full");
        }
        else {
            System.out.println(" queue 1 : Empty");
        }
        if (queue2.size() == 3) {
            System.out.println(" queue 2 : Full");
        }
        else {
            System.out.println(" queue 2 : Empty");
        }
        if (queue3.size() == 5) {
            System.out.println(" queue 3 : Full");
        }
        else {
            System.out.println(" queue 3 : Empty");
        }
    }

    /**
     * Method to add a new customer to the queue with the minimum length.
     */
    private static void addCustomerToQueue() {

        String firstName = readString("Enter customer first name: ");
        String capitalizeFirstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1); // Capitalize the first letter in customer first name.

        String secondName = readString("Enter customer second name: ");
        String capitalizeSecondName = secondName.substring(0, 1).toUpperCase() + secondName.substring(1); // Capitalize the first letter in customer second name.

        int burgerNumber = readInteger("Enter the number of  burgers required: ");

        // Add customer details to the customer class and create the customer class object.
        Customer customer = new Customer(capitalizeFirstName, capitalizeSecondName, burgerNumber);


        ArrayList<Customer> queue1 = queues.get(0).getCustomers();
        ArrayList<Customer> queue2 = queues.get(1).getCustomers();
        ArrayList<Customer> queue3 = queues.get(2).getCustomers();
        ArrayList<Customer> waitingQueue = queues.get(3).getCustomers();

        // Check all the queues are full or not and if full add customers to the waiting queue.
        if (queue1.size() == 2 && queue2.size() == 3 && queue3.size() == 5) {
            System.out.println("All the queues are full.\nCustomer " + customer.getFirstName() + " " + customer.getSecondName() + " add to the waiting queue.");
            waitingQueue.add(customer);
        }
        else {
            if (queue1.size() < 2 && queue1.size() <= queue2.size() && queue1.size() <= queue3.size()) {  // select the minimum length queue.
                queue1.add(customer);
            } else if (queue2.size() < 3 && queue2.size() <= queue1.size() && queue2.size() <= queue3.size()) {
                queue2.add(customer);
            } else {
                queue3.add(customer);
            }

            System.out.println("Customer " + customer.getFirstName() + " " + customer.getSecondName() + " add to the queue.");
        }
    }


    /**
     * Method to remove a customer from a selected position in a queue.
     */
    private static void removeCustomerFromAQueue() {

        ArrayList<Customer> queue1 = queues.get(0).getCustomers();
        ArrayList<Customer> queue2 = queues.get(1).getCustomers();
        ArrayList<Customer> queue3 = queues.get(2).getCustomers();
        ArrayList<Customer> waitingQueue = queues.get(3).getCustomers();

        int queueNumber = selectQueue();
        int positionNumber = readInteger("Enter customer position: ");
        int positionIndex = positionNumber - 1;

        // Using ternary operator to maintain each queue length
        int positionLimit = (queueNumber == 1) ? 2 : (queueNumber == 2 ? 3 : 5);

        if (positionIndex < positionLimit) {

            if (queueNumber == 1 && !queue1.isEmpty() && positionNumber <= queue1.size()) {
                System.out.println("Customer " + queue1.get(positionIndex).getFirstName() + " " + queue1.get(positionIndex).getSecondName() + " removed from queue " + queueNumber + ".");
                queue1.remove(positionIndex);
                if (queue1.size() < 2 && !waitingQueue.isEmpty()) { // Check for customers in the waiting queue and add customers in to the empty queue.
                    queue1.add(waitingQueue.remove(0));
                }

            } else if (queueNumber == 2 && !queue2.isEmpty() && positionNumber <= queue2.size()) {
                System.out.println("Customer " + queue2.get(positionIndex).getFirstName() + " " + queue2.get(positionIndex).getSecondName() + " removed from queue " + queueNumber + ".");
                queue2.remove(positionIndex);
                if (queue2.size() < 3 && !waitingQueue.isEmpty()) { // Check for customers in the waiting queue and add customers in to the empty queue.
                    queue2.add(waitingQueue.remove(0));
                }

            } else if (queueNumber == 3 && !queue3.isEmpty() && positionNumber <= queue3.size()){
                System.out.println("Customer " + queue3.get(positionIndex).getFirstName() + " " + queue3.get(positionIndex).getSecondName() + " removed from queue " + queueNumber + ".");
                queue3.remove(positionIndex);
                if (queue3.size() < 5 && !waitingQueue.isEmpty()) { // Check for customers in the waiting queue and add customers in to the empty queue.
                    queue3.add(waitingQueue.remove(0));
                }
            }
            else {
                System.out.println("No customer found in that queue position.");
            }
        }
        else {
            System.out.println("Invalid customer position. Please try again.");
        }
    }

    /**
     * Method to remove a served customer from the queue.
     */
    private static void removeServedCustomer() {

        ArrayList<Customer> queue1 = queues.get(0).getCustomers();
        ArrayList<Customer> queue2 = queues.get(1).getCustomers();
        ArrayList<Customer> queue3 = queues.get(2).getCustomers();
        ArrayList<Customer> waitingQueue = queues.get(3).getCustomers();

        int queueNumber = selectQueue();
        int positionIndex = 0;

        if (queueNumber == 1 && !queue1.isEmpty()) {
            int number = burgerLimit(queueNumber, positionIndex);
            if (number == 1) {
                queue1ServedBurgers += queue1.get(positionIndex).getBurgersNumber();
                System.out.println("Customer " + queue1.get(positionIndex).getFirstName() + " " + queue1.get(positionIndex).getSecondName() + " remove from the queue " + queueNumber + ".");
                queue1.remove(positionIndex);
                if (queue1.size() < 2 && !waitingQueue.isEmpty()) { // Check for customers in the waiting queue and add customers in to the empty queue.
                    queue1.add(waitingQueue.remove(0));
                }
            }

        } else if (queueNumber == 2 && !queue2.isEmpty()) {
            int number = burgerLimit(queueNumber, positionIndex);
            if (number == 1) {
                queue2ServedBurgers += queue2.get(positionIndex).getBurgersNumber();
                System.out.println("Customer " + queue2.get(positionIndex).getFirstName() + " " + queue2.get(positionIndex).getSecondName() + " remove from the queue " + queueNumber + ".");
                queue2.remove(positionIndex);
                if (queue2.size() < 3 && !waitingQueue.isEmpty()) { // Check for customers in the waiting queue and add customers in to the empty queue.
                    queue2.add(waitingQueue.remove(0));
                }
            }

        } else if (queueNumber == 3 && !queue3.isEmpty()) {
            int number = burgerLimit(queueNumber, positionIndex);
            if (number == 1) {
                queue3ServedBurgers += queue3.get(positionIndex).getBurgersNumber();
                System.out.println("Customer " + queue3.get(positionIndex).getFirstName() + " " + queue3.get(positionIndex).getSecondName() + " remove from the queue " + queueNumber + ".");
                queue3.remove(positionIndex);
                if (queue3.size() < 5 && !waitingQueue.isEmpty()) { // Check for customers in the waiting queue and add customers in to the empty queue.
                    queue3.add(waitingQueue.remove(0));
                }
            }
        }
        else {
            System.out.println("Selected queue is empty. Please try again.");
        }

        // Show a warning if the burger count is low.
        if (burgerStock <= 10 && burgerStock > 0) {
            System.out.println("\nWarning!!! Low burgers in stock \nRemaining burgers: " + burgerStock);
        }
        if (burgerStock <= 0) { // When the burger count reach 0 ask to add burgers to the stock.
            System.out.println("\nNo burgers in the stock!");
            question();
        }
    }

    /**
     * Method to sort the customer names in alphabetical order and print the names by the order.
     */
    private static void viewCustomersSorted() {

        System.out.println("============================");
        System.out.println("----- Sorted Customers -----");
        System.out.println("============================");

        ArrayList<String> customers = allCustomers();

        if (customers.isEmpty()) {
            System.out.println("No customers found in queues.");
        }
        else {
            // https://stackoverflow.com/questions/18689672/how-to-sort-a-string-array-alphabetically-without-using-compareto-or-arrays-sor
            // https://www.programiz.com/java-programming/library/arraylist/set
            for (int i = 0; i < customers.size() - 1; i++) {
                for (int j = 0; j < customers.size() - 1 - i; j++) {
                    if (compareCustomerNames(customers.get(j), customers.get(j + 1)) > 0) {
                        String tempName = customers.get(j);
                        customers.set(j, customers.get(j + 1));
                        customers.set((j + 1), tempName);
                    }
                }
            }
        }
        for (String customerName : customers) {
            System.out.println(customerName);
        }
    }

    /**
     * Method to save customer data in to a file
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

            ArrayList<Customer> queue1 = queues.get(0).getCustomers();
            ArrayList<Customer> queue2 = queues.get(1).getCustomers();
            ArrayList<Customer> queue3 = queues.get(2).getCustomers();
            ArrayList<Customer> waitingQueue = queues.get(3).getCustomers();

            writeDetails.write("Last saved at: " + liveDateTime.format(live));

            if (!queue1.isEmpty()) {
                writeDetails.write("\n\nQueue 1 customer details");
                for (int i = 0; i < queue1.size(); i++) {
                    writeDetails.write("\n\nCustomer name: " + queue1.get(i).getFirstName() + " " + queue1.get(i).getSecondName());
                    writeDetails.write("\nNumber of burgers required: " + queue1.get(i).getBurgersNumber());
                    writeDetails.write("\nCustomer position in the queue: " + (i + 1));
                }
            }

            if (!queue2.isEmpty()) {
                writeDetails.write("\n\nQueue 2 customer details");
                for (int j = 0; j < queue2.size(); j++) {
                    writeDetails.write("\n\nCustomer name: " + queue2.get(j).getFirstName() + " " + queue2.get(j).getSecondName());
                    writeDetails.write("\nNumber of burgers required: " + queue2.get(j).getBurgersNumber());
                    writeDetails.write("\nCustomer position in the queue: " + (j + 1));
                }
            }

            if (!queue3.isEmpty()) {
                writeDetails.write("\n\nQueue 3 customer details");
                for (int k = 0; k < queue3.size(); k++) {
                    writeDetails.write("\n\nCustomer name: " + queue3.get(k).getFirstName() + " " + queue3.get(k).getSecondName());
                    writeDetails.write("\nNumber of burgers required: " + queue3.get(k).getBurgersNumber());
                    writeDetails.write("\nCustomer position in the queue: " + (k + 1));
                }
            }

            if (!waitingQueue.isEmpty()) {
                writeDetails.write("\n\nWaiting queue customer details");
                for (int l = 0; l < waitingQueue.size(); l++) {
                    writeDetails.write("\n\nCustomer name: " + waitingQueue.get(l).getFirstName() + " " + waitingQueue.get(l).getSecondName());
                    writeDetails.write("\nNumber of burgers required: " + waitingQueue.get(l).getBurgersNumber());
                    writeDetails.write("\nCustomer position in the waiting queue: " + (l + 1));
                }
            }

            writeDetails.close();
            System.out.println("\nAll the details have been saved into the file successfully.");
        }
        catch (Exception error) {
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
     * Method to calculate income of each queue.
     */
    private static void incomeOfQueues() {

        System.out.println("==========================");
        System.out.println("---- Income of Queues ----");
        System.out.println("==========================");

        int queue1Income = queue1ServedBurgers * 650;
        int queue2Income = queue2ServedBurgers * 650;
        int queue3Income = queue3ServedBurgers * 650;
        int totalIncome = queue1Income + queue2Income + queue3Income;

        System.out.println("\nPrice of a burger RS: 650/=\n");

        System.out.println("Queue 1");
        System.out.println("Served burgers in queue: " + queue1ServedBurgers +"\nIncome of queue: \nRs: " + queue1Income);

        System.out.println("\nQueue 2");
        System.out.println("Served burgers in queue: " + queue2ServedBurgers +"\nIncome of queue: \nRs: " + queue2Income);

        System.out.println("\nQueue 3");
        System.out.println("Served burgers in queue: " + queue3ServedBurgers +"\nIncome of queue: \nRs: " + queue3Income);

        System.out.println("\nTotal income in all 3 queues: \nRs: " + totalIncome);

    }

    /**
     * Add all 3 queues customer names in to an array.
     * @return the array that contain all customers first names.
     */
    private static String[][] addCustomerNames() {

        String[][] allQueues = new String[3][5];

        ArrayList<Customer> queue1 = queues.get(0).getCustomers();
        ArrayList<Customer> queue2 = queues.get(1).getCustomers();
        ArrayList<Customer> queue3 = queues.get(2).getCustomers();

        for (int i = 0; i < queue1.size(); i++) {
            allQueues[0][i] = queue1.get(i).getFirstName();
        }
        for (int j = 0; j < queue2.size(); j++) {
            allQueues[1][j] = queue2.get(j).getFirstName();
        }
        for (int k = 0; k < queue3.size(); k++) {
            allQueues[2][k] = queue3.get(k).getFirstName();
        }
        return allQueues;
    }

    /**
     * Printing the queue options and get the input.
     * @return the selected queue number.
     */
    private static int selectQueue() {
        System.out.println("\nSelect Queue:");
        System.out.println("1: Queue 01");
        System.out.println("2: Queue 02");
        System.out.println("3: Queue 03");

        int number = readInteger("Enter queue number: ");
        if (number <= 3 && number >= 1) {
            return number;
        } else {
            System.out.println("Invalid queue number. Please Try again");
            return selectQueue();
        }
    }

    /**
     * Check that the number of burgers customer required is more than the burgers in the stock.
     * @param queueNumber the selected queue number.
     * @param positionIndex the index number of the customer position.
     * @return the number that decide the code other part execute or not.
     */
    private static int burgerLimit (int queueNumber, int positionIndex) {

        ArrayList<Customer> queue1 = queues.get(0).getCustomers();
        ArrayList<Customer> queue2 = queues.get(1).getCustomers();
        ArrayList<Customer> queue3 = queues.get(2).getCustomers();

        if (queueNumber == 1) {
            if (burgerStock < queue1.get(positionIndex).getBurgersNumber()) {
                System.out.println("Burger stock do not contain that much burgers.");
            }
            else {
                burgerStock -= queue1.get(positionIndex).getBurgersNumber();
                return 1;
            }
        } else if (queueNumber == 2) {
            if (burgerStock < queue2.get(positionIndex).getBurgersNumber()) {
                System.out.println("Burger stock do not contain that much burgers.");
            }
            else {
                burgerStock -= queue2.get(positionIndex).getBurgersNumber();
                return 1;
            }
        }
        else {
            if (burgerStock < queue3.get(positionIndex).getBurgersNumber()) {
                System.out.println("Burger stock do not contain that much burgers.");
            }
            else {
                burgerStock -= queue3.get(positionIndex).getBurgersNumber();
                return 1;
            }
        }
        return 0;
    }

    /**
     * Add all the customers fist name and the last name to an arraylist.
     * @return the arraylist that contain all the customers names.
     */
    private static ArrayList<String> allCustomers() {

        ArrayList<Customer> queue1 = queues.get(0).getCustomers();
        ArrayList<Customer> queue2 = queues.get(1).getCustomers();
        ArrayList<Customer> queue3 = queues.get(2).getCustomers();

        ArrayList<String> customers = new ArrayList<>();

        for (int i = 0; i < queue1.size(); i++) {
            customers.add(queue1.get(i).getFirstName() + " " + queue1.get(i).getSecondName());
        }

        for (int j = 0; j < queue2.size(); j++) {
            customers.add(queue2.get(j).getFirstName() + " " + queue2.get(j).getSecondName());
        }

        for (int k = 0; k < queue3.size(); k++) {
            customers.add(queue3.get(k).getFirstName() + " " + queue3.get(k).getSecondName());
        }
        return customers;
    }

    /**
     * Get two customers names and check for the largest size name.
     * @param customer1 the name of the first selected customer.
     * @param customer2 the name of the second selected customer.
     * @return the difference between selected two names letters or names length.
     */
    // https://stackoverflow.com/questions/18689672/how-to-sort-a-string-array-alphabetically-without-using-compareto-or-arrays-sor
    private static int compareCustomerNames(String customer1, String customer2) {
        int smallLengthName = Math.min(customer1.length(), customer2.length()); // Selecting the lowest length name.

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
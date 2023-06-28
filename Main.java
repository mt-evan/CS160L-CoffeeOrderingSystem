import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    private static Map<String, Integer> inventory = new TreeMap<String, Integer>();
    private static List<CoffeeOrder> orders = new ArrayList<CoffeeOrder>();
    private static String logFile = "OrderLog.txt";

    // C:\Users\evant\IdeaProjects\Lab 4\src\OrderLog.txt
    private static String inventoryFile = "";

    public static void main(String[] args) {

        CoffeeOrder order = new CoffeeOrder();
        Scanner scan = new Scanner(System.in);
        String userCommand = "";

        do {
            order = buildOrder();
            System.out.println(order.printOrder());
            orders.add(order);

            System.out.println("Type 'exit' to exit. Type anything else to create a new order.");
            userCommand = scan.nextLine();
            userCommand = userCommand.toLowerCase();
        } while(!userCommand.equals("exit"));

        scan.close();

        try {
            writeOrderLog(logFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Map<String, Integer> readInventory(String filePath) {
        return null;
    }

    private static void writeInventory(String filePath) {}

    private static List<CoffeeOrder> readOrderLog(String filePath) {
        return null;
    }

    private static void writeOrderLog(String logFile) throws IOException {
        try (BufferedWriter buffWriter = new BufferedWriter(new FileWriter(logFile, true))){

            // write stuff
            for (CoffeeOrder drinks : orders){
                buffWriter.write(drinks.printOrder());
                buffWriter.newLine();
            }
            buffWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        orders.clear();     // "After every order has been printed, orders should be empty."

    }

    private static boolean isInInventory(String i) {
        return false;
    }

    private static void updateOrderLog() {}


    private static CoffeeOrder buildOrder() {
        Scanner scn = new Scanner(System.in);
        String customerInput;
        String coffeeType = "";
        int numIngredients = -1;
        CoffeeOrder order = new CoffeeOrder();

        System.out.println("Type 'exit' to finalize order. Type anything else to add a new coffee.");
        customerInput = scn.next();
        customerInput = customerInput.toLowerCase();

        while (!customerInput.equals("exit")){

            coffeeType = "";

            while (!coffeeType.equals("black") && !coffeeType.equals("espresso")) {
                System.out.println("For coffee, enter 'black' for black coffee or 'espresso' for espresso as base.");
                coffeeType = scn.next();
                coffeeType = coffeeType.toLowerCase();
            }

            numIngredients = -1;    // Resets value in case customer orders multiple drinks.

            while (numIngredients < 0) {
                System.out.println("Enter number of decorator ingredients.");
                // Using try and catch for if user doesn't enter a number.

                try {
                    numIngredients = scn.nextInt();
                    if (numIngredients < 0) {
                        System.out.println("Please enter a valid digit.");
                        continue;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid digit.");
                    scn.nextLine();
                }
            }

            scn.nextLine();     // So that the next input is correctly taken in.
            int i = 0;

            switch (coffeeType) {
                case "black" -> {
                    Coffee coffee1 = new BlackCoffee();
                    while (i < numIngredients) {
                        i++;

                        System.out.println("Enter an ingredient: milk, hot water, sugar, whipped cream, caramel, mocha, vanilla, or anything else to cancel ingredient.");
                        customerInput = scn.nextLine();
                        customerInput = customerInput.toLowerCase();
                        switch (customerInput) {
                            case "milk" -> coffee1 = new WithMilk(coffee1);
                            case "hot water" -> coffee1 = new WithHotWater(coffee1);
                            case "sugar" -> coffee1 = new WithSugar(coffee1);
                            case "whipped cream" -> coffee1 = new WithWhippedCream(coffee1);
                            case "caramel" -> coffee1 = new WithFlavor(coffee1, WithFlavor.Syrup.caramel);
                            case "mocha" -> coffee1 = new WithFlavor(coffee1, WithFlavor.Syrup.mocha);
                            case "vanilla" -> coffee1 = new WithFlavor(coffee1, WithFlavor.Syrup.vanilla);
                            default -> System.out.println("No ingredients added");
                        }

                    }
                    order.addCoffee(coffee1);
                }

                // To do... add syrups.
                case "espresso" -> {
                    Coffee espresso1 = new Espresso();
                    while (i < numIngredients) {
                        i++;
                        System.out.println("Enter an ingredient: milk, hot water, sugar, whipped cream, caramel, mocha, vanilla, or anything else to cancel ingredient.");
                        customerInput = scn.nextLine();
                        customerInput = customerInput.toLowerCase();
                        switch (customerInput) {
                            case "milk" -> espresso1 = new WithMilk(espresso1);
                            case "hot water" -> espresso1 = new WithHotWater(espresso1);
                            case "sugar" -> espresso1 = new WithSugar(espresso1);
                            case "whipped cream" -> espresso1 = new WithWhippedCream(espresso1);
                            case "caramel" -> espresso1 = new WithFlavor(espresso1, WithFlavor.Syrup.caramel);
                            case "mocha" -> espresso1 = new WithFlavor(espresso1, WithFlavor.Syrup.mocha);
                            case "vanilla" -> espresso1 = new WithFlavor(espresso1, WithFlavor.Syrup.vanilla);
                            default -> System.out.println("No ingredients added");
                        }

                    }
                    order.addCoffee(espresso1);
                }
            }
            System.out.println("Are you finished ordering? Type 'exit' to finalize or anything else to add another coffee.");
            customerInput = scn.next();
            customerInput = customerInput.toLowerCase();
        }


    //    scn.close();
        return order;
    }

}
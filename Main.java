import java.io.*;
import java.util.*;

/**
 * CS 160L Summer 2023
 * This class is the main driver for the coffee ordering system.
 *
 * @author Evan Tardiff
 */
public class Main {
    private static Map<String, Integer> inventory = new TreeMap<String, Integer>();
    private static List<CoffeeOrder> orders = new ArrayList<CoffeeOrder>();
    private static String logFile = "OrderLog.txt";
    private static String inventoryFile = "Inventory.txt";

    public static void main(String[] args) {
        String option;
        boolean skipCoffee = false;
        System.out.println("Welcome to Java Coffee Co.!");

        try (Scanner input = new Scanner(System.in)) {
            boolean addOrder = true;

            /*
             Update main() so that it loads in the current inventory
             before the user loop starts.
             */
            readInventory("Inventory.txt");

            do {
                    option = "0";
                    System.out.println("Select an option:");
                    System.out.println("\t1. New Order");
                    System.out.println("\t2. Reload Inventory");
                    System.out.println("\t3. Update Inventory");
                    System.out.println("\t4. Update Order Log");
                    System.out.println("\t5. Exit Application");
                    option = input.next();
                    input.nextLine();

                    if (option.equals("2")) {
                        System.out.println("Reloading inventory...");
                        writeInventory(inventoryFile);
                        System.out.println(readInventory(inventoryFile));
                        skipCoffee = true;
                    } else if (option.equals("3")) {
                        skipCoffee = true;
                        writeInventory(inventoryFile);
                    } else if (option.equals("4")) {
                        writeOrderLog(logFile);
                        skipCoffee = true;
                    } else if (option.equals("5")) {
                        System.out.println("Exiting application...");
                        input.close();
                        break;
                    } else if (!option.equals("1")) {
                        System.out.println("Invalid option. Select again.");
                        System.out.println();
                        skipCoffee = true;
                    } else if (option.equals("1")){
                        skipCoffee = false;
                    }

                if (!skipCoffee) {
                    CoffeeOrder order = buildOrder();
                    orders.add(order);
                    System.out.println(order.printOrder());

                    System.out.println("\nWould you like to enter another order (Y or N)?");
                    String yn = input.nextLine();
                    while (!(yn.equalsIgnoreCase("N") || yn.equalsIgnoreCase("Y"))) {
                        System.out.println("Please enter Y or N.");
                        yn = input.nextLine();
                    }
                    addOrder = !yn.equalsIgnoreCase("N");

                }

            } while (addOrder);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Updates inventory and order log before terminating the program.
        if (orders.size() > 0) {
            writeOrderLog(logFile);
            writeInventory(inventoryFile);
        }
    }

    /**
     * This is a helper method that handles the ordering of a coffee.
     * @return the order made by a customer which includes the drinks
     */
    private static CoffeeOrder buildOrder() {
        CoffeeOrder order = new CoffeeOrder();
        try {
            Scanner input = new Scanner(System.in);
            boolean addCoffee = true;

            while (addCoffee) {
                // prompt user to select base coffee type
                System.out.println("Select coffee type:");
                System.out.println("\t1. Black Coffee");
                System.out.println("\t2. Espresso");
                Coffee coffee;

                int option = 0;
                while (option < 1 || option > 2) {
                    if (!input.hasNextInt()) {
                        System.out.println("Please enter a valid number.");
                        input.nextLine();
                    } else {
                        option = input.nextInt();
                        if (option < 1 || option > 2) System.out.println("Please enter a valid option.");
                    }
                }
                input.nextLine();

                if (option == 2) {
                    if (!isInInventory("Espresso")) {
                        System.out.println("Out of Espresso.");
                        continue;
                    }
                    // Espresso is a specific case
                    coffee = new Espresso();
                    inventory.put("Espresso", inventory.get("Espresso") - 1);
                } else {
                    if (!isInInventory("Black Coffee")) {
                        System.out.println("Out of Black Coffee.");
                        continue;
                    }

                    // make BlackCoffee the default case
                    coffee = new BlackCoffee();
                    inventory.put("Black Coffee", inventory.get("Black Coffee") - 1);
                }


                // prompt user for any customizations
                while (option != 0) {
                    System.out.println(String.format("Coffee brewing: %s.", coffee.printCoffee()));
                    System.out.println("Would you like to add anything to your coffee?");
                    System.out.println("\t1. Flavored Syrup");
                    System.out.println("\t2. Hot Water");
                    System.out.println("\t3. Milk");
                    System.out.println("\t4. Sugar");
                    System.out.println("\t5. Whipped Cream");
                    System.out.println("\t0. NO - Finish Coffee");

                    while (!input.hasNextInt()) {
                        System.out.println("Please enter a valid number.");
                        input.nextLine();
                    }
                    option = input.nextInt();
                    input.nextLine();
                    switch (option) {
                        case 1:
                            System.out.println("Please select a flavor:");
                            for (WithFlavor.Syrup flavor : WithFlavor.Syrup.values()) {
                                System.out.println("\t" + String.format("%d. %s", flavor.ordinal() + 1, flavor));
                            }
                            int max = WithFlavor.Syrup.values().length;
                            option = 0;
                            while (option < 1 || option > max) {
                                if (!input.hasNextInt()) {
                                    System.out.println("Please enter a valid number.");
                                    input.nextLine();
                                } else {
                                    option = input.nextInt();
                                    if (option < 1 || option > max) System.out.println("Please enter a valid option.");
                                }
                            }
                            input.nextLine();
                            WithFlavor.Syrup flavor = WithFlavor.Syrup.values()[option - 1];
                            if (!isInInventory(flavor.toString())) {
                                System.out.println("Out of " + flavor + ".");
                                continue;
                            }
                            option = 1;
                            coffee = new WithFlavor(coffee, flavor);
                            inventory.put(flavor.toString(), inventory.get(flavor.toString()) - 1);
                            break;

                        case 2:
                            if (!isInInventory("Hot Water")) {
                                System.out.println("Out of Hot Water.");
                                continue;
                            }
                            coffee = new WithHotWater(coffee);
                            inventory.put("Hot Water", inventory.get("Hot Water") - 1);

                            break;

                        case 3:
                            if (!isInInventory("Milk")) {
                                System.out.println("Out of Milk.");
                                continue;
                            }
                            coffee = new WithMilk(coffee);
                            inventory.put("Milk", inventory.get("Milk") - 1);
                            break;

                        case 4:
                            if (!isInInventory("Sugar")) {
                                System.out.println("Out of Sugar.");
                                continue;
                            }
                            coffee = new WithSugar(coffee);
                            inventory.put("Sugar", inventory.get("Sugar") - 1);
                            break;

                        case 5:
                            if (!isInInventory("Whipped Cream")) {
                                System.out.println("Out of Whipped Cream.");
                                continue;
                            }
                            coffee = new WithWhippedCream(coffee);
                            inventory.put("Whipped Cream", inventory.get("Whipped Cream") - 1);
                            break;

                        default:
                            if (option != 0) System.out.println("Please enter valid option.");
                            coffee = coffee;
                            break;
                    }
                }

                // Prompts the customer to choose one of three sizes for their drink.
                option = -1;
                while(option < 1 || option > 3){
                    System.out.println("Select a size for your coffee:");
                    System.out.println("\t1. Small");
                    System.out.println("\t2. Medium");
                    System.out.println("\t3. Large");

                    while (!input.hasNextInt()) {
                        System.out.println("Please enter a valid number.");
                        input.nextLine();
                    }

                    option = input.nextInt();
                    input.nextLine();

                    switch (option) {
                        case 1:
                            // make coffee small
                            if (!isInInventory("Small")) {
                                System.out.println("Out of Small cups.");
                                continue;
                            }
                            coffee = new Small(coffee);
                            inventory.put("Small", inventory.get("Small") - 1);
                            break;

                        case 2:
                            // make coffee medium
                            if (!isInInventory("Medium")) {
                                System.out.println("Out of Medium cups.");
                                continue;
                            }
                            coffee = new Medium(coffee);
                            inventory.put("Medium", inventory.get("Medium") - 1);
                            break;

                        case 3:
                            // make coffee large
                            if (!isInInventory("Large")) {
                                System.out.println("Out of Large cups.");
                                continue;
                            }
                            coffee = new Large(coffee);
                            inventory.put("Large", inventory.get("Large") - 1);
                            break;

                        default:
                            System.out.println("Please select a valid option.");
                    }

                }
                order.addCoffee(coffee);    // Add the coffee to the order.

                System.out.println("Would you like to order another coffee (Y or N)?");
                String yn = input.nextLine();
                while (!(yn.equalsIgnoreCase("N") || yn.equalsIgnoreCase("Y"))) {
                    System.out.println("Please enter Y or N.");
                    yn = input.nextLine();
                }
                addCoffee = !yn.equalsIgnoreCase("N");
            }
        } catch (Exception e) {
            System.out.println("Error building order: " + e.getMessage());
        }
        return order;
    }

    /**
     * This method reads the inventory from a file and prints it for the user.
     *
     * @param filePath holds the path to "Inventory.txt" so that it can be read from
     * @return the inventory that deals with what ingredients are in stock
     */
    private static Map<String, Integer> readInventory(String filePath) {
        try (BufferedReader buffReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            Map<String, Integer> tempInventory = new LinkedHashMap<>();

            while ((line = buffReader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String ingredient = parts[0].trim();
                    int quantity = Integer.parseInt(parts[1].trim());
                    tempInventory.put(ingredient, quantity);
                }
            }
            inventory = tempInventory;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return inventory;
    }

    /**
     * This method updates the number of ingredients left available on "Inventory.txt"
     *
     * @param filePath holds the path to "Inventory.txt" so that it can be written to
     */
    private static void writeInventory(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                String ingredient = entry.getKey();
                int quantity = entry.getValue();
                writer.write(ingredient + " = " + quantity);
                writer.newLine();
            }
            System.out.println("Inventory updated.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * This method writes down the order receipt to "OrderLog.txt"
     *
     * @param filePath holds the path to "OrderLog.txt" so that it can be written to
     */
    private static void writeOrderLog(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (CoffeeOrder order : orders) {
                writer.write(order.printOrder());
                writer.newLine();
            }
            System.out.println("Successfully updated order log");
            writer.flush();
            orders.clear();
        } catch (Exception e) {
            System.out.println("Error writing order log: " + e.getMessage());
        }
    }

    /**
     *
     * @param i is the item in inventory that is being checked
     * @return whether the item we are looking is available
     */
    private static boolean isInInventory(String i) {
        String trimmedIngredient = i.trim();
        return inventory.containsKey(trimmedIngredient) && inventory.get(trimmedIngredient) > 0;
    }

}
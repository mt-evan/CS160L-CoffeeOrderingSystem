import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * CS160L Summer 2023
 * This class holds the order a customer makes with relevant information.
 *
 * @author Evan Tardiff
 */
public class CoffeeOrder {
    private List<Coffee> coffees;
    private LocalDateTime orderDate;

    /**
     * Constructor which assigns relevant values to fields that holds the coffees and the date/time.
     */
    public CoffeeOrder() {
        coffees = new ArrayList<Coffee>();
        orderDate = LocalDateTime.now();
    }

    public CoffeeOrder(LocalDateTime orderDate) {
        coffees = new ArrayList<Coffee>();
        this.orderDate = orderDate;
    }

    /**
     * Constructor which accepts the coffee to be decorated.
     * @param c the coffee to be decorated
     */
    public void addCoffee(Coffee c) {
        coffees.add(c);
    }

    public List<Coffee> getCoffees() { return coffees; }

    public LocalDateTime getOrderDate() { return orderDate; }

    /**
     * This method calculates the total price of the whole order which includes all coffees and all ingredients.
     * @return the cost of the order
     */
    public double getTotal() {
        double total = 0;
        for (Coffee coffee : coffees) {
            total += coffee.getCost();
        }
        return total;
    }

    /**
     * This method is used so that a receipt can be printed out to a text file.
     * @return The string value of an order that lists what was ordered, its price, and the date/time
     */
    public String printOrder() {
        StringBuilder order = new StringBuilder("ORDER RECEIPT\n");
        order.append(String.format("Timestamp: %s%n", orderDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mma"))));
        for (int i = 0; i < coffees.size(); i++) {
            Coffee coffee = coffees.get(i);
            order.append(String.format("Item %d: %s - %.2f%n", i + 1, coffee.printCoffee(), coffee.getCost()));
        }
        order.append(String.format("TOTAL = %.2f%n", getTotal()));
        return order.toString();
    }
}

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.format.*;
import java.util.List;

public class CoffeeOrder {
    private List<Coffee> coffees;
    private LocalDateTime orderDate;
    private String dateTime;  // Will hold the value for the date and time to get outputted.

    public CoffeeOrder() {
        coffees = new ArrayList<Coffee>();
        orderDate = LocalDateTime.now();

        // modifies orderDate so that it comes out neat
        DateTimeFormatter frm = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mma");
        dateTime = orderDate.format(frm);
    }

    // Adds a second constructor which takes in a LocalDateTime and stores it accordingly.
    public CoffeeOrder(LocalDateTime orderDate){
        this.orderDate = orderDate;
        // modifies orderDate so that it comes out neat
        DateTimeFormatter frm = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mma");
        dateTime = this.orderDate.format(frm);
    }

    public void addCoffee(Coffee c) {
        coffees.add(c);
    }

    public List<Coffee> getCoffees() {
        return coffees;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public double getTotal() {
        double total = 0;
        for (Coffee coffee : coffees) {
            total += coffee.getCost();
        }
        return total;
    }

    public String printOrder() {
        String order = "ORDER RECEIPT\n";
        order += "Timestamp: " + dateTime + "\n";
        for (int i = 0; i < coffees.size(); i++) {
            Coffee coffee = coffees.get(i);
            order += "Item " + (i + 1) + ": " + coffee.printCoffee() + " - " + String.format("%.2f" , coffee.getCost()) + "\n";
        }
        order += "TOTAL = " + String.format("%.2f", getTotal()) + "\n";
        return order;
    }
}

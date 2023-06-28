import java.util.List;

/**
 * CS160L Summer 2023
 * This is a parent class for coffees with ingredients.
 *
 * @author Evan Tardiff
 */
public abstract class CoffeeDecorator implements Coffee {
    private Coffee coffee;

    /**
     * Constructor which accepts the coffee to be decorated.
     * @param c the coffee to be decorated
     */
    public CoffeeDecorator(Coffee c) {
        coffee = c;
    }

    public double getCost() { return coffee.getCost(); }

    public List<String> getIngredients() {
        return coffee.getIngredients();
    }

    public String printCoffee() {
        return coffee.printCoffee();
    }
}

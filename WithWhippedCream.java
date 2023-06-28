import java.util.List;

/**
 * CS160L Summer 2023
 * This class acts as the whipped cream topping.
 *
 * @author Evan Tardiff
 */
public class WithWhippedCream extends CoffeeDecorator {

    /**
     * Constructor which accepts the coffee to be decorated.
     * @param c the coffee to be decorated
     */
    public WithWhippedCream(Coffee c) {
        super(c);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.25;
    }

    @Override
    public List<String> getIngredients() {
        List<String> ingredients = super.getIngredients();
        ingredients.add("Whipped cream");
        return ingredients;
    }

    @Override
    public String printCoffee() {
        return super.printCoffee() + " with whipped cream";
    }
}

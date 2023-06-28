import java.util.List;

/**
 * CS160L Summer 2023
 * This class acts as the size that the coffee will be in.
 *
 * @author Evan Tardiff
 */
public class Small extends CoffeeDecorator{

    /**
     * Constructor which accepts the coffee to be decorated.
     * @param c the coffee to be decorated
     */
    public Small(Coffee c) {
        super(c);
    }

    @Override
    public double getCost() {
        return super.getCost();
    }

    @Override
    public List<String> getIngredients() {
        List<String> ingredients = super.getIngredients();
        ingredients.add("Small");
        return ingredients;
    }

    @Override
    public String printCoffee() {
        return super.printCoffee() + " of size small";
    }
}

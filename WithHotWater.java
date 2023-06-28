import java.util.List;

/**
 * CS160L Summer 2023
 * This class acts as the coffee ingredient hot water.
 *
 * @author Evan Tardiff
 */
public class WithHotWater extends CoffeeDecorator {

    /**
     * Constructor which accepts the coffee to be decorated.
     * @param c the coffee to be decorated
     */
    public WithHotWater(Coffee c) {
        super(c);
    }

    @Override
    public double getCost() {
        return super.getCost();
    }

    @Override
    public List<String> getIngredients() {
        List<String> ingredients = super.getIngredients();
        ingredients.add("Hot water");
        return ingredients;
    }

    @Override
    public String printCoffee() {
        return super.printCoffee() + " with hot water";
    }
}

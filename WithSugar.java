import java.util.List;

/**
 * CS160L Summer 2023
 * This class acts as the coffee ingredient sugar.
 *
 * @author Evan Tardiff
 */
public class WithSugar extends CoffeeDecorator {

    /**
     * Constructor which accepts the coffee to be decorated.
     * @param c the coffee to be decorated
     */
    public WithSugar(Coffee c) {
        super(c);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.15;
    }

    @Override
    public List<String> getIngredients() {
        List<String> ingredients = super.getIngredients();
        ingredients.add("Sugar");
        return ingredients;
    }

    @Override
    public String printCoffee() {
        return super.printCoffee() + " with sugar";
    }
}

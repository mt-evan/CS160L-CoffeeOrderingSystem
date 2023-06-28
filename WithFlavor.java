import java.util.List;

/**
 * CS160L Summer 2023
 * This class holds the three enums that represent different flavors of syrup.
 *
 * @author Evan Tardiff
 */
public class WithFlavor extends CoffeeDecorator {

    /**
     * This documents "Syrup."
     * They are the three different flavors of syrup.
     */
    enum Syrup {
        CARAMEL,
        MOCHA,
        VANILLA
    }

    private Syrup flavor;

    /**
     * Constructor which accepts the coffee to be decorated and syrup to add as a topping.
     * @param c the coffee being customized
     * @param s the syrup being added to the coffee
     */
    public WithFlavor(Coffee c, Syrup s) {
        super(c);
        flavor = s;
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.35;
    }

    @Override
    public List<String> getIngredients() {
        List<String> ingredients = super.getIngredients();
        ingredients.add(flavor.toString());
        return ingredients;
    }

    @Override
    public String printCoffee() {
        return super.printCoffee() + " with " + flavor;
    }
}

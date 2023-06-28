import java.util.ArrayList;
import java.util.List;

/**
 * CS160L Summer 2023
 * This class works as an espresso base.
 *
 * @author Evan tardiff
 */
public class Espresso implements Coffee {
    @Override
    public double getCost() {
        return 1.75;
    }

    @Override
    public List<String> getIngredients() {
        List<String> ingredients = new ArrayList<>();
        ingredients.add("Espresso");
        return ingredients;
    }

    @Override
    public String printCoffee() {
        return "An espresso";
    }
}


import java.util.ArrayList;
import java.util.List;

/**
 * CS160L Summer 2023
 * This class acts as a black coffee base.
 *
 * @author Evan Tardiff
 */
public class BlackCoffee implements Coffee {
    @Override
    public double getCost() {
        return 1.0;
    }

    @Override
    public List<String> getIngredients() {
        List<String> ingredients = new ArrayList<>();
        ingredients.add("Black coffee");
        return ingredients;
    }

    @Override
    public String printCoffee() {
        return "A black coffee";
    }
}

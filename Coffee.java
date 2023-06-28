import java.util.List;

/**
 * CS160L Summer 2023
 * This interface represents every individual coffee object.
 *
 * @author Evan Tardiff
 */
public interface Coffee {
    /**
     * Calculates the cost of the coffee including the cost of any added ingredients.
     * @return the calculated cost of the coffee and any added ingredients.
     */
    public double getCost();

    /**
     * Assembles a list of the ingredients added to this coffee, adding them
     * to the base coffee ingredient before returning.
     *
     * @return a list containing all the ingredients added to his coffee including the coffee itself
     */
    public List<String> getIngredients();

    /**
     * Acts like a toString() method by building a string that describes the coffee
     * and all of its ingredients.
     *
     * @return a string describing the coffee and all its ingredients
     */
    public String printCoffee();
}

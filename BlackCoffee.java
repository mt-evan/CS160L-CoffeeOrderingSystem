import java.util.List;

public class BlackCoffee implements Coffee {
    @Override
    public double getCost() {
        return 1.0;
    }

    @Override
    public List<String> getIngredients() {
        return null;
    }

    @Override
    public String printCoffee() {
        return "A black coffee";
    }
}

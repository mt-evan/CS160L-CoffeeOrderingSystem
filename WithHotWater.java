import java.util.List;

public class WithHotWater extends CoffeeDecorator {
    public WithHotWater(Coffee c) {
        super(c);
    }

    @Override
    public double getCost() {
        return super.getCost();
    }

    @Override
    public List<String> getIngredients() {
        return null;
    }

    @Override
    public String printCoffee() {
        return super.printCoffee() + " with hot water";
    }
}

package machine;

import java.util.ArrayList;
import java.util.List;

public class Coffee {
    private List<Integer> ingredients;
    private int price;

    public Coffee(CoffeeType type) {
        ingredients = new ArrayList<>();
        switch (type) {
            case CAPPUCCINO:
                this.ingredients.add(200);
                this.ingredients.add(100);
                this.ingredients.add(12);
                this.price = 6;
                break;
            case LATE:
                this.ingredients.add(350);
                this.ingredients.add(75);
                this.ingredients.add(20);
                this.price = 7;
                break;
            case ESPRESSO:
                this.ingredients.add(250);
                this.ingredients.add(0);
                this.ingredients.add(16);
                this.price = 4;
                break;
            default:
                System.out.println("Unsupported enum constant.");
        }
    }

    public int getPrice() {
        return this.price;
    }

    public List<Integer> getIngredients() {
        return this.ingredients;
    }
}

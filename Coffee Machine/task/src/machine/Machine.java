package machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Machine {
    private List<Integer> storage;
    private int dispCups;
    private int money;

    public Machine() {
        this.storage = new ArrayList<>();
        this.storage.add(400);
        this.storage.add(540);
        this.storage.add(120);
        this.dispCups = 9;
        this.money = 550;
    }

    // adds ingredient for 'fill' method
    public void addIngredient(Integer ingredient, int index) {
        this.storage.set(index, this.storage.get(index) + ingredient);
    }

    public void printState(){
        System.out.println("\nThe coffee machine has:\n" +
                this.storage.get(0) + " ml of water\n" +
                this.storage.get(1) + " ml of milk\n" +
                this.storage.get(2) + " g of coffee beans\n" +
                this.dispCups + " disposable cups\n$" +
                this.money + " of money\n");
    }

    public void take(){
        System.out.println("I gave you $" + this.money + "\n");
        this.money = 0;
    }

    public void addDispCups(int dispCups) {
        this.dispCups += dispCups;
    }

    // returns true if we could buy
    public boolean buy(CoffeeType coffeeType) {
        Coffee buying = new Coffee(coffeeType);
        if(ableToMake(this.storage, buying.getIngredients()) > 0){
            System.out.println("I have enough resources, making you a coffee!");
            this.money += buying.getPrice();
            for (int i = 0; i < 3; i++) {
                this.storage.set(i, this.storage.get(i) - buying.getIngredients().get(i));
            }
            this.dispCups--;
            return true;
        } else {
            System.out.println("Sorry, not enough " + lacking(this.storage, buying.getIngredients()) + "\n");
        }
        return false;
    }
    public static int ableToMake(List<Integer> storage, List<Integer> needed){
        List<Integer> cups = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            if(needed.get(i) == 0) i++; // espresso error / 0
            cups.add(storage.get(i) / needed.get(i));
        }
        Optional<Integer> min = cups.stream().min(Integer::compare);
        return min.get();
    }

    public static String lacking(List<Integer> storage, List<Integer> needed){
        for(int i = 0; i < 3; i++) {
            if(needed.get(i) == 0) i++; // espresso error / 0
            if (storage.get(i) / needed.get(i) < 1) {
                if(i == 0) return "water!";
                if(i == 1) return "milk!";
                if(i == 2) return "coffee beans!";
            }
        }
        return "";
    }


}

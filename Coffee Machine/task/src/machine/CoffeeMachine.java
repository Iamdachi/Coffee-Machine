package machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        /*
        System.out.println("Starting to make a coffee\n" +
                "Grinding coffee beans\n" +
                "Boiling water\n" +
                "Mixing boiled water with crushed coffee beans\n" +
                "Pouring coffee into the cup\n" +
                "Pouring some milk into the cup\n" +
                "Coffee is ready!");


        Scanner scanner = new Scanner(System.in);
        List<Integer> storage = new ArrayList<>();
        System.out.println("Write how many ml of water the coffee machine has: ");
        storage.add(Integer.parseInt(scanner.nextLine()));

        System.out.println("Write how many ml of milk the coffee machine has: ");
        storage.add(Integer.parseInt(scanner.nextLine()));

        System.out.println("Write how many grams of coffee beans the coffee machine has: ");
        storage.add(Integer.parseInt(scanner.nextLine()));


        List<Integer> needed = new ArrayList<>();
        System.out.println("Write how many cups of coffee you will need:");
        int quant = Integer.parseInt(scanner.nextLine());
        needed.add(quant * 200);
        needed.add(quant * 50);
        needed.add(quant * 15);

        /*System.out.println("For " + quant + " cups of coffee you will need:\n" +
                needed.get(0) + " ml of water\n" +
                needed.get(1) + " ml of milk\n" +
                needed.get(2) + " g of coffee beans");

        if(haveEnoughMaterials(needed, storage)) {
            // update storage
            for(int i = 0; i < 3; i++) {
                storage.set(i, storage.get(i) - needed.get(i));
            }
            int cupsLeft = ableToMake(storage);
            if(cupsLeft == 0) System.out.println("Yes, I can make that amount of coffee");
            else System.out.printf("Yes, I can make that amount of coffee (and even %d more than that)\n", cupsLeft);
        } else {
            System.out.printf("No, I can make only %d cup(s) of coffee\n", ableToMake(storage));
        }
        */
        Scanner scanner = new Scanner(System.in);

        Machine dachisCoffeeMachine = new Machine();
        while(true) {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            String uInput = scanner.nextLine();

            if(uInput.compareTo("exit") == 0) {
                break;
            }
            if (uInput.compareTo("remaining") == 0) {
                dachisCoffeeMachine.printState();
            }
            if (uInput.compareTo("take") == 0) {
                dachisCoffeeMachine.take();
            }
            if (uInput.compareTo("buy") == 0) {
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                String inputLine = scanner.nextLine();
                if(inputLine.compareTo("back") != 0) {
                    int which = Integer.parseInt(inputLine);
                    if (which == 1) {
                        dachisCoffeeMachine.buy(CoffeeType.ESPRESSO);
                    }
                    if (which == 2) {
                        dachisCoffeeMachine.buy(CoffeeType.LATE);
                    }
                    if (which == 3) dachisCoffeeMachine.buy(CoffeeType.CAPPUCCINO);
                }

            }
            if (uInput.compareTo("fill") == 0) {
                System.out.println("\nWrite how many ml of water you want to add: ");
                dachisCoffeeMachine.addIngredient(Integer.parseInt(scanner.nextLine()), 0);

                System.out.println("Write how many ml of milk you want to add: ");
                dachisCoffeeMachine.addIngredient(Integer.parseInt(scanner.nextLine()), 1);

                System.out.println("Write how many grams of coffee beans you want to add: ");
                dachisCoffeeMachine.addIngredient(Integer.parseInt(scanner.nextLine()), 2);

                System.out.println("Write how many disposable cups of coffee you want to add: ");
                dachisCoffeeMachine.addDispCups(Integer.parseInt(scanner.nextLine()));
            }

        }
    }

    public static boolean haveEnoughMaterials(List<Integer> needed, List<Integer> storage){
        for(int i = 0; i < 3; i++) {
            if (needed.get(i) > storage.get(i)) {
                return false;
            }
        }
        return true;
    }

    public static int ableToMake(List<Integer> storage){
        List<Integer> needed = new ArrayList<>();
        needed.add(200);
        needed.add(50);
        needed.add(15);

        List<Integer> cups = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            cups.add(storage.get(i) / needed.get(i));
        }
        Optional<Integer> min = cups.stream().min(Integer::compare);
        return min.get();
    }

}

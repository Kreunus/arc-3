import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
/**
 * Beschreiben Sie hier die Klasse Actor.
 * 
 * @author Aaron Winter 
 * @version 2018.01.21
 */
public class Actor
{
    private static int WEIGHT_MAX = 40000;
    private static int CALORIES_MAX = 2500;
    
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private String firstName;
    private String lastName;
    
    private int weight;
    private int calories;

    private Inventory inventory;

    /**
     * Constructor of an object actor
     */
    public Actor(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;

        this.weight = 0;
        this.calories = CALORIES_MAX;
        
        this.inventory = new Inventory();
    }
    
    public boolean step() {
        calories -= 10;
        
        if (calories <= 0) {
            System.out.println("You starved! Game Over!");
            return true;
        }
        return false;
    }

    /**
     * @return first name of the actor
     */
    public String getFirstName() { return firstName; }

    /**
     * @return last name of the actor
     */
    public String getLastName() { return lastName; }

    /**
     * @return complete name of the actor
     */
    public String getName() { return firstName + " " + lastName; }

    /**
     * @return the String s generated below
     */
    public String getDetails() {
        String s = "Details:";
        s += "\n" + getName();
        s += "\nWeight: " + weight + "/" + WEIGHT_MAX;
        s += "\nKalories: " + calories + "/" + CALORIES_MAX;
        s += "\nList of ";
        s += getItemStrings();
        return s;
    }

    /**
     * @return the String s generated a list of all items in the bag
     */
    public String getItemStrings(){
        return inventory.details();
    }

    /**
     * logic of taking an item from a room and adding it to the inventory
     */
    public void takeItem(String itemName, Inventory roomItems) {
        if (roomItems.item(itemName) != null) {
            if ((weight + roomItems.item(itemName).getWeight()) <= WEIGHT_MAX) {
                weight += (roomItems.item(itemName).getWeight());
                inventory.add(roomItems.item(itemName));
                roomItems.remove(itemName);
                System.out.println("You took " + itemName);
            } else {
                System.out.println("It's too heavy!");
            }
        } else {
            System.out.println("There is no such item. Note upper and lower case writing");
        }
    }

    /**
     * logic of droping an item from the invetory and adding it to a room
     */
    public void dropItem(String itemName, Inventory roomItems) {
        if (inventory.item(itemName) != null) {
            weight -= inventory.item(itemName).getWeight();
            inventory.remove(itemName);
            roomItems.add(inventory.item(itemName));
            System.out.println("You dropped " + itemName);
        }
        else {
            System.out.println(itemName + " is not in your inventory. Note upper and lower case writing");
        }
    }

    // Not working yet
    /**
     * logic of taking all items from a room and adding them to the inventory
     */   
    public void takeAllItems(Inventory roomItems)
    {
    }

    // Not working yet
    /**
     * logic of droping all items from the invetory and adding them to a room
     */
    public void dropAllItems(Inventory roomItems)
    {
    }

    /**
     * logic of eating an item from the inventory
     * @param itemName the name of the item which should be eaten
     */
    public void eatItem(String itemName) {
        if (inventory.item(itemName) != null) {
            if(inventory.item(itemName).isEatable()) {
                weight -= inventory.item(itemName).getWeight();
                inventory.remove(itemName);        
                System.out.println("You ate " + itemName);
            }
            else {
                System.out.println("You can't eat " + itemName);
            }
            if (itemName.toLowerCase().equals("cookie")) {
                WEIGHT_MAX += 1000;
                System.out.println("Your max weight increased by 1000!");
            }
        }
        else {
            System.out.println("There is no such item in your inventory");
        }
    }
}

import java.util.HashMap;
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    private static int WEIGHT_MAX = 40000;
    private static int CALORIES_MAX = 2500;
    private static int CALORIES_STEP_COST = 10;
    private int weight;
    private int calories;
    
    private HashMap<String, Slot> inventory;
    
    /**
     * Constructor for objects of class Player
     */
    public Player(String firstName, String lastName)
    {
        super(firstName, lastName);
        this.weight = 0;
        this.calories = CALORIES_MAX;
        
        this.inventory = new HashMap();
    }
    
    public boolean step() {
        calories -= CALORIES_STEP_COST;
        if (calories <= 0) {
            System.out.println("You starved! Game Over!");
            return true;
        }
        return false;
    }
    
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
        String s = "Items:";
        for (Slot slot : inventory.values()) {
        s += "\n" + slot.details();
        }
        return s;
    }

    /**
     * logic of taking an item from a room and adding it to the inventory
     */
    public void takeItem(String itemName, HashMap<String, Slot> roomItems) {
        if (roomItems.get(itemName) != null) {
            if ((weight + roomItems.get(itemName).item().getWeight()) <= WEIGHT_MAX) {
                weight += (roomItems.get(itemName).item().getWeight());
                inventory.put(itemName, roomItems.get(itemName));
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
    public void dropItem(String itemName, HashMap<String, Slot> roomItems) {
        if (inventory.get(itemName) != null) {
            weight -= inventory.get(itemName).item().getWeight();
            inventory.remove(itemName);
            roomItems.put(itemName, inventory.get(itemName));
            System.out.println("You dropped " + itemName);
        }
        else {
            System.out.println(itemName + " is not in your inventory. Note upper and lower case writing");
        }
    }
    
    /**
     * logic of droping all items from the invetory and adding them to a room
     */
    public void dropAllItems(HashMap<String, Slot> roomItems)
    {
        for (Slot slot : inventory.values()) {
            for(slot.number() ; slot.number() > 0; slot.remove()) {
                roomItems.put(slot.item().getName(), slot);
                weight -= slot.item().getWeight();
                System.out.println("You dropped " + slot.item().getName());
            }
        }
        inventory.clear();
    }

    /**
     * logic of eating an item from the inventory
     * @param itemName the name of the item which should be eaten
     */
    public void eatItem(String itemName) {
        if (inventory.get(itemName) != null) {
            if(inventory.get(itemName).item().isEatable()) {
                weight -= inventory.get(itemName).item().getWeight();
                calories += inventory.get(itemName).item().getCalories();
                if(calories > CALORIES_MAX)
                    calories = CALORIES_MAX;
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

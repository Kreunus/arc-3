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
    
    private Inventory inventory;
    
    /**
     * Constructor for objects of class Player
     */
    public Player(String firstName, String lastName)
    {
        super(firstName, lastName, "");
        this.weight = 0;
        this.calories = CALORIES_MAX;
        
        this.inventory = new Inventory();
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
        for (String itemName : inventory.slots().keySet()) {
        s += "\n" + inventory.slots().get(itemName).details();
        }
        return s;
    }
    
    /**
     * logic of droping an item from the invetory and adding it to a room
     */
    public void dropItem(String itemName) {
        Inventory roomItems = currentRoom.getItems();
        if (inventory.get(itemName) == null) {
            System.out.println("There is no such item!");
        }
        else {
            weight -= (inventory.get(itemName).item().getWeight());
            roomItems.add(inventory.get(itemName));                
            inventory.remove(itemName);
        }
    }
    
    /**
     * logic of taking an item from the current room and adding it to the inventory
     */
    public void takeItem(String itemName) {
        Inventory roomItems = currentRoom.getItems();
        if (roomItems.get(itemName) == null) {
            System.out.println("There is no such item!");
        }
        else if ((weight + roomItems.get(itemName).item().getWeight()) >= WEIGHT_MAX) {
            System.out.println("It's too heavy!");
        }
        else {
            weight += (roomItems.get(itemName).item().getWeight());
            inventory.add(roomItems.get(itemName));                
            roomItems.remove(itemName);
        }
    }
    
    /**
     * logic of droping all items from the invetory and adding them to a room
     */
    public void dropAllItems()
    {
        Inventory roomItems = currentRoom.getItems();
        roomItems.addAll(inventory.slots());
        inventory.slots().clear();
        weight = 0;
        System.out.println("You dropped all Items");
    }
    
    /**
     * logic of taking all items from the room and adding them to the inventory
     */
    public void takeAllItems()
    {
        Inventory roomItems = currentRoom.getItems();
        Inventory itemsToAdd = new Inventory();
        
        for (String itemName : roomItems.slots().keySet()) {
            if ((weight + roomItems.get(itemName).weight()) > WEIGHT_MAX ) {
                System.out.println(itemName + " is too heavy to pick up");
            }
            else {
                weight += roomItems.get(itemName).weight();
                itemsToAdd.add(roomItems.get(itemName));
                System.out.println("You took " + roomItems.get(itemName).number() + "x " + itemName);
            }
        }
        inventory.addAll(itemsToAdd.slots());
        for (String itemName : itemsToAdd.slots().keySet()) {
            roomItems.removeSlot(itemName);
        }
    }

    /**
     * logic of eating an item from the inventory
     * @param itemName the name of the item which should be eaten
     */
    public void eatItem(String itemName) {
        if (inventory.get(itemName) == null) {
            System.out.println("There is no such item in your inventory");
        }
        else {
            if(!inventory.get(itemName).item().isEatable()) {
                System.out.println("You can't eat " + itemName);
            }
            else {
                weight -= inventory.get(itemName).item().getWeight();
                calories += inventory.get(itemName).item().getCalories();
                if(calories > CALORIES_MAX)
                    calories = CALORIES_MAX;
                inventory.remove(itemName);        
                System.out.println("You ate " + itemName);
                if (itemName.toLowerCase().equals("cookie")) {
                    WEIGHT_MAX += 1000;
                    System.out.println("Your max weight increased by 1000!");
                }
            }
        }
    }
}

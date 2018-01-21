import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
/**
 * Beschreiben Sie hier die Klasse Actor.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Actor
{
    private static int weight_max = 40000;
    
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private String surName;
    private String lastName;
    private int weight;
    
    private ArrayList<String> responses;
    private HashMap<String, Item> inventory;
    
    public Actor(String surName, String lastName)
    {
        this.surName = surName;
        this.lastName = lastName;
        this.responses = new ArrayList<>();
        this.inventory = new HashMap<>();
        
        this.weight = 0;
    }
    
    public void takeItem(Item item, Room currentRoom) {
        if (item != null) {
            this.weight += (item.getWeight());
            inventory.put(item.getName(), item);
            currentRoom.removeItem(item.getName());
            System.out.println("You took " + item.getName());
        }
        else {
            System.out.println("There is no such item. Note upper and lower case writing");
        }
    }
    
    public Item dropItem(String itemName, Room currentRoom) {
        Item item;
        if (inventory.get(itemName) != null) {
            item = inventory.get(itemName);
            weight -= item.getWeight();
            inventory.remove(itemName);
            currentRoom.addItem(item);
            System.out.println("You dropped " + itemName);
            return item;
        }
        else {
            System.out.println(itemName + " is not in your inventory. Note upper and lower case writing");
        }
        return null;
    }
    
    public void dropAllItems(Room currentRoom)
    {
       for (Item item : inventory.values()) {
           dropItem(item.getName(), currentRoom);
        }
    }
    
    public void takeAllItems(Room currentRoom)
    {
       for (Item item : currentRoom.getItems().values()) {
           takeItem(item, currentRoom);
        }
    }
}

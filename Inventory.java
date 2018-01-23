import java.util.HashMap;
/**
 * Write a description of class Inventory here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Inventory
{
    // instance variables - replace the example below with your own
    private HashMap<String, Slot> slots;

    /**
     * Constructor for objects of class Inventory
     */
    public Inventory() {
        slots = new HashMap();
    }
    
    public Slot get(String itemName) {
        return slots.get(itemName);
    }
    
    public HashMap<String, Slot> slots() {
        return slots;
    }
    
    public void addAll(HashMap<String, Slot> slots) {
        for (Slot slot : slots.values()) {
            add(slot);
        }
    }
    
    public void add(String name, String description, int weight, boolean eatable, int calories, boolean pickable) {
     slots.put(name, new Slot(name, description, weight, eatable, calories, pickable));
    }
    
    public void add(Slot slot) {
        if (!slots.containsKey(slot.item().getName())) {
            slots.put(slot.item().getName(), slot);
        }
        else {
            int number = slot.number();
            for (int i = 0; i < number; i++)
                slots.get(slot.item().getName()).add();
        }
    }
    
    
    public void removeSlot(String itemName) {
        slots.remove(itemName);
    }
    
    public void remove(String itemName) {
        if (slots.get(itemName).number() > 1) {
            slots.get(itemName).remove();
        }
        else {
            slots.remove(itemName);
        }
    }
    
    public int size() {
        int size = 0;
        for (Slot slot : slots.values())
            size += slot.number();
        return size();
    }
}

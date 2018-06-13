import java.util.HashMap;
/**
 * The Inventory can hold any number of Items
 * 
 * @author Aaron Winter
 * @version 2018.01.30
 */
public class Inventory
{
    // instance variables - replace the example below with your own
    private HashMap<String, Slot> slots;

    /** Constructor for objects of class Inventory
     */
    public Inventory() { slots = new HashMap(); }
    
    /** @param itemName the name of the item, whose slot should be returned
     * @return returns the slot which contains the item
     */
    public Slot get(String itemName) { return slots.get(itemName); }
    
    /** @return returns all slots
     */
    public HashMap<String, Slot> slots() { return slots; }
    
    /** This method allowes you to pass in a HashMap with slots and add all of them to the Inventory's slots
     * @param slots the HashMap with slots to be added into the inventory
     */
    public void addAll(HashMap<String, Slot> slots) {
        for (Slot slot : slots.values()) {
            add(slot);
        }
    }
    
    /** Adds a single Slot to the Inventory
     * @param name the name of the item
     * @param description the description of the item
     * @param weight the weight of the item
     * @param eatable whether the item is eatable or not
     * @param calories the calories of the item
     * @param pickable wether the item is pickable or not
     */
    public void add(String name, String description, int weight, boolean eatable, int calories, boolean pickable) {
        add(new Slot(name, description, weight, eatable, calories, pickable));
    }
    
    /** Takes a Slot and adds it to the Inventory. If an item is already in the inventory, it simply increases
     * the number by one
     * @param slot the slot to be added
     */
    public void add(Slot slot) {
        if (!slots.containsKey(slot.item().getName())) {
            slots.put(slot.item().getName(), slot);
        }
        else {
            int number = slot.number();
            for (int i = 0; i < number; i++) {
                slots.get(slot.item().getName()).add();
            }
        }
    }
    
    /** removes a slot by the name of the item
     * @param itemName name of the item
     */
    public void removeSlot(String itemName) { slots.remove(itemName);  }
    
    /** Removes an Item by the name of the item. If the number of that item is highter than one, instead it
     * decreases the number by one
     * @param itemName the name of the item to be removed
     */
    public void remove(String itemName) {
        if (slots.get(itemName).number() > 1) {
            slots.get(itemName).remove();
        }
        else {
            slots.remove(itemName);
        }
    }
    
    /** @return returns the total number of items. Multiples are count as well
     */
    public int size() {
        int size = 0;
        for (Slot slot : slots.values()) {
            size += slot.number();
        }
        return size;
    }
}

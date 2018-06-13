/**
 * The Slot is used for the players inventory and represents one slot for an item 
 * Also allows to have multiples of an item which was not possible before with just using a HashMap 
 * 
 * @author Aaron Winter
 * @version 2018.01.30
 */

public class Slot {
    
    // the stored item
    private Item item = new Item();
    // keeps track of the number of the stored item
    private int number;

    Slot(Item item) {
        this.item = item;
        number = 1;
    }

    Slot(String name, String description, int weight, boolean eatable, int calories, boolean pickable) {
        this(new Item(name, description, weight, eatable, calories, pickable));
    }
    
    /** @return an item.
     */
    public Item item() { return item; }
    
    /** @return number of the stored items.
     */
    public int number() { return number; }
    
    /** @return String with all the item details.
     */
    public String details() {
        return number + "x " + item.getName() + "(" + item.getWeight() + "g)" + ": " + item.getDescription();
    }
    
    /** increases the amount of an item by one.
     */
    public void add() { number++; }
    
    /** decreases the amount of an item by one.
     */
    public void remove() { number--; }
    
    /** @return the weight of all items carried in this slot
     */
    public int weight() { return (number * item.getWeight()); }
}
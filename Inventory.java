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
    HashMap<String, Slot> slots;

    /**
     * Constructor for objects of class Inventory
     */
    public Inventory()
    {
        slots = new HashMap<>();
    }

    public Item item(String id) {
        return slots.get(id).item();
    }
    
    public int number(String id) {
        return slots.get(id).number();
    }
    
    public Slot slot(String id) {
        return slots.get(id);
    }
    
    public void add(int count,String name,String description,int weight,boolean eatable,int calories,boolean pickable) {
        add(count, new Slot(new Item(name, description, weight, eatable, calories, pickable)));
    }
    
    public void add(Slot slot) {
        if(slots.get(slot.item().getName()) != null) {
            slots.get(slot.item().getName()).add();
        } else {
            slots.put(slot.item().getName(), slot);
        }
    }
    
    public void add(Item item) {
        if(slots.get(item.getName()) != null) {
            slots.get(item.getName()).add();
        } else {
            slots.put(item.getName(), new Slot(item));
        }
    }
    
    public void add(int count, Slot slot) {
        for (int i = 0; i < count; i++)
            add(slot);
    }
    
    public void remove(String itemName) {
        if(slots.get(itemName).number() > 1) {
            slots.get(itemName).remove();
        } else {
            slots.remove(itemName);
        }
    }
    
    public String details() {
    String s = "Items:";
        for(Slot slot: slots.values()){
            s += "\n" + slot.getDetails();
        }
        return s;
    }
}

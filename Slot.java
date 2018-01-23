public class Slot {
    
    private Item item = new Item();
    private int number;
    
    Slot(Item item) {
        this.item = item;
        number = 1;
    }
    
    Slot(String name, String description, int weight, boolean eatable, int calories, boolean pickable) {
        this(new Item(name, description, weight, eatable, calories, pickable));
    }
    
    public Item item() {
        return item;
    }
    
    public int number() {
        return number;
    }
    
    public String details() {
        return number + "x " + item.getName() + "(" + item.getWeight() + "g)" + ": " + item.getDescription();
    }
    
    public void add() {
        number++;
    }
    
    public void remove() {
        number--;      
    }
    
    public int weight() {
        return (number * item.getWeight());
    }
    
}
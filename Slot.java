public class Slot {
    private Item item;
    private int number;

    Slot(Item item) {
        this.item = item;
        number = 1;
    }
    
    public Item item() {
        return item;
    }
    
    public int number() {
        return number;
    }
    
    public String getDetails() {
        return number + "x " + item.getName() + "(" + item.getWeight() + "g)" + ": " + item.getDescription();
    }
    
    public void add() {
        number++;
    }
    
    public void add(int n) {
        number += n;
    }
    
    public void remove() {
        number--;
    }
    
}
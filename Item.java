/**
 * Write a description of class Item here.
 * 
 * @author Thu Ky Vu Hoang, Sebastian Pütz
 * @version 2018.01.21
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String name;
    private int weight;
    private String description;
    private boolean pickable;
    private boolean eatable;

    /**
     * Constructor for objects of class Item
     */
    public Item(String name, String description, int weight)
    {
        this(name, description, weight, false, true);
    }

    public Item(String name, String description, int weight, boolean eatable)
    {
        this(name, description, weight, eatable, true);
    }

    public Item(String name, String description, int weight, boolean eatable, boolean pickable)
    {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.eatable = eatable;
        this.pickable = pickable;
    }
    
    public String getDetails() {
        return name + "(" + weight + "): " + description;
    }
    /**
     * @return name of an item.
     */
    public String getName() {
        return name;
    }

    /**
     * @return description of an item.
     */
    public String getDescription(){
        return description;
    }

    /**
     * @return weight of an item.
     */
    public int getWeight(){
        return weight;
    }
    
    public boolean isEatable() { return eatable; }
    public boolean isPickable() { return pickable; }
}
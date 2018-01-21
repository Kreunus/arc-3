/**
 * Write a description of class Item here.
 * 
 * @author Thu Ky Vu Hoang, Sebastian Pütz
 * @version 2018.01.21
 */
public class Item
{
    // instance variables - replace the example below with your own
    private int weight;
    private String name, description;
    private boolean pickable, eatable;

    /**
     * Default Constructor for objects of class Item
     */
    public Item(String name, String description, int weight)
    {
        this(name, description, weight, false, true);
    }

    /**
     * Constructor for an object of class Item that can be used with the command eat
     */
    public Item(String name, String description, int weight, boolean eatable)
    {
        this(name, description, weight, eatable, true);
    }

    /**
     * Constructor for an object of class Item that can be used with the command eat, take
     */
    public Item(String name, String description, int weight, boolean eatable, boolean pickable)
    {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.eatable = eatable;
        this.pickable = pickable;
    }
    
    /**
     *return the details of an item
     */
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
    
    /**
     * @return that an item eaten by the actor
     */
    public boolean isEatable() { return eatable; }
    
    /**
     * @return that an item can be taken by the actor
     */
    public boolean isPickable() { return pickable; }
}

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
    private String description;
    
    private int weight;
    private int calories;
    
    private boolean pickable;
    private boolean eatable;

    /**
     * Constructor for objects of class Item
     */
    public Item(String name, String description, int weight)
    {
        this(name, description, weight, false, 0, true);
    }

    public Item(String name, String description, int weight, boolean eatable, int calories)
    {
        this(name, description, weight, eatable, 0, true);
    }

    public Item(String name, String description, int weight, boolean eatable, int calories, boolean pickable)
    {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.eatable = eatable;
        this.pickable = pickable;
        if (eatable)
            this.calories = calories;
        else
            this.calories = 0;
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
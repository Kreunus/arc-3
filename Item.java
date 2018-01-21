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
    
    /**
     * Constructor for objects of class Item
     */
    public Item(String name, String description, int weight)
    {
        // initialise instance variables
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    /**
     * @return name of an item.
     */
    public String getName(){
        return name;
    }
    
    /**
     * @return description of an item.
     */
    public String getItemDescription(){
        return description;
    }
    
    /**
     * @return weight of an item.
     */
    public int getWeight(){
        return weight;
    }
}
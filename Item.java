/**
 * Write a description of class Item here.
 *
 * @author Thu Ky Vu Hoang
 * @version (a version number or a date)
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
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    
    public String getName(){
        return name;
    }
    
    public String getItemDescription(){
        return description;
    }

    public int getWeight(){
        return weight;
    }
}
/**
 * Items are objects which can be found in rooms and can be carried by the player. 
 * These can be things like food, tools, clothes, computers, furniture and so on and so forth.
 * 
 * @author Thu Ky Vu Hoang, Sebastian Pütz, Aaron Winter
 * @version 2018.01.30
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String name;
    private String description;
    
    private int weight;
    // determines how much calories an item gives to a player, if it is eatable
    private int calories;
    
    // Whether an object can be picked up by the player or not
    private boolean pickable;
    // whether an object can be eaten by the player or not
    private boolean eatable;
    
    public Item() {}
    
    public Item(String name, String description, int weight) {
        this(name, description, weight, false, 0, true);
    }

    public Item(String name, String description, int weight, boolean eatable, int calories) {
        this(name, description, weight, eatable, 0, true);
    }

    /** Constructor for an object of class Item that can be used with the command eat, take
     */
    public Item(String name, String description, int weight, boolean eatable, int calories, boolean pickable) {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.eatable = eatable;
        this.pickable = pickable;
        if (eatable) {
            this.calories = calories;
        }
        else {
            this.calories = 0;
        }
    }
    
    /** @return name of an item.
     */
    public String getName() { return name; }

    /** @return description of an item.
     */
    public String getDescription(){ return description; }

    /** @return weight of an item.
     */
    public int getWeight() { return weight; }
    
    /** @return calories of an item.
     */
    public int getCalories() {  return calories; }

    /**  @return that an item eaten by the actor
     */
    public boolean isEatable() { return eatable; }
    
    /** @return that an item can be taken by the actor
     */
    public boolean isPickable() { return pickable; }
}

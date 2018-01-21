import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kolling, David J. Barnes, Aaron Winter, Thu Ky Vu Hoang
 * @version 2018.01.21
 */
public class Room 
{
    private String name, id, description;
    //added private to HashMap and added ArrayList (Thu Ky Vu Hoang)
    private HashMap <String, Room> exits;
    private HashMap<String, Item> items;
    private HashMap<String, Character> npcs;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String name, String id, String description) 
    {
        exits = new HashMap<>();
        npcs = new HashMap<>();
        //added ArrayList (Thu Ky Vu Hoang)
        items = new HashMap<>();
        this.name = name;
        this.id = id;
        this.description = description;
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param room
     */
    public void setExit(Room room) {
        exits.put(room.getId(), room);
    }
    
    /**
     * @param name
     * @retung the collection of exits
     */
    public Room getExit(String name) {
        return exits.get(name);
    }
    
    /**
     * @return The description of the room.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return The name of the room.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The id of the room.
     */
    public String getId() {
        return id;
    }
    
    /**
     * @return a String with all exits of a room.
     */
    public String getExitStrings() {
        String s = "Exits:";
        for (String exit : exits.keySet()) {
            s += "\n" + exits.get(exit).getName() + " (" + exit + ")";
        }
        return s;
    }
    
    /**
     * Adds an Item to the Room
     * @param item an Item that gets added to the room
     */
    public void addItem(Item item) {
        items.put(item.getName(), item);
    }
    
    /**
     * A method that removes a item from its collection of a room.
     * @param item
     */
    public void removeItem(String name) {
        items.remove(name);
    }
    
    /**
     * @return a String that includes all available items in a room.
     * @author Thu Ky Vu Hoang
     */
    public String getItemStrings(){
        String s = "Items:";
        for(Item item: items.values()){
            s += "\n" + item.getDetails();
        }
        return s;
    }
    
    /**
     * @return Returns a detailed description of the room and it's content
     */
    public String getLongDescription() {
        return getCharacterStrings() + "\n" + getItemStrings() + "\n" + getExitStrings();
    }

    /**
     * @param itemName
     * @return values of an item by the key itemName
     */
    public Item getItem(String itemName) {
        return items.get(itemName);
    }

    /**
     * @return a collection of items
     */
    public HashMap<String, Item> getItems() { return items; }
    
    /**
     * Mehtod that creates a new NPC in a room.
     * @param fName, lName, respone
     */ 
    public void newNpc(String fName, String lName, String response) {
        Character c = new Character(fName, lName, response);
        npcs.put(fName, c);
    }
    
    /**
     * @param name The name of the Character that should respond
     * @return a String thats includes all reponses from NPCs.
     */
    public String getResponseFromCharacter(String name) {
        if(npcs.get(name) == null)
            return "There is no " + name;
        return name + ": \"" + npcs.get(name).getResponse() + "\"";
    }

    /**
     * @return a String of all NPSc that are available in a room.
     */
    public String getCharacterStrings() {
        String s = "Characters:";
        for (Character c : npcs.values()) {
            s += "\n" + c.getName();
        }
        return s;
    }

}

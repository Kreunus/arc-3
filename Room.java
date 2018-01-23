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
    private HashMap<String, Room> exits;
    private HashMap<String, Actor> npcs;
    private Inventory items;
    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String name, String id, String description) {
        exits = new HashMap<>();
        npcs = new HashMap<>();
        items = new Inventory();
        this.name = name;
        this.id = id;
        this.description = description;
    }

    /** Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param room
     */
    public void setExit(Room room) { exits.put(room.getId(), room); }

    /** @param name
     * @retung the collection of exits
     */
    public Room getExit(String name) { return exits.get(name); }
    
    public HashMap<String, Room> getExits() { return exits; }

    /** @return The description of the room.
     */
    public String getDescription() { return description; }

    /** @return The name of the room.
     */
    public String getName() { return name; }

    public boolean hasExit(String id) {
        for (Room room : exits.values()) {
            if (room.getId().equals(id))
                return true;
        }
        return false;
    }

    /** @return The id of the room.
     */
    public String getId() { return id;  }

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
    public void addItem(int count, String name, String description, int weight,boolean pickable, int calories, boolean eatable) {
        for (int i = 0; i < count ; i++) {
            items.add(name, description, weight, eatable, calories, pickable);
        }
    }

    /**
     * A method that removes a item from its collection of a room.
     * @param item
     */
    public void removeItem(String itemName) { items.remove(itemName); }
    
    /**
     * @return the String s generated a list of all items in the bag
     */
    public String getItemStrings(){
        String s = "Items:";
        for (String itemName : items.slots().keySet()) {
            s += "\n" + items.slots().get(itemName).details();
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
     * @return a collection of items
     */
    public Inventory getItems() { return items; }

    /**
     * Mehtod that creates a new NPC in a room.
     * @param fName, lName, respone
     */ 
    public void addNpc(Actor actor) {
        actor.setCurrentRoom(this);
        npcs.put(actor.getFirstName(), actor);
    }
    
    public void removeNpc(String name) {
        npcs.remove(name);
    }

    /**
     * @param name The name of the Character that should respond
     * @return a String thats includes all reponses from NPCs.
     */
    public String getResponseFromCharacter(String name) {
        if(npcs.get(name) == null) {
            return "There is no " + name;
        }
        return name + ": \"" + npcs.get(name).getResponse() + "\"";
    }

    /**
     * @return a String of all NPSc that are available in a room.
     */
    public String getCharacterStrings() {
        String s = "Characters:";
        for (Actor c : npcs.values()) {
            s += "\n" + c.getName();
        }
        return s;
    }

}

import java.util.HashMap;
import java.util.Random;
/**
 * An Actor is a character that can appear in the game. Currently used for npcs.
 * 
 * @author Aaron Winter 
 * @version 2018.01.30
 */
public class Actor
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    protected String firstName;
    protected String lastName;
    protected String response;
    
    protected Room currentRoom;

    /** Constructor of an object actor
     */
    public Actor(String firstName, String lastName, String response) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.response = response;
    }

    /** @return first name of the actor
     */
    public String getFirstName() { return firstName; }

    /** @return last name of the actor
     */
    public String getLastName() { return lastName; }
    
    /** @return the complete name of the actor
     */
    public String getName() { return firstName + " " + lastName; }
    
    public String getResponse() { return response; }
    
    public Room getCurrentRoom() { return currentRoom; }
    
    public void setCurrentRoom(Room nextRoom) { currentRoom = nextRoom; }
    
    /** Lets an Actor move from its current room randomly to a neighbour room.
     */
    public void moveToRandomExit() {
        Random rand = new Random();
        HashMap<String, Room> exits = currentRoom.getExits();
        int number = rand.nextInt(exits.size());
        Object[] exitRooms = exits.values().toArray();
        Room exitRoom = (Room) exitRooms[number];
        currentRoom.removeNpc(firstName);
        exitRoom.addNpc(this);
        System.out.println("[DEBUG]: "+ firstName + " moved to " + currentRoom.getName());
    }
}

import java.util.Collection;
import java.util.HashMap;
import java.util.Random;
/**
 * Beschreiben Sie hier die Klasse Actor.
 * 
 * @author Aaron Winter 
 * @version 2018.01.21
 */
public class Actor
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    protected String firstName;
    protected String lastName;
    protected String response;
    
    private Room currentRoom;

    /**
     * Constructor of an object actor
     */
    public Actor(String firstName, String lastName, String response)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.response = response;
    }

    /**
     * @return first name of the actor
     */
    public String getFirstName() { return firstName; }

    /**
     * @return last name of the actor
     */
    public String getLastName() { return lastName; }
    
    /**
     * @return complete name of the actor
     */
    public String getName() { return firstName + " " + lastName; }
    
    public String getResponse() { return response; }
    
    public Room getCurrentRoom() { return currentRoom; }
    
    public void setCurrentRoom(Room nextRoom) { currentRoom = nextRoom; }
    
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

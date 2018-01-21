
/**
 * The class character manages npcs in the game
 *
 * @author Aaron Winter, Sebastian Pütz
 * @version 2018/01/21
 */
public class Character
{
    // instance variables - replace the example below with your own
    String firstName, lastName, response;

    /**
     * Constructor for objects of class Character
     */
    public Character(String fName, String lName, String response)
    {
        firstName = fName;
        lastName = lName;
        this.response = response;
    }
    
    /**
     * return the reposnse of a npc
     */
    public String getResponse() { return response; }
    
    /**
     * @return the first name of a npc
     */
    public String getFirstName() { return firstName; }
    
    /**
     * @return the last name of a npc
     */    
    public String getLastName() { return lastName; }
   
    /**
     * @return the complete name of a npc
     */
    public String getName() { return firstName + " " + lastName; }
}

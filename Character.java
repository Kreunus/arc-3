
/**
 * The class character manages npcs in the game
 *
 * @author Aaron Winter, Sebastian Pütz
 * @version 2018/01/21
 */
public class Character extends Actor
{
    // instance variables - replace the example below with your own
    private String response;

    /**
     * Constructor for objects of class Character
     */
    public Character(String fName, String lName, String response)
    {
        super(fName, lName);
        this.response = response;
    }
    
    /**
     * @return the reposnse of a npc
     */
    public String getResponse() { return response; }
    
    public boolean step() { return false; };
}


/**
 * Write a description of class Character here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Character
{
    // instance variables - replace the example below with your own
    String firstName;
    String lastName;
    
    String response;

    /**
     * Constructor for objects of class Character
     */
    public Character(String fName, String lName, String response)
    {
        firstName = fName;
        lastName = lName;
        this.response = response;
    }
    
    public String getResponse() { return response; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getName() { return firstName + " " + lastName; }
}

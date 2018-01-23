import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
/**
 * Beschreiben Sie hier die Klasse Actor.
 * 
 * @author Aaron Winter 
 * @version 2018.01.21
 */
public abstract class Actor
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private String firstName;
    private String lastName;

    /**
     * Constructor of an object actor
     */
    public Actor(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public abstract boolean step();

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

    /**
     * @return the String s generated below
     */
    public String getDetails() {
        String s = "Details:";
        s += "\n" + getName();
        return s;
    }
}

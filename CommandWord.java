/**
 * Enumeration class CommandWord - write a description of the enum class here
 *
 * @author Aaron Winter, Sebastian Pütz
 * @version v1
 */
public enum CommandWord
{
    GO("go"), 
    QUIT("quit"),
    LOOK("look"),
    SEARCH("search"),
    EAT("eat"),
    TAKE("take"),
    DROP("drop"),
    ALICE("alice"),
    ASK("ask"),
    //added back (Thu Ky Vu Hoang)
    BACK("back"),
    HELP("help");
    
    private String commandWord;
    
    CommandWord(String commandWord) { 
        this.commandWord = commandWord; 
    }
    
    /**
     * @return object of commandWord
     */
    public String getCommandWord() {
        return commandWord;
    }
    
    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public static boolean isCommand(String commandWord) {
        for (CommandWord command : CommandWord.values()) {
            if (command.commandWord.equalsIgnoreCase(commandWord)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * @return returns the CommandWord by String. 
     * Returns null when no CommandWord matches the String
     */
    public static CommandWord fromString(String commandWord) {
        for (CommandWord command : CommandWord.values()) {
            if (command.commandWord.equalsIgnoreCase(commandWord)) {
                return command;
            }
        }
        return null;
    }
    
    /**
     * @return String s that lists all commands.
     */
    public static String getCommandWords() {
        String s = "\t";
        for (CommandWord command : CommandWord.values()) {
            s = s + " " + command.commandWord;
        }
        return s;
    }
}
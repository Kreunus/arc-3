import java.util.Stack;
import java.util.ArrayList;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kolling, David J. Barnes, Aaron Winter, Sebastian Pütz, Thu Ky Vu Hoang
 * @version 2008.03.30
 */

public class Game 
{
    private Player player;
    private Parser parser;
    // private Room currentRoom;

    boolean wantToQuit;

    //saves the current room before going to the next (Thu Ky Vu Hoang)
    private Stack<Room> previousRooms;
    private ArrayList<Actor> npcs;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        player = new Player("Richard", "Clarke");
        previousRooms = new Stack<Room>();
        npcs = new ArrayList<>();
        parser = new Parser();
        createRooms();
        wantToQuit = false;
    }

    /** Main play routine.  Loops until end of play.
     */
    public void play() {            
        printWelcome();
        printLocation();

        boolean finished = false;
        while (! finished) {
            finished = processCommand();
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /** Given a command, process (that is: execute) the command.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand()  {
        if(parser.getNewCommand().isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        switch(parser.getCommandWord()) {
            case ALICE: alice(); break;
            case ASK: ask(); break;
            case BACK: goBack(); break;   
            case DROP: drop(); break;
            case EAT: eat(); break; 
            case GO: goRoom(); break;
            case LOOK: look(); break;
            case HELP: printHelp(); break;
            case QUIT: wantToQuit = quit(); break;
            case STATS: stats(); break;
            case TAKE: take(); break;  
        }
        return wantToQuit;
    }

    /** Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom() 
    {   
        if(!parser.getCommand().hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = parser.getCommand().getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            // if the nextRoom does not have the currentRoom as Exit, the prevRooms get cleared so there is no going back
            if (!nextRoom.hasExit(player.getCurrentRoom().getId())) {
                previousRooms.clear();
                System.out.println("You used a trap door. You can't go back to " + player.getCurrentRoom().getName());
            }
            else {
                previousRooms.push(player.getCurrentRoom());
            }
            player.setCurrentRoom(nextRoom);
            step();
            printLocation();
        }
    }

    private void step() {
        for (Actor actor : npcs) {
            actor.moveToRandomExit();
        }
        wantToQuit = player.step();
    }

    /** The player looks around. For now nothing happens...
     */
    private void look() {
        printLocation();
    }

    /** A method that manages the logic of the command eat and the second word
     */
    private void eat() {
        if(!parser.getCommand().hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Eat what?");
            return;
        }
        else {
            player.eatItem(parser.getCommand().getSecondWord());
        }
    }

    private void ask() {
        if(!parser.getCommand().hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Ask who?");
            return;
        }
        else {
            System.out.println(player.getCurrentRoom().getResponseFromCharacter(parser.getCommand().getSecondWord()));
        }
    }

    private void alice() {
        if(!parser.getCommand().hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("ALICE: How can I help you?");
            return;
        }
        else {
            System.out.println("ALICE: I'm afraid I can't help you with that.");
        }
    }

    //goBack method (Thu Ky Vu Hoang)
    /** logic of the command back
     */
    private void goBack(){
        if(parser.getCommand().hasSecondWord()) {
            System.out.println("What ?");
            return;
        }
        if (previousRooms.empty()){
            System.out.println("You can't go back !");
        }
        else {
            step();
            player.setCurrentRoom(previousRooms.pop());
            printLocation();
        }
    }

    /** Print out iformation of the player and its environment/back.
     */
    private void stats() { System.out.println(player.getDetails()); }

    /** logic of the command take and the second word
     */
    private void take() {
        if(!parser.getCommand().hasSecondWord()) {
            // if there is no second word...
            System.out.println("What item?");
            return;
        }
        else {
            if (parser.getCommand().getSecondWord().equals("all")) {
                player.takeAllItems();
            } 
            else  {
                player.takeItem(parser.getCommand().getSecondWord());
            }

        }
    }

    private void drop() {
        if(!parser.getCommand().hasSecondWord()) {
            // if there is no second word...
            System.out.println("What item?");
            return;
        }
        else {
            if (parser.getCommand().getSecondWord().equals("all")) {
                player.dropAllItems();
            } 
            else  {
                player.dropItem(parser.getCommand().getSecondWord());
            }
        }
    }

    /**  "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit()  {
        if(parser.getCommand().hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    /** Print out the opening message for the player.
     */
    private void printWelcome()  {
        System.out.println();
        System.out.println("The Year is 2165. The earth is worn down by mankinf and we are in need to to look for a new planet.");
        System.out.println("Therefore, hu,ams semt eight big space ships, Arc-1 to 8 out to planets which are considered habitable for humans.");
        System.out.println("Every Arc contains a number of animals and plants form the earth to bring them to the new planets.");
        System.out.println("70 Years have already passed since the Arcs started their mission to reach the other planets.");
        System.out.println("After a few years, the connection to the others was lost and they were completele on themselves.");
        System.out.println("\nYou are on Arc-3 - \"Earth\".");
        System.out.println("You belong to the maintenance team and your mission is to keep the space ship intact until it arrives its destination.");
        System.out.println("(type 'help' if you need help.)");
        System.out.println();
    }

    /** Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp()  {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.getCommandWords());
    }

    /** Method that is used to pringt out the current location of.
     */
    private void printLocation() {
        System.out.println("You are " + player.getCurrentRoom().getDescription());
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    /** Create all the rooms and link their exits together.
     */
    private void createRooms() {
        // Creating characters
        Actor kelly = new Actor("Kelly", "Smith", "These old computers are killing me.");
        npcs.add(kelly);
        Actor oprah = new Actor("Oprah", "Dontos", "Hey " + player.getFirstName() + ", how are you?");
        npcs.add(oprah);
        Actor noah = new Actor("Noah", "Windbreaker", "What are you doing in my office!");
        npcs.add(noah);

        // Creating rooms and adding items/characters to it
        Room navigationRoom = new Room("navigation room", "nav", "in the navigation room.");

        Room computationRoom = new Room("computing room", "com", "in the computing room.");
        computationRoom.addItem(4, "computer", "a computer that computes things", 12000, true, 0, false);
        computationRoom.addNpc(kelly);

        Room laboratory = new Room("laboratory", "lab", "in the laboratory.");
        laboratory.addItem(1, "cookie", "Increases maximum weight", 50, true, 200, true);
        laboratory.addItem(1, "toolkit", "A basic toolkit for repairing things", 100, true, 0, false);
        laboratory.addItem(1, "recycler", "A big Mashine for recycling stuff", 500000, true, 0, false);

        Room armory = new Room("armory", "arm", "in the armory.");

        Room meetingRoom = new Room("meeting room", "meet", "in the meeting room.");

        Room captainCell = new Room("Captain Cell", "cc", "in the room of the captain.");

        Room aiCore = new Room("core room", "core", "in the artificial intelligence core room.");

        Room engineRoom1 = new Room("enginge room 1", "eng1", "in the engine room 1.");
        engineRoom1.addItem(1, "engine", "These engine is massive!", 3000000, false, 0, false);

        Room engineRoom2 = new Room("engine room 2", "eng2", "in the engine room 2.");
        engineRoom2.addItem(1, "engine", "These engine is massive!", 3000000, false, 0, false);

        Room cryoRoom1 = new Room("cryo room 1", "cryo1", "in the cryo room 1.");
        cryoRoom1.addItem(26, "capsules", "a frozen human, incredible!", 120000, false, 0, false);

        Room cryoRoom2 = new Room("cryo room 2", "cryo2", "in the cryo room 2.");
        cryoRoom2.addItem(26, "capsules", "a frozen human, incredible!", 120000, false, 0, false);

        Room livingRoom1 = new Room("living cell 1", "cell1", "in the living cell 1.");
        livingRoom1.addItem(2, "banana", "It's a banana. Bananas are cool!", 100, true, 200, true);

        Room livingRoom2 = new Room("living cell 2", "cell2", "in your living cell 2.");
        livingRoom2.addItem(1, "apple", "an apple a day keeps the doctor away", 100, true, 200, true);
        livingRoom2.addItem(2, "clothes", "Normal Clothes no effect", 2000, true, 0, false);

        Room livingRoom3 = new Room("living cell 3", "cell3", "in the living cell 3.");
        livingRoom2.addNpc(oprah);
        livingRoom3.addItem(3, "orange", "So orange...", 100, true, 200, true);

        Room livingRoom4 = new Room("living cell 4", "cell4", "in the living cell 4.");
        livingRoom4.addItem(1, "clothes", "Normal Clothes no effect", 2000, true, 0, false);

        Room animalRoom1 = new Room("animal room 1", "anim1", "in the animal room 1.");
        Room animalRoom2 = new Room("animal room 2", "anim2", "in the animal room 2.");

        Room plantRoom1 = new Room("plant room 1", "plant1", "in the plant room 1.");
        plantRoom1.addItem(6, "banana", "It's a banana. Bananas are cool!", 100, true, 200, true);
        plantRoom1.addItem(3, "orange", "So orange...", 100, true, 200, true);

        Room plantRoom2 = new Room("plant room 2", "plant2", "in the plant room 2.");
        plantRoom2.addItem(3, "apple", "an apple a day keeps the doctor away", 100, true, 200, true);

        Room hall1 = new Room("sector 2", "sec2", "in the communication unit.");
        Room hall2 = new Room("sector 3", "sec3", "in the technical unit.");
        Room hall3 = new Room("sector 4", "sec4", "in the crew living unit.");
        Room hall4 = new Room("sector 5", "sec5", "in the cryo chamber unit.");
        Room hall5 = new Room("hall (sector 6)", "sec6", "in the animal and plant unit.");

        captainCell.addNpc(noah);

        //sets exits for rooms
        navigationRoom.setExit(computationRoom);
        computationRoom.setExit(navigationRoom);
        computationRoom.setExit(hall3);

        // TRAPDOOR! Trapdoors are automatically recognized if the nextRoom doesn't have the currentRoom as Exit.
        computationRoom.setExit(hall5);

        hall3.setExit(computationRoom);
        hall3.setExit(livingRoom1);
        hall3.setExit(livingRoom2);
        hall3.setExit(livingRoom3);
        hall3.setExit(livingRoom4);
        hall3.setExit(hall1);
        livingRoom1.setExit(hall3);
        livingRoom2.setExit(hall3);
        livingRoom3.setExit(hall3);
        livingRoom4.setExit(hall3);

        hall1.setExit(hall3);
        hall1.setExit(hall2);
        hall1.setExit(laboratory);
        hall1.setExit(armory);
        hall1.setExit(meetingRoom);
        armory.setExit(hall1);
        laboratory.setExit(hall1);
        meetingRoom.setExit(hall1);
        meetingRoom.setExit(captainCell);
        captainCell.setExit(meetingRoom);

        hall2.setExit(hall1);
        hall2.setExit(hall4);
        hall2.setExit(aiCore);
        hall2.setExit(engineRoom1);
        hall2.setExit(engineRoom2);
        aiCore.setExit(hall2);
        engineRoom1.setExit(hall2);
        engineRoom2.setExit(hall2);

        hall4.setExit(hall2);
        hall4.setExit(hall5);
        hall4.setExit(cryoRoom1);
        hall4.setExit(cryoRoom2);
        cryoRoom1.setExit(hall4);
        cryoRoom2.setExit(hall4);

        hall5.setExit(hall4);
        hall5.setExit(animalRoom1);
        hall5.setExit(plantRoom1);
        animalRoom1.setExit(hall5);
        animalRoom1.setExit(animalRoom2);
        animalRoom2.setExit(animalRoom1);
        plantRoom1.setExit(hall5);
        plantRoom1.setExit(plantRoom2);
        plantRoom2.setExit(plantRoom1);

        player.setCurrentRoom(livingRoom2);
    }
}

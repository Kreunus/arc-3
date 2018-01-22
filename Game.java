import java.util.Stack;

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
    private Actor player;
    private Parser parser;
    private Room currentRoom;

    boolean wantToQuit;

    //saves the current room before going to the next (Thu Ky Vu Hoang)
    private Stack<Room> previousRooms;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        player = new Actor("Richard", "Clarke");
        createRooms();
        previousRooms = new Stack<Room>();
        parser = new Parser();
        wantToQuit = false;
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() {            
        printWelcome();
        printLocation();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            finished = processCommand();
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {
        //initialize rooms
        Room navigationRoom = new Room("navigation room", "nav", "in the navigation room.");
        Room computationRoom = new Room("computing room", "com", "in the computing room.");

        Room laboratory = new Room("laboratory", "lab", "in the laboratory.");
        Room armory = new Room("armory", "arm", "in the armory.");
        Room meetingRoom = new Room("meeting room", "meet", "in the meeting room.");
        Room captainCell = new Room("Captain Cell", "cc", "in the room of the captain.");

        Room aiCore = new Room("core room", "core", "in the artificial intelligence core room.");
        Room engineRoom1 = new Room("enginge room 1", "eng1", "in the engine room 1.");
        Room engineRoom2 = new Room("engine room 2", "eng2", "in the engine room 2.");

        Room cryoRoom1 = new Room("cryo room 1", "cryo1", "in the cryo room 1.");
        Room cryoRoom2 = new Room("cryo room 2", "cryo2", "in the cryo room 2.");

        Room livingRoom1 = new Room("living cell 1", "cell1", "in the living cell 1.");
        Room livingRoom2 = new Room("living cell 2", "cell2", "in your living cell 2.");
        Room livingRoom3 = new Room("living cell 3", "cell3", "in the living cell 3.");
        Room livingRoom4 = new Room("living cell 4", "cell4", "in the living cell 4.");

        Room animalRoom1 = new Room("animal room 1", "anim1", "in the animal room 1.");
        Room animalRoom2 = new Room("animal room 2", "anim2", "in the animal room 2.");
        Room plantRoom1 = new Room("plant room 1", "plant1", "in the plant room 1.");
        Room plantRoom2 = new Room("plant room 2", "plant2", "in the plant room 2.");

        Room hall1 = new Room("sector 2", "sec2", "in the communication unit.");
        Room hall2 = new Room("sector 3", "sec3", "in the technical unit.");
        Room hall3 = new Room("sector 4", "sec4", "in the crew living unit.");
        Room hall4 = new Room("sector 5", "sec5", "in the cryo chamber unit.");
        Room hall5 = new Room("hall (sector 6)", "sec6", "in the animal and plant unit.");
        
        laboratory.addItem(1, "cookie", "Increases maximum weight", 50, true, 200, true);
        laboratory.addItem(5, "banana", "It's a banana. Bananas are cool!", 100, true, 200, true);
        laboratory.addItem(1, "tool", "A Tool", 100, true);
        laboratory.addItem(1, "recycler", "A big Mashine for recycling stuff", 500000, true);

        livingRoom1.addItem(2, "banana", "It's a banana. Bananas are cool!", 100, true, 200, true);
        livingRoom2.addItem(2, "clothes", "Normal Clothes no effect", 2000, true);
        livingRoom3.addItem(3, "orange", "So orange...", 100, true, 200, true);
        livingRoom4.addItem(1, "clothes", "Normal Clothes no effect", 2000, true);

        livingRoom3.newNpc("Oprah", "Winfrey", "Hey " + player.getFirstName() + ", how are you?");
        captainCell.newNpc("Noah", "Windbreaker", "What are you doing in my office!");

        //sets exits for rooms
        navigationRoom.setExit(computationRoom);
        computationRoom.setExit(navigationRoom);
        computationRoom.setExit(hall3);

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

        currentRoom = livingRoom2;
    }

    /**
     * Print out the opening message for the player.
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

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand() 
    {
        if(parser.getNewCommand().isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        switch(parser.getCommandWord()) {
            
            //alphabetical order
            case ALICE: alice(); break;
            case ASK: ask(); break;
            case BACK: goBack(); break;
            case BAG: bag(); break;   
            case DROP: drop(); break;
            case EAT: eat(); break; 
            case GO: goRoom(); break;
            case HELP: printHelp(); break;
            case LOOK: look(); break;
            case QUIT: wantToQuit = quit(); break;
            case TAKE: take(); break;
                       
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.getCommandWords());
    }
    
    /**
     * Method that is used to pringt out the current location of.
     */
    private void printLocation() {
        System.out.println("You are " + currentRoom.getDescription());
        System.out.println(currentRoom.getLongDescription());

    }

    /** 
     * Try to go to one direction. If there is an exit, enter
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
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            // save current position (Thu Ky Vu Hoang)
            previousRooms.push(currentRoom);
            currentRoom = nextRoom;
            step();
            printLocation();
        }
    }
    
    private void step() {
        wantToQuit = player.step(10);
    }
    
    private void step(int calories) {
        wantToQuit = player.step(calories);
    }
    
    /**
     * The player looks around. For now nothing happens...
     */
    private void look() {
        step(2);
        printLocation();
    }
    
    /**
     * A method that manages the logic of the command eat and the second word
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
        step(5);
        if(!parser.getCommand().hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Ask who?");
            return;
        }
        else {
            System.out.println(currentRoom.getResponseFromCharacter(parser.getCommand().getSecondWord()));
        }
    }

    private void alice() {
        step(5);
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
    /**
     * logic of the command back
     */
    private void goBack(){
        if(parser.getCommand().hasSecondWord()) {
            System.out.println("What ?");
            return;
        }
        if (previousRooms.empty()){
            System.out.println("You can't go back !");
        }
        else{
            step();
            currentRoom = previousRooms.pop();
            printLocation();
        }
    }

    
    /*
     * Print out iformation of the player and its environment/back.
     */
    private void bag() {
        step(5);
        System.out.println(player.getDetails());
    }

    /**
     * logic of the command take and the second word
     */
    private void take() {
        if(!parser.getCommand().hasSecondWord()) {
            // if there is no second word...
            System.out.println("What item?");
            step(2);
            return;
        }
        else {
            /*
            if(parser.getCommand().getSecondWord().toLowerCase().equals("all"))
                player.takeAllItems(currentRoom.getItems());
            else */
                step(5);
                player.takeItem(parser.getCommand().getSecondWord(), currentRoom.getItems());
        }
    }
    
        private void drop() {
        if(!parser.getCommand().hasSecondWord()) {
            // if there is no second word...
            System.out.println("What item?");
            step(2);
            return;
        }
        else {
            /*if(parser.getCommand().getSecondWord().equals("all"))
                player.dropAllItems(currentRoom.getItems());
            else */
                step(5);
                player.dropItem(parser.getCommand().getSecondWord(), currentRoom.getItems());
        }
    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit() 
    {
        if(parser.getCommand().hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}

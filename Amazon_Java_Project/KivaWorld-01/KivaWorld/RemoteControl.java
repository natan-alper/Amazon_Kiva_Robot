import edu.duke.FileResource;

/**
 * This is the class that controls Kiva's actions. Implement the <code>run()</code>
 * method to deliver the pod and avoid the obstacles.
 * 
 * @see Kiva
 * @see KivaCommand
 */

public class RemoteControl {
    KeyboardResource keyboardResource;

    public RemoteControl() {
        keyboardResource = new KeyboardResource();
    }
    
    /**
     * Converts the string of characters (representing the directionKeys)
     * into an array of KivaCommands.
     * @param userCommands The string the user input. 
     * This string represents the list of directionKeys that
     * will be used to find the correlated KivaCommands and added to the 
     * KivaCommands array.
     * @return An array of KivaCommands that will be iterated over in the
     * <code>run()</code> method.
     */
    public KivaCommand[] convertToKivaCommands(String userCommands){
        KivaCommand[] commandsToExecute = new KivaCommand[userCommands.length()];
        KivaCommand[] commandChoices = KivaCommand.values();
        int i = 0;
        
        //Iterate over each letter of the string
        for (char directionKey : userCommands.toCharArray()){
            /*Iterate over each KivaCommand to find which directionKey 
            the letter matches.
            Then, add that command to the commandsToExecute array.
            */
            for (KivaCommand command : commandChoices){
                if (directionKey == command.getDirectionKey()){
                    commandsToExecute[i] = command;
                    break;
                }
            }
            
            if (commandsToExecute[i] == null){
                throw new IllegalArgumentException("Character \'"+directionKey+
                "\' does not correspond to a command!");
            }
            i++;
        }
        
        return commandsToExecute;
    }
    
    /**
     * The controller that directs Kiva's activity. 
     * Prompts the user for the floor map
     * to load, displays the map, and asks the user for 
     * the commands for Kiva to execute.
     * Calls the <code>convertToKivaCommands(String userCommands)</code> 
     * method to convert the user input into an array of KivaCommands and
     * executes them.
     */
    public void run() {
        System.out.println("Please select a map file.");
        FileResource fileResource = new FileResource();
        String inputMap = fileResource.asString();
        FloorMap floorMap = new FloorMap(inputMap);
        //Create kiva using floorMap
        Kiva kiva = new Kiva(floorMap);
        //Info for the user
        System.out.println("Diagnostic information:");
        System.out.println("Current location of the Kiva is: "
                        +kiva.getCurrentLocation());
        System.out.println("Current direction the Kiva is facing: "
                        +kiva.getDirectionFacing());
        
        System.out.println("Floor map file chosen: "+floorMap.toString());
        //Request user instructions
        System.out.println("Please enter the directions for the Kiva Robot to take.");
        String directions = keyboardResource.getLine();
        System.out.println("Directions that you typed in: " + directions);
        //Convert user input into array of KivaCommands
        KivaCommand[] commands = convertToKivaCommands(directions);
        
        //Execute array of commands
        for (KivaCommand command : commands){
            kiva.move(command);
        }
        
        if (kiva.isSuccessfullyDropped()){
            System.out.println("Successfully picked up the pod and "+
                "dropped it off. Thank you!");
        }
        else {
            System.out.println("I\'m sorry. The Kiva Robot did not pick up the pod"+
                " and then drop it off in the right place.");
        }
    }
}

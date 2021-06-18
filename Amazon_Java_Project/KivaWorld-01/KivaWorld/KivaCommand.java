
/**
 * The KivaCommand enum contains the list of commands that a Kiva object 
 * can perform.
 * Each command is associated with a char-valued direction key.
 * 
 * @see Kiva
 * @author Natan Alper 
 * @version 4/25/2021
 */
public enum KivaCommand {
    /**
     * Moves the Kiva object forward one space on the map.
     * Direction Key: 'F'.
     */
    FORWARD('F'),
    /**
     * Rotates Kiva object to the left.
     * Direction Key: 'L'.
     */
    TURN_LEFT('L'),
    /**
     * Rotates Kiva object to the right.
     * Direction Key: 'R'.
     */
    TURN_RIGHT('R'),
    /**
     * Takes/picks up pod (must not already be holding a pod and 
     * be at the pod location).
     * Direction Key: 'T'.
     */
    TAKE('T'),
    /**
     * Drops pod (must already be holding a pod and be at 
     * drop-off location in order to drop).
     * Direction Key: 'D'.
     */
    DROP('D');
    
    char directionKey;
    
    private KivaCommand(char directionKey){
        this.directionKey = directionKey;
    }
    
    /**
     * Returns the unique char associated with its KivaCommand.
     * @return The direction key (char) of the given KivaCommand.
     * Direction Keys: 'F','L','R','T','D'.
     */
    public char getDirectionKey(){
        return this.directionKey;
    }
}

import java.util.Arrays;

/**
 * Miscellaneous tests for RemoteControl class.
 * 
 * @see RemoteControl
 * @author Natan Alper
 * @version 2021/5/2
 */
public class RemoteControlTest {
    
    /**
     * Tests if convertToKivaCommands is able to convert a string of character
     * commands (string of direction keys) into KivaCommands.
     */
    public void testConvertToKivaCommands(){
        RemoteControl rc = new RemoteControl();
        
        System.out.println("TEST 1");
        String input = "FFFTRF";
        System.out.println(Arrays.toString(rc.convertToKivaCommands(input)));
        
        System.out.println("TEST 2"); //should throw exception 
        input = "B";
        System.out.println(Arrays.toString(rc.convertToKivaCommands(input)));
    }
}

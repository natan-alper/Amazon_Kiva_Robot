/**
 * A single scenario to test if the motorLifeTime variable in the 
 * Kiva class is keeping track of the motor life of the Kiva robot.
 * The motor life of the robot is measured in milliseconds.
 * 
 * @see Kiva
 * @see KivaCommand
 * @author Natan Alper
 * @version 4/29/2021
 */

public class KivaMotorLifetimeTester {
    
    public void tester(){
        // Define the FloorMap we'll use for all the tests
        String defaultLayout = ""
                               + "-----\n"
                               + "|K D|\n"
                               + "| P |\n"
                               + "|* *|\n"
                               + "-----\n";
    
        FloorMap defaultMap = new FloorMap(defaultLayout);
        
        // GIVEN
        // A Kiva built with the default map we defined earlier
        Kiva kiva = new Kiva(defaultMap);
        
        System.out.println(String.format("Expected Motor Life Utilized: %d\n"+
        "Actual Motor Life Utilized: %d", 0, kiva.getMotorLifetime()));
        kiva.move(KivaCommand.TURN_RIGHT);
        System.out.println(String.format("Expected Motor Life Utilized: %d\n"+
        "Actual Motor Life Utilized: %d", 1000, kiva.getMotorLifetime()));
        kiva.move(KivaCommand.FORWARD);
        System.out.println(String.format("Expected Motor Life Utilized: %d\n"+
        "Actual Motor Life Utilized: %d", 2000, kiva.getMotorLifetime()));
        kiva.move(KivaCommand.TURN_RIGHT);
        System.out.println(String.format("Expected Motor Life Utilized: %d\n"+
        "Actual Motor Life Utilized: %d", 3000, kiva.getMotorLifetime()));
        kiva.move(KivaCommand.FORWARD);
        System.out.println(String.format("Expected Motor Life Utilized: %d\n"+
        "Actual Motor Life Utilized: %d", 4000, kiva.getMotorLifetime()));
        kiva.move(KivaCommand.TAKE);
        System.out.println(String.format("Expected Motor Life Utilized: %d\n"+
        "Actual Motor Life Utilized: %d", 4000, kiva.getMotorLifetime()));
    }
}

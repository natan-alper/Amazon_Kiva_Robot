import edu.duke.Point;

/**
 * Test Kiva robot movement functionality.
 * 
 * @see Kiva
 * @see KivaCommand
 * @author Natan Alper
 * @version 4/26/2021
 */

public class KivaMoveTest {
    // Define the FloorMap we'll use for all the tests
    String defaultLayout = ""
                           + "-------------\n"
                           + "        P   *\n"
                           + "   **       *\n"
                           + "   **       *\n"
                           + "  K       D *\n"
                           + " * * * * * **\n"
                           + "-------------\n";

    FloorMap defaultMap = new FloorMap(defaultLayout);
    
    //Testing FORWARD movements from multiple starting directionFacings
    public void testForwardFromUp() {
        // GIVEN
        // A Kiva built with the default map we defined earlier
        Kiva kiva = new Kiva(defaultMap);

        // WHEN
        // We move one space forward
        kiva.move(KivaCommand.FORWARD);
        
        // THEN
        // The Kiva has moved one space up
        verifyKivaState("testForwardFromUp", 
            kiva, new Point(2, 3), FacingDirection.UP, false, false);
    }
    
    public void testForwardWhileFacingLeft() {
        // GIVEN
        // A Kiva built with the default map we defined earlier
        Kiva kiva = new Kiva(defaultMap);
        // Change Facing Direction to test forward movement in different direction
        kiva.move(KivaCommand.TURN_LEFT);
        
        // WHEN
        // We move one space forward
        kiva.move(KivaCommand.FORWARD);
        
        // THEN
        // The Kiva has moved one space left
        verifyKivaState("testForwardFromLeft", 
            kiva, new Point(1, 4), FacingDirection.LEFT, false, false);
    }
    
    public void testForwardWhileFacingDown() {
        // GIVEN
        // A Kiva built with the default map we defined earlier
        Kiva kiva = new Kiva(defaultMap);
        // Change Facing Direction to test forward movement in different direction
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.TURN_LEFT);
        
        // WHEN
        // We move one space forward
        kiva.move(KivaCommand.FORWARD);
        
        // THEN
        // The Kiva has moved one space down
        verifyKivaState("testForwardFromDown", 
            kiva, new Point(2, 5), FacingDirection.DOWN, false, false);
    }
    
    public void testForwardWhileFacingRight() {
        // GIVEN
        // A Kiva built with the default map we defined earlier
        Kiva kiva = new Kiva(defaultMap);
        // Change Facing Direction to test forward movement in different direction
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.TURN_LEFT);
        
        // WHEN
        // We move one space forward
        kiva.move(KivaCommand.FORWARD);
        
        // THEN
        // The Kiva has moved one space right
        verifyKivaState("testForwardFromRight", 
            kiva, new Point(3, 4), FacingDirection.RIGHT, false, false);
    }
    
    //Tesing TURN_LEFT movements from multiple starting directionFacings
    public void testTurnLeftFromUp() {
        // GIVEN
        // A Kiva built with the default map we defined earlier
        Kiva kiva = new Kiva(defaultMap);

        // WHEN
        // We turn left once
        kiva.move(KivaCommand.TURN_LEFT);
        
        // THEN
        // The Kiva should be facing left
        verifyKivaState("testTurnLeftFromUp", 
            kiva, new Point(2, 4), FacingDirection.LEFT, false, false);
    }
    
    public void testTurnLeftFromLeft() {
        // GIVEN
        // A Kiva built with the default map we defined earlier
        Kiva kiva = new Kiva(defaultMap);

        // WHEN
        // We turn left twice
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.TURN_LEFT);
        
        // THEN
        // The Kiva should be facing down
        verifyKivaState("testTurnLeftFromLeft", 
            kiva, new Point(2, 4), FacingDirection.DOWN, false, false);
    }
    
    public void testTurnLeftFromDown() {
        // GIVEN
        // A Kiva built with the default map we defined earlier
        Kiva kiva = new Kiva(defaultMap);

        // WHEN
        // We turn left three times
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.TURN_LEFT);
        
        // THEN
        // The Kiva should be facing right
        verifyKivaState("testTurnLeftFromDown", 
            kiva, new Point(2, 4), FacingDirection.RIGHT, false, false);
    }
    
    public void testTurnLeftFromRight() {
        // GIVEN
        // A Kiva built with the default map we defined earlier
        Kiva kiva = new Kiva(defaultMap);

        // WHEN
        // We turn left four times
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.TURN_LEFT);
        
        // THEN
        // The Kiva should be facing right
        verifyKivaState("testTurnLeftFromRight", 
            kiva, new Point(2, 4), FacingDirection.UP, false, false);
    }
    
    //Tesing TURN_RIGHT movements from multiple starting directionFacings
    public void testTurnRightFromUp() {
        // GIVEN
        // A Kiva built with the default map we defined earlier
        Kiva kiva = new Kiva(defaultMap);

        // WHEN
        // We turn right once
        kiva.move(KivaCommand.TURN_RIGHT);
        
        // THEN
        // The Kiva should be facing right
        verifyKivaState("testTurnRightFromUp", 
            kiva, new Point(2, 4), FacingDirection.RIGHT, false, false);
    }
    
    public void testTurnRightFromLeft() {
        // GIVEN
        // A Kiva built with the default map we defined earlier
        Kiva kiva = new Kiva(defaultMap);

        // WHEN
        // We turn left (so we start facing left), then turn right from there
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.TURN_RIGHT);
        
        // THEN
        // The Kiva should be facing up
        verifyKivaState("testTurnRightFromLeft", 
            kiva, new Point(2, 4), FacingDirection.UP, false, false);
    }
    
    public void testTurnRightFromDown() {
        // GIVEN
        // A Kiva built with the default map we defined earlier
        Kiva kiva = new Kiva(defaultMap);

        // WHEN
        // We turn left twice (so we are facing down), then turn right from there
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.TURN_RIGHT);
        
        // THEN
        // The Kiva should be facing left
        verifyKivaState("testTurnRightFromDown", 
            kiva, new Point(2, 4), FacingDirection.LEFT, false, false);
    }
    
    public void testTurnRightFromRight() {
        // GIVEN
        // A Kiva built with the default map we defined earlier
        Kiva kiva = new Kiva(defaultMap);

        // WHEN
        // We turn left three times (so we are facing down), then turn right from there
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.TURN_RIGHT);
        
        // THEN
        // The Kiva should be facing down
        verifyKivaState("testTurnRightFromRight", 
            kiva, new Point(2, 4), FacingDirection.DOWN, false, false);
    }
    
    public void testTakeOnPod(){
        // GIVEN
        // A Kiva built with the default map we defined earlier
        Kiva kiva = new Kiva(defaultMap);
        
        // WHEN
        // We go up three times, turn right, move right six times, and take the pod
        for (int i = 1; i <=3; i++){
            kiva.move(KivaCommand.FORWARD);
        }
        kiva.move(KivaCommand.TURN_RIGHT);
        for (int i = 1; i <=6; i++){
            kiva.move(KivaCommand.FORWARD);
        }
        kiva.move(KivaCommand.TAKE);
        // THEN
        // The Kiva should be carrying the pod
        verifyKivaState("testTakeOnPod", 
            kiva, new Point(8, 1), FacingDirection.RIGHT, true, false);
    }
    
    public void testDropOnDropZone(){
        // GIVEN
        // A Kiva built with the default map we defined earlier
        Kiva kiva = new Kiva(defaultMap);
        
        // WHEN
        // We go up three times, turn right, move right six times, and take the pod
        for (int i = 1; i <=3; i++){
            kiva.move(KivaCommand.FORWARD);
        }
        kiva.move(KivaCommand.TURN_RIGHT);
        for (int i = 1; i <=6; i++){
            kiva.move(KivaCommand.FORWARD);
        }
        kiva.move(KivaCommand.TAKE);
        //Picked up Pod and now moving to Drop
        for (int i = 1; i <=2; i++){
            kiva.move(KivaCommand.FORWARD);
        }
        kiva.move(KivaCommand.TURN_RIGHT);
        for (int i = 1; i <=3; i++){
            kiva.move(KivaCommand.FORWARD);
        }
        kiva.move(KivaCommand.DROP);
        
        // THEN
        // The Kiva should be carrying the pod
        verifyKivaState("testDropOnDropZone", 
            kiva, new Point(10, 4), FacingDirection.DOWN, false, true);
    }
    
    //Checks if exceptions are thrown if Kiva tries to move off the map
    public void testMoveOutOfBounds() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        System.out.println("testMoveOutOfBounds: (expect an IllegalMoveException)");
        kiva.move(KivaCommand.FORWARD);
        
        // This only runs if no exception was thrown
        System.out.println("testMoveOutOfBounds FAIL!");
        System.out.println("Moved outside the FloorMap!");
    }
    
    //Checks if exceptions are thrown if Kiva tries to move into an obstacle
    public void testMoveIntoObstacle() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.TURN_RIGHT);
        System.out.println("testMoveIntoObstacle: (expect an IllegalMoveException)");
        kiva.move(KivaCommand.FORWARD);
        
        // This only runs if no exception was thrown
        System.out.println("testMoveIntoObstacles FAIL!");
        System.out.println("Moved into an obstacle!");
    }
    
    //Checks if exceptions are thrown if Kiva tries to take a pod when there isn't one at the location
    public void testTakeWhenNoPod() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.move(KivaCommand.FORWARD);
        System.out.println("testTakeWhenNoPod: (expect a NoPodException)");
        kiva.move(KivaCommand.TAKE);
        
        // This only runs if no exception was thrown
        System.out.println("testTakeWhenNoPods FAIL!");
        System.out.println("Somehow able to take when no pod!");
    }
    
    //Checks if exceptions are thrown if Kiva tries to drop pod outside of drop-zone
    public void testDropOutsideDropZone() {
        Kiva kiva = new Kiva(defaultMap);
        for (int i = 1; i <=3; i++){
            kiva.move(KivaCommand.FORWARD);
        }
        kiva.move(KivaCommand.TURN_RIGHT);
        for (int i = 1; i <=6; i++){
            kiva.move(KivaCommand.FORWARD);
        }
        kiva.move(KivaCommand.TAKE);
        kiva.move(KivaCommand.FORWARD);
        System.out.println("testDropOutsideDropZone: (expect an IllegalDropZoneException)");
        kiva.move(KivaCommand.DROP);
        
        // This only runs if no exception was thrown
        System.out.println("testDropOutsideDropZones FAIL!");
        System.out.println("Dropped pod outside drop-zone!");
    }
    
    //Checks if exceptions are thrown if Kiva tries to drop but doesn't have a pod to drop
    public void testDropWithNoPod() {
        Kiva kiva = new Kiva(defaultMap);
        for (int i = 1; i <=3; i++){
            kiva.move(KivaCommand.FORWARD);
        }
        kiva.move(KivaCommand.TURN_RIGHT);
        for (int i = 1; i <=6; i++){
            kiva.move(KivaCommand.FORWARD);
        }
        kiva.move(KivaCommand.FORWARD);
        System.out.println("testDropWithNoPod: (expect an IllegalMoveException)");
        kiva.move(KivaCommand.DROP);
        
        // This only runs if no exception was thrown
        System.out.println("testDropWithNoPods FAIL!");
        System.out.println("Attempted to drop with no pod!");
    }
    
    
    private boolean sameLocation(Point a, Point b) {
        return a.getX() == b.getX() && a.getY() == b.getY();
    }

    private void verifyKivaState(
            String testName,
            Kiva actual,
            Point expectLocation,
            FacingDirection expectDirection,
            boolean expectCarry,
            boolean expectDropped) {
                
        Point actualLocation = actual.getCurrentLocation();
        if (sameLocation(actualLocation, expectLocation)) {
            System.out.println(
                    String.format("%s: current location SUCCESS", testName));
        }
        else {
            System.out.println(
                    String.format("%s: current location FAIL!", testName));
            System.out.println(
                    String.format("Expected %s, got %s",
                            expectLocation, actualLocation));
        }

        FacingDirection actualDirection = actual.getDirectionFacing();
        if (actualDirection == expectDirection) {
            System.out.println(
                    String.format("%s: facing direction SUCCESS", testName));
        }
        else {
            System.out.println(
                    String.format("%s: facing direction FAIL!", testName));
            System.out.println(
                    String.format("Expected %s, got %s",
                            expectDirection, actualDirection));
        }

        boolean actualCarry = actual.isCarryingPod();
        if (actualCarry == expectCarry) {
            System.out.println(
                    String.format("%s: carrying pod SUCCESS", testName));
        }
        else {
            System.out.println(
                    String.format("%s: carrying pod FAIL!", testName));
            System.out.println(
                    String.format("Expected %s, got %s",
                            expectCarry, actualCarry));
        }

        boolean actualDropped = actual.isSuccessfullyDropped();
        if (actualDropped == expectDropped) {
            System.out.println(
                    String.format("%s: successfully dropped SUCCESS", testName));
        }
        else {
            System.out.println(
                    String.format("%s: successfully dropped FAIL!", testName));
            System.out.println(
                    String.format("Expected %s, got %s",
                            expectDropped, actualDropped));
        }
    }
}
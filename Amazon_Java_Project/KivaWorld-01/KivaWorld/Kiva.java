import edu.duke.Point;

/**
 * Kiva objects can perform actions such as moving around 
 * and picking and dropping items. 
 * A Kiva object's attributes include it's current location on a map, 
 * current direction it's facing, the map layout that it's moving on, status of
 * whether it's carrying a pod, if it has successfully dropped a pod and a
 * motor life time that indicates how much of it's motor life has been spent.
 * 
 * @see KivaCommand
 * @author Natan Alper 
 * @version 4/25/2021
 */

public class Kiva {
    private Point currentLocation;
    private FacingDirection directionFacing;
    private FloorMap map;
    private boolean carryingPod;
    private boolean successfullyDropped;
    //72,000,000,000 milliseconds in 20,000 hours = robot motor life time
    private long motorLifetime;
       
    /**
     * Creates a Kiva robot instance using only a FloorMap.
     * @param map FloorMap of the area. Includes the starting location of 
     * the Kiva robot, location of the boundaries, obstacles, pod and drop-zone.
     */
    public Kiva(FloorMap map){
        this.currentLocation = map.getInitialKivaLocation();
        this.directionFacing = FacingDirection.UP;
        this.map = map;
        this.carryingPod = false;
        this.successfullyDropped = false;
        this.motorLifetime = 0;
    }
    
    /**
     * Creates a Kiva robot instance using a FloorMap and a Point to set 
     * the starting location of the Kiva robot.
     * @param map FloorMap of the area. Includes the locations of the Kiva robot, 
     * boudaries, obstacles, pod and drop-zone.
     * @param currentLocation Starting location of the Kiva robot.
     */
    public Kiva(FloorMap map, Point currentLocation){
        this.currentLocation = currentLocation;
        this.directionFacing = FacingDirection.UP;
        this.map = map;
        this.carryingPod = false;
        this.successfullyDropped = false;
        this.motorLifetime = 0;
    }
    
    /**
     * Depending on the KivaCommand it receives, move() will update the 
     * current location, the direction the robot is pointing, 
     * whether it is carrying a pod or if the pod has been successfully dropped.
     * @param command KivaCommand that indicates the specific movement the 
     * Kiva robot should perform.
     * @see #turnLeft()
     * @see #turnRight()
     * @see #take()
     * @see #drop()
     */
    public void move(KivaCommand command){
        if (command.getDirectionKey()=='F'){
            moveForward();
        }
        else if (command.getDirectionKey()=='L'){
            turnLeft();
        }
        else if (command.getDirectionKey()=='R'){
            turnRight();
        }
        else if (command.getDirectionKey()=='T'){
            take();
        }
        else if (command.getDirectionKey()=='D'){
            drop();
        }
    }
    
    //*** HELPER METHODS FOR move(KivaCommand command) method
    private void moveForward() {
        Point curDir = this.directionFacing.getDelta();
        //WHEN FACING UP --> Move up 1
        if (curDir.getX()==0 && curDir.getY()==-1){
            Point newLoc = new Point(this.currentLocation.getX(), this.currentLocation.getY()-1);
            FloorMapObject mapObj = this.map.getObjectAtLocation(newLoc);
            this.handleExceptions(newLoc, mapObj);
            this.currentLocation = newLoc;
        }
        //WHEN FACING LEFT --> Move left 1
        else if (curDir.getX()==-1 && curDir.getY()==0){
            Point newLoc = new Point(this.currentLocation.getX()-1, this.currentLocation.getY());
            FloorMapObject mapObj = this.map.getObjectAtLocation(newLoc);
            this.handleExceptions(newLoc, mapObj);
            this.currentLocation = newLoc;
        }
        //WHEN FACING DOWN --> Move down 1
        else if (curDir.getX()==0 && curDir.getY()==1){
            Point newLoc = new Point(this.currentLocation.getX(), this.currentLocation.getY()+1);
            FloorMapObject mapObj = this.map.getObjectAtLocation(newLoc);
            this.handleExceptions(newLoc, mapObj);
            this.currentLocation = newLoc;
        }
        //WHEN FACING RIGHT --> Move right 1
        else if (curDir.getX()==1 && curDir.getY()==0){
            Point newLoc = new Point(this.currentLocation.getX()+1, this.currentLocation.getY());
            FloorMapObject mapObj = this.map.getObjectAtLocation(newLoc);
            this.handleExceptions(newLoc, mapObj);
            this.currentLocation = newLoc;
        }
        this.incrementMotorLifetime();
        this.successfullyDropped = false;
    }
            
    //Handle exceptions for moveForward() methods
    private void handleExceptions(Point newLoc, FloorMapObject mapObj){
        //throws exception if Kiva tries to move off the map
        if ((newLoc.getX() > map.getMaxColNum()) ||
                newLoc.getY() > map.getMaxRowNum()){
            throw new IllegalMoveException(String.format("Can't MOVE: location %s "+
            "is off the map!", newLoc));
        }
        //throws exception if Kiva tries to move into an obstacle
        else if (mapObj == FloorMapObject.OBSTACLE){
            throw new IllegalMoveException(String.format("Can't MOVE FORWARD: "+
            "location %s has an %s, hence not EMPTY!", this.currentLocation, mapObj));
        }
    }
    
    /**
     * Kiva object's direction will rotate to the left. 
     * Does NOT change the location that the Kiva object is currently at. 
     */
    public void turnLeft() {
        Point curDir = this.directionFacing.getDelta();
        //WHEN FACING UP --> LEFT
        if (curDir.getX()==0 && curDir.getY()==-1){
            this.directionFacing = FacingDirection.LEFT;
        }
        //WHEN FACING LEFT --> DOWN
        else if (curDir.getX()==-1 && curDir.getY()==0){
            this.directionFacing = FacingDirection.DOWN;
        }
        //WHEN FACING DOWN --> RIGHT
        else if (curDir.getX()==0 && curDir.getY()==1){
            this.directionFacing = FacingDirection.RIGHT;
        }
        //WHEN FACING RIGHT --> UP
        else if (curDir.getX()==1 && curDir.getY()==0){
            this.directionFacing = FacingDirection.UP;
        }
        this.incrementMotorLifetime();
        this.successfullyDropped = false;
    }
    
    /**
     * Kiva object's direction will rotate to the right. 
     * Does NOT change the location that the Kiva object is currently at. 
     */
    public void turnRight() {
        Point curDir = this.directionFacing.getDelta();
        //WHEN FACING UP --> RIGHT
        if (curDir.getX()==0 && curDir.getY()==-1){
            this.directionFacing = FacingDirection.RIGHT;
        }
        //WHEN FACING LEFT --> UP
        else if (curDir.getX()==-1 && curDir.getY()==0){
            this.directionFacing = FacingDirection.UP;
        }
        //WHEN FACING DOWN --> LEFT
        else if (curDir.getX()==0 && curDir.getY()==1){
            this.directionFacing = FacingDirection.LEFT;
        }
        //WHEN FACING RIGHT --> DOWN
        else if (curDir.getX()==1 && curDir.getY()==0){
            this.directionFacing = FacingDirection.DOWN;
        }
        this.incrementMotorLifetime();
        this.successfullyDropped = false;
    }
    
    /**
     * Kiva object will take/pick up pod. 
     * This will throw a NoPodException if it is already holding a pod 
     * or, if there is no pod at the current location.
     */
    public void take() {
        FloorMapObject mapObj = this.map.getObjectAtLocation(this.currentLocation);
        //throws exception if Kiva tries to take pod when there is no pod present
        if (mapObj != FloorMapObject.POD || this.carryingPod == true){
            throw new NoPodException(String.format("Can't TAKE: location %s is %s, "+
            "not POD!", this.currentLocation, mapObj));
        }
        this.carryingPod = true;
        this.successfullyDropped = false;
    }
    
    /**
     * Kiva object will drop pod. 
     * This will throw a IllegalMoveException if it is not holding a pod at the 
     * time of the drop or, an IllegalDropZoneException if it is not 
     * at a drop-zone location.
     */
    public void drop() {
        FloorMapObject mapObj = this.map.getObjectAtLocation(this.currentLocation);
        //throws exception if Kiva tries to drop pod when it is not holding a pod
        if (this.carryingPod == false){
            throw new IllegalMoveException(String.format("Can't DROP: currently "+
            "not holding a pod."));
        }
        //throws exception if Kiva tries to drop pod in a non-drop-zone location
        else if (mapObj != FloorMapObject.DROP_ZONE){
            throw new IllegalDropZoneException(String.format("Can't DROP: "+
            "location %s is %s not a DROP_ZONE!", this.currentLocation, mapObj));
        }
        this.successfullyDropped = true;
        this.carryingPod = false;
    }
    
    /**
     * Adds 1000 milliseconds to the motorLifetime 
     * (indicating that another 1000 milliseconds have been used).
     */
    public void incrementMotorLifetime(){
        this.motorLifetime = this.motorLifetime + 1000;
    }
    
    //*** End of HELPERS ***
    
    //*** GETTERS ***
    /**
     * Returns the current location of the Kiva object.
     * @return the current location as a 
     * Point (x-y coordinate). 
     */
    protected Point getCurrentLocation(){
        return this.currentLocation;
    }
    
    /**
     * Returns the direction that the Kiva object is facing on the map
     * (UP, DOWN, LEFT, RIGHT).
     * @return current direction that the Kiva is facing as a FacingDirection object
     */
    protected FacingDirection getDirectionFacing(){
        return this.directionFacing;
    }
    
    /**
     * Returns the map which the Kiva is using to navigate.
     * @return map that shows the starting locations of the Kiva, obstacles,
     * boundaries, pod, and drop-zone, as a FloorMap object.
     */
    protected FloorMap getMap(){
        return this.map;
    }
    
    /**
     * Returns true if Kiva is carrying the pod and false if not.
     */
    public boolean isCarryingPod(){
        return this.carryingPod;
    }
    
    /**
     * Returns true if Kiva successfully dropped the pod and false if not.
     */
    public boolean isSuccessfullyDropped(){
        return this.successfullyDropped;
    }
    
    /**
     * Returns the motorLifetime utilized by the Kiva, measured in milliseconds.
     */
    public long getMotorLifetime(){
        return this.motorLifetime;
    }
}

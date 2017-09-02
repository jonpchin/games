//Joanthan Chin
package rooms;

import Equipment.Item;
import Monsters.Monster;
import java.util.ArrayList;

/**
 *
 * @author jonc
 */
//superclass
public class Room {
    //this will be handy in the future when we need to identify a room
    int roomID;
    //what will be displayed in each room when a charater walks in
    public String roomDescription = "empty room description";
    //map area such as ThePit, Factory, CentralHub, etc
    String area;
    //3 coordinates to store x, y, z location in an area
    int xCoordinate;
    int yCoordinate;
    int zCoordinate;
    
    //by default all directions are disabled except north, east, south and west. 
    //when room is actually created we enable directions and doors accordingly
    private boolean north = true;
    private boolean south = true;
    private boolean east = true;
    private boolean west = true;
    private boolean up = false;
    private boolean down = false;
    private boolean northDoor = false;
    private boolean southDoor = false;
    private boolean eastDoor = false;
    private boolean westDoor = false;
    private boolean secret = false;
    
    public Room(){
        
    }
    
    public Room(int id){
        roomID = id;
    }
    
    public Room(String desc){
        roomDescription = desc;
    }
    
    //initalize basic directions
    public Room(boolean n, boolean e, boolean s,  boolean w){
        north = n;
        east = e;
        south = s;
        west = w;
    }
    
    //intitalizes advanced directions 
    public Room(boolean n, boolean e, boolean s,  boolean w, boolean u, boolean d, boolean nD, boolean eD,  boolean sD, boolean wD,  boolean sec){
        north = n;
        east = e;
        south = s;
        west = w;
        up = u;
        down = d;
        northDoor = nD;
        eastDoor = eD;
        southDoor = sD;
        westDoor = wD;
        secret = sec;
    }

    

    /**
     * @return the roomID
     */
    public int getRoomID() {
        return roomID;
    }

    /**
     * @param roomID the roomID to set
     */
    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    /**
     * @return the roomDescription
     */
    public String getRoomDescription() {
        return roomDescription;
    }

    /**
     * @param roomDescription the roomDescription to set
     */
    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    /**
     * @return the north
     */
    public boolean isNorth() {
        return north;
    }

    /**
     * @param north the north to set
     */
    public void setNorth(boolean north) {
        this.north = north;
    }

    /**
     * @return the south
     */
    public boolean isSouth() {
        return south;
    }

    /**
     * @param south the south to set
     */
    public void setSouth(boolean south) {
        this.south = south;
    }

    /**
     * @return the east
     */
    public boolean isEast() {
        return east;
    }

    /**
     * @param east the east to set
     */
    public void setEast(boolean east) {
        this.east = east;
    }

    /**
     * @return the west
     */
    public boolean isWest() {
        return west;
    }

    /**
     * @param west the west to set
     */
    public void setWest(boolean west) {
        this.west = west;
    }

    /**
     * @return the up
     */
    public boolean isUp() {
        return up;
    }

    /**
     * @param up the up to set
     */
    public void setUp(boolean up) {
        this.up = up;
    }

    /**
     * @return the down
     */
    public boolean isDown() {
        return down;
    }

    /**
     * @param down the down to set
     */
    public void setDown(boolean down) {
        this.down = down;
    }

    /**
     * @return the northDoor
     */
    public boolean isNorthDoor() {
        return northDoor;
    }

    /**
     * @param northDoor the northDoor to set
     */
    public void setNorthDoor(boolean northDoor) {
        this.northDoor = northDoor;
    }

    /**
     * @return the southDoor
     */
    public boolean isSouthDoor() {
        return southDoor;
    }

    /**
     * @param southDoor the southDoor to set
     */
    public void setSouthDoor(boolean southDoor) {
        this.southDoor = southDoor;
    }

    /**
     * @return the eastDoor
     */
    public boolean isEastDoor() {
        return eastDoor;
    }

    /**
     * @param eastDoor the eastDoor to set
     */
    public void setEastDoor(boolean eastDoor) {
        this.eastDoor = eastDoor;
    }

    /**
     * @return the westDoor
     */
    public boolean isWestDoor() {
        return westDoor;
    }

    /**
     * @param westDoor the westDoor to set
     */
    public void setWestDoor(boolean westDoor) {
        this.westDoor = westDoor;
    }

    /**
     * @return the secret
     */
    public boolean isSecret() {
        return secret;
    }

    /**
     * @param secret the secret to set
     */
    public void setSecret(boolean secret) {
        this.secret = secret;
    }

    /**
     * @return the area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(String area) {
        this.area = area;
    }

    public ArrayList<Item> getItems() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addItem(Item searchInventory) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Monster> getMonsters() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setItems(ArrayList<Item> removeItem) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   
}

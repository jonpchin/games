/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import Equipment.Item;
import Monsters.Monster;
import Skills.Basic;
import java.util.ArrayList;
import rooms.CentralHub;
import rooms.Factory;
import rooms.Room;
import rooms.Secret;
import rooms.Sewers;
import rooms.ThePit;
import rooms.TrainStation;
import rooms.Wastelands;
import scifimud.ObjectCreator;

/**
 *
 * @author jonc
 */

//this class information will be saved into a text file so player can access when 
//the player logins again
public class Player implements Basic{
    
    private int health;
    private int energy;
    private int attack;
    private int speed;   
    private int defense;
    private int intelligence;
    private int level;
    private int experience;
    private int bitcoins;
    
    //x, y, and z coordinates on the map
    private int xCoordinate;
    private int yCoordinate;
    private int zCoordinate;
    private String location;
    
    private Room[][][] map;
    private Room room;
  
    
    //stores player information
    private String name;
    private String password;
    //name of the class such as Cyborg, Time Traveller, etc
    private String className;
    private String status;
   
    //dyanmic size for players inventory so it can hold as much items as memory can hold
    //or until the player is carrying the max weight
    //max weight needs to be implemented to prevent abuse of infinite items
    private ArrayList<Item> inventory = new ArrayList();
    
    //players equipment
    private Item weapon;
    private Item head;
    private Item torso;
    private Item pants;
    private Item shoes;
    
    public Player(){
       
    }
    
    /**
     * @return the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * @param health the health to set
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * @return the energy
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * @param energy the energy to set
     */
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    /**
     * @return the attack
     */
    public int getAttack() {
        return attack;
    }

    /**
     * @param attack the attack to set
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * @return the defense
     */
    public int getDefense() {
        return defense;
    }

    /**
     * @param defense the defense to set
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * @return the intelligence
     */
    public int getIntelligence() {
        return intelligence;
    }

    /**
     * @param intelligence the intelligence to set
     */
    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    /**
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * @return the experience
     */
    public int getExperience() {
        return experience;
    }

    /**
     * @param experience the experience to set
     */
    public void setExperience(int experience) {
        this.experience = experience;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    /**
     * @param className the className to set
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * @return the weapon
     */
    public Item getWeapon() {
        return weapon;
    }

    /**
     * @param weapon the weapon to set
     */
    public void setWeapon(Item weapon) {
        this.weapon = weapon;
    }

    /**
     * @return the head
     */
    public Item getHead() {
        return head;
    }

    /**
     * @param head the head to set
     */
    public void setHead(Item head) {
        this.head = head;
    }

    /**
     * @return the torso
     */
    public Item getTorso() {
        return torso;
    }

    /**
     * @param torso the torso to set
     */
    public void setTorso(Item torso) {
        this.torso = torso;
    }

    /**
     * @return the pants
     */
    public Item getPants() {
        return pants;
    }

    /**
     * @param pants the pants to set
     */
    public void setPants(Item pants) {
        this.pants = pants;
    }

    /**
     * @return the shoes
     */
    public Item getShoes() {
        return shoes;
    }

    /**
     * @param shoes the shoes to set
     */
    public void setShoes(Item shoes) {
        this.shoes = shoes;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

   
    @Override
    public void look() {
       System.out.println(getRoom(getxCoordinate(), getyCoordinate(), getzCoordinate()).roomDescription);
       System.out.println("You see the following in the room:");
       here();
    }

    //examines inventory or equpiment to give stats or examine monsters in room
    public void examine(String target) {
        Item item = new Item();
        item = searchInventory(target, getInventory());
        ArrayList<Monster> monsterList = new ArrayList();
        monsterList = getRoom(getxCoordinate(), getyCoordinate(),getzCoordinate()).getMonsters();
        int i;
        Monster monster = new Monster();
        monster = searchMonster(target, monsterList);
        
        ArrayList<Item> itemListInRoom = new ArrayList();
        itemListInRoom = getRoom(getxCoordinate(), getyCoordinate(),getzCoordinate()).getItems();
        
        Item itemInRoom = new Item();
        itemInRoom = searchItemInRoom(target, itemListInRoom);
        
        if(item!=null){
            item.displayProperties();
            System.out.println(item.getDescription());
        }else if(monster!=null){
            System.out.println(monster.getDescription());
            System.out.println(monster.getName() + " looks " + monster.getStatus());
        }else if(itemInRoom!=null){
            System.out.println(itemInRoom.getDescription());
        }else{
            System.out.println("Examine what?");
        }
    }

    @Override
    public void tell() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void say() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void yell() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void equipment() {
        System.out.println("You are wearing :");
        System.out.println("Weapon : " + getWeapon().getName());
        System.out.println("Head : " + getHead().getName());
        System.out.println("Torso : " + getTorso().getName());
        System.out.println("Head : " + getHead().getName());
        System.out.println("Shoes : " + getShoes().getName());
    }

    @Override
    public void inventory() {
        int i;
        String inventory = ObjectCreator.getInventory(getInventory());
        String[] newInventory  = inventory.split(", ");

        System.out.println("Inventory : ");
        for(i=0 ; i<newInventory.length; i++){
            System.out.println(newInventory[i]);
        }
    }

    @Override
    public void east() {
        if(getRoom(getxCoordinate(), getyCoordinate(), getzCoordinate()).isEast()){
            setxCoordinate(getxCoordinate() + 1);
            setxCoordinate(getxCoordinate());
            //used for debugging purposes to make sure room is updating
            //in the future we will add displayRoom objects and descriptions
            System.out.printf("You are located in the room coordinates %d %d %d\n", getxCoordinate(), getyCoordinate(), getzCoordinate());
            look();
        }
        else{
            System.out.println("There is no exit there.");
        }
        
    }

    @Override
    public void west() {
       if(getRoom(getxCoordinate(), getyCoordinate(), getzCoordinate()).isWest()){
            setxCoordinate(getxCoordinate() - 1);
            setxCoordinate(getxCoordinate());
            System.out.printf("You are located in the room coordinates %d %d %d\n", getxCoordinate(), getyCoordinate(), getzCoordinate());
            look();
        }
        else{
            System.out.println("There is no exit there.");
        }
       
    }

    @Override
    public void south() {
        if(getRoom(getxCoordinate(), getyCoordinate(), getzCoordinate()).isSouth()){
            setyCoordinate(getyCoordinate() - 1);
            setyCoordinate(getyCoordinate());
            System.out.printf("You are located in the room coordinates %d %d %d\n", getxCoordinate(), getyCoordinate(), getzCoordinate());
            look();
        }
        else{
            System.out.println("There is no exit there.");
        }
        
    }

    @Override
    public void north() {
        
        if(getRoom(getxCoordinate(), getyCoordinate(), getzCoordinate()).isNorth()){
            setyCoordinate(getyCoordinate() + 1);
            setyCoordinate(getyCoordinate());
            System.out.printf("You are located in the room coordinates %d %d %d\n", getxCoordinate(), getyCoordinate(), getzCoordinate());
            look();
        }
        else{
            System.out.println("There is no exit there.");
        }
        
    }
    
     @Override
    public void up() {
        if(getRoom(getxCoordinate(), getyCoordinate(), getzCoordinate()).isUp()){
            setzCoordinate(getzCoordinate() + 1);
            setzCoordinate(getzCoordinate());
            System.out.printf("You are located in the room coordinates %d %d %d\n", getxCoordinate(), getyCoordinate(), getzCoordinate());
            look();
        }
        else{
            System.out.println("There is no exit there.");
        }
        
    }

    @Override
    public void down() {
        if(getRoom(getxCoordinate(), getyCoordinate(), getzCoordinate()).isDown()){
            setzCoordinate(getzCoordinate() - 1);
            setzCoordinate(getzCoordinate());
            System.out.printf("You are located in the room coordinates %d %d %d\n", getxCoordinate(), getyCoordinate(), getzCoordinate());

        }
        else{
            System.out.println("There is no exit there.");
        }
    }

    @Override
    public void here() {
        int i;
        //used to store the arraylist of all items in the room
        ArrayList<Item> tempInventory = new ArrayList();
        //used to store the arraylist of all monsters in the room
        ArrayList<Monster> tempMonsters = new ArrayList();

        tempInventory = getRoom(getxCoordinate(), getyCoordinate(), getzCoordinate()).getItems();
        for(i=0; i<tempInventory.size(); i++){
            
            System.out.println(tempInventory.get(i).getName());
        }
        tempMonsters = getRoom(getxCoordinate(), getyCoordinate(),getzCoordinate()).getMonsters();
        for(i=0; i<tempMonsters.size(); i++){
            System.out.println(tempMonsters.get(i).getName());
        }
    }
    

    @Override
    public void self() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void level() {
        System.out.println("You are level " + getLevel());
    }

    @Override
    public void stats() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void location() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public void bitcoins() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //removes an item from players inventory
    @Override
    public void remove(String target) {
        Item item = new Item();
        item = findEquipment(target);
        //used a "empty" slot
        Item temp = new Item();
        temp.setName("Nothing");

        if(item != null){
            switch(item.getGroup()){
                case "weapon":
                    setWeapon(temp);
                    break;
                case "torso":
                    setTorso(temp);
                    break;
                case "head":
                    setHead(temp);
                    break;
                case "pants":
                    setPants(temp);
                    break;
                case "shoes":
                    setShoes(temp);
                    break;
                default:
                    System.out.println("Error no match in assiging weapon. Pleaese contact admin.");
                    break;
            }
            //adds item to inventory
           System.out.println("You remove a(n) " + item.getName()); 
           setInventory(addItem(item));
        }
        else{
            System.out.println("Remove what?");
        }
    }
    
    @Override
    public void drop(String target) {
        Item item = new Item();
        item = searchInventory(target, getInventory());
        if(item!=null){
            //removes item from player inventory
            setInventory(removeItem(target, getInventory()));
            //adds the item to the room
            getRoom(getxCoordinate(), getyCoordinate(), getzCoordinate()).addItem(item);
            System.out.println("You successully drop a(n) " + item.getName());
        }
        else{
            System.out.println("Drop what?");
        }
    }
    
    @Override
    public void take(String target) {
        Item item = new Item();
         //uses the same function as when searching a player inventory but this passes in different paramters such as the items in the room instead of the items in the players inventory
        item =  searchInventory(target, getRoom(getxCoordinate(), getyCoordinate(), getzCoordinate()).getItems());
        if(item != null){
            //removes item from room if item is in the room
            getRoom(getxCoordinate(), getyCoordinate(), getzCoordinate()).setItems(removeItem(target, getRoom(getxCoordinate(), getyCoordinate(), getzCoordinate()).getItems()));
            //adds item to inventory
            System.out.println("You take a(n) " + item.getName());
            setInventory(addItem(item));
        }
        else{
            System.out.println("Take what?");
        }
    }
    
    @Override
    public void wear(String target) {
        Item item = new Item();
        item = searchInventory(target, getInventory());
        if(item!=null){
            switch(item.getGroup()){
                case "drink":
                    System.out.println("You can't wear a drink");
                    break;
                case "food":
                    System.out.println("You can't wear food.");
                    break;
                case "artifact":
                    System.out.println("You can't wear that.");
                    break;
                case "weapon":
                    //if there is already an item being worn in the slot first remove the item and then wear the desired equipment
                    if(!getWeapon().getName().equals("Nothing")){
                       remove(getWeapon().getName());
                    }
                    setWeapon(item);
                    System.out.println("You wear a " + item.getName());
                    break;
                case "torso":
                    if(!getTorso().getName().equals("Nothing")){
                       remove(getTorso().getName());
                    }
                    setTorso(item);
                    System.out.println("You wear a " + item.getName());
                    break;
                case "head":
                    if(!getHead().getName().equals("Nothing")){
                       remove(getHead().getName());
                    }
                    setHead(item);
                    System.out.println("You wear a " + item.getName());
                    break;
                case "shoes":
                    if(!getShoes().getName().equals("Nothing")){
                       remove(getShoes().getName());
                    }
                    setShoes(item);
                    System.out.println("You wear a " + item.getName());
                    break;
                case "pants":
                    if(!getPants().getName().equals("Nothing")){
                       remove(getPants().getName());
                    }
                    setPants(item);
                    System.out.println("You wear a " + item.getName());
                    break;
                default:
                    break;
            }
            //removes the item from the players inventory now that he is wearing it
            setInventory(removeItem(target, getInventory()));
        }
        else{
            System.out.println("Wear what?");
        }
    }

    /**
     * @return the bitcoins
     */
    public int getBitcoins() {
        return bitcoins;
    }

    /**
     * @param bitcoins the bitcoins to set
     */
    public void setBitcoins(int bitcoins) {
        this.bitcoins = bitcoins;
    }

    /**
     * @return the xCoordinate
     */
    public int getxCoordinate() {
        return xCoordinate;
    }

    /**
     * @param xCoordinate the xCoordinate to set
     */
    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    /**
     * @return the yCoordinate
     */
    public int getyCoordinate() {
        return yCoordinate;
    }

    /**
     * @param yCoordinate the yCoordinate to set
     */
    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    /**
     * @return the zCoordinate
     */
    public int getzCoordinate() {
        return zCoordinate;
    }

    /**
     * @param zCoordinate the zCoordinate to set
     */
    public void setzCoordinate(int zCoordinate) {
        this.zCoordinate = zCoordinate;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the area
     */
    public Object[][][] getArea() {
        return getMap();
    }

    /**
     * @param area the area to set
     */
  
  

    /**
     * @param x
     * @param y
     * @param z
     * @return the room
     */
    public Room getRoom(int x, int y, int z) {
        return getMap()[x][y][z];
    }

    /**
     * @param room the room to set
     */
    public void setRoom(Room room) {
        this.room = room;
    }
    
    public void setArea(ThePit[][][] mainMap) {
        map = mainMap;
    }

    public void setArea(CentralHub[][][] hubMap) {
        map = hubMap;
    }
    public void setArea(Factory[][][] factoryMap) {
        map = factoryMap;
    }
    public void setArea(Sewers[][][] sewerMap) {
        map = sewerMap;
    }
    public void setArea(TrainStation[][][] trainMap) {
        map = trainMap;
    }
    public void setArea(Wastelands[][][] wastelandsMap) {
        map = wastelandsMap;
    }
    public void setArea(Secret[][][] secretMap) {
        map = secretMap;
    }

    /**
     * @return the map
     */
    public Room[][][] getMap() {
        return map;
    }

  
    /**
     * @return the room
     */
    public Room getRoom() {
        return room;
    }

   

    /**
     * @return the inventory
     */
    public ArrayList<Item> getInventory() {
        return inventory;
    }

    /**
     * @param playerInventory
     */
    public void setInventory(ArrayList<Item> playerInventory) {
        this.inventory = playerInventory;
    }
    //searches the players inventory or item in a room, given a String of the item name
    //returns the item that is being searched
    public Item searchInventory(String item, ArrayList<Item> inventory){
        int i;
        for(i=0; i<inventory.size(); i++){
            if(item.equalsIgnoreCase(inventory.get(i).getName())){
                return inventory.get(i);
            }else if(inventory.get(i).getName().toLowerCase().startsWith(item.toLowerCase())){
                return inventory.get(i); 
            }
        }
        return null;
    }
    
    
    //removes item from inventory and returns the inventory with one less item
    //if a player has multiple copies of an item then all items of this name will be removed
    public ArrayList<Item> removeItem(String item, ArrayList<Item> inventory){
        int i;
        int removed = 0;
        for(i=0; i<inventory.size(); i++){
            if(item.equalsIgnoreCase(inventory.get(i).getName())){
                inventory.remove(i);
                removed = 1;
            }else if(inventory.get(i).getName().toLowerCase().startsWith(item.toLowerCase())){
                inventory.remove(i);
                removed = 1;
            } 
        }
        if(removed == 0){
            System.out.println("You are not carrying a " + item);
        }
        return inventory;
    }
    
    public ArrayList<Item> addItem(Item item){
        getInventory().add(item);
        return inventory;
    }

    public ArrayList<Item> addItem(String target, ArrayList<Item> inventory) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //checks to see if the any of the equipment matches and if so it returns the piece of equipment. Otherwise it returns null
    //if no match was found
    public Item findEquipment(String item){
        if(item.equalsIgnoreCase(getWeapon().getName()) || getWeapon().getName().toLowerCase().startsWith(item.toLowerCase())){
            return getWeapon();
        }
        else if(item.equalsIgnoreCase(getTorso().getName()) || getTorso().getName().toLowerCase().startsWith(item.toLowerCase())){
            return getTorso();
        }
        
        else if(item.equalsIgnoreCase(getHead().getName()) || getHead().getName().toLowerCase().startsWith(item.toLowerCase())){
            return getHead();
        }
        else if(item.equalsIgnoreCase(getShoes().getName()) || getShoes().getName().toLowerCase().startsWith(item.toLowerCase())){
            return getShoes();
        }
        else if(item.equalsIgnoreCase(getPants().getName()) || getPants().getName().toLowerCase().startsWith(item.toLowerCase())){
            return getPants();
        }
        return null;
    }
    
    public Monster searchMonster(String monster, ArrayList<Monster> monsterList){
        int i;
        for(i=0; i<monsterList.size(); i++){
            if(monster.equals(monsterList.get(i).getName())){
                return monsterList.get(i);
            }else if(monsterList.get(i).getName().toLowerCase().startsWith(monster.toLowerCase())){
                return monsterList.get(i);
            }
                
        }
        return null;
    }
    
    public Item searchItemInRoom(String item, ArrayList<Item> itemList){
        int i;
        for(i=0; i<itemList.size(); i++){
            if(item.equals(itemList.get(i).getName())){
                return itemList.get(i);
            }else if(itemList.get(i).getName().toLowerCase().startsWith(item.toLowerCase())){
                return itemList.get(i);
            }
                
        }
        return null;
    }

    @Override
    public void examine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

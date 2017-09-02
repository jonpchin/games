//Joanthan Chin
package Monsters;


/**
 *
 * @author jonc
 */
public class CorruptAndroid extends Monster{
    
    private String name = "Android";
    //The description when a player examines corruptAndriod
    private String description = "A tall robot like figure looms here.";
    private String status = "Healthy";
    private int health=40;
    private int energy=20;
    private int attack=7;
    private int speed=5;
    private int defense=14;
    private int intelligence=3;
    //this will determine how much exp to reward the player once this monster is killed
    private int level=2;
    //chance it will attack a player that enters the room
    private int aggression=4;
    //when this monster is killed it will drop this amount of gold
    private int gold=50;
    //chance an item will drop when this monster is slayed
    private int droprate=30;
    
    //coordiantes of where the monster spawns at
    private int xCoordinate;
    private int yCoordinate;
    private int zCoordinate;
    
    
    public CorruptAndroid(int x, int y, int z){
        xCoordinate = x;
        yCoordinate = y;
        zCoordinate = z;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @return the aggression
     */
    public int getAggression() {
        return aggression;
    }

    /**
     * @param aggression the aggression to set
     */
    public void setAggression(int aggression) {
        this.aggression = aggression;
    }

    /**
     * @return the gold
     */
    public int getGold() {
        return gold;
    }

    /**
     * @param gold the gold to set
     */
    public void setGold(int gold) {
        this.gold = gold;
    }

    /**
     * @return the droprate
     */
    public int getDroprate() {
        return droprate;
    }

    /**
     * @param droprate the droprate to set
     */
    public void setDroprate(int droprate) {
        this.droprate = droprate;
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
     * @return the name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }
}

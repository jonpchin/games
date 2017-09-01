//Joanthan Chin
package Equipment;

/**
 *
 * @author jonc
 */
public class Weapons extends Item{ 
   
    //what the equipment is called, this will also be used to save and read from file
    //the inventory of a player
    private String name;
    private String description;
    //wearing certain equpiment boost players stats
    private int health;
    private int energy;
    private int attack;
    private int speed;
    private int defense;
    private int intelligence;
    //special effects are like when an Ice blade can "chill" the enemy or 
    //a poison dart has the special effect "poison"
    private String specialEffect;
    //weapon type is like blades, guns, etc. This is usd because some classes can only use certain weapons,
    //for example nanomedic can not use guns but can use a scalpel or blades.
    private String type;

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
     * @return the specialEffect
     */
    public String getSpecialEffect() {
        return specialEffect;
    }

    /**
     * @param specialEffect the specialEffect to set
     */
    public void setSpecialEffect(String specialEffect) {
        this.specialEffect = specialEffect;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    
    @Override
    public void displayProperties(){
        System.out.println(getName());
        System.out.println("Health : " + getHealth());
        System.out.println("Energy : " + getHealth());
        System.out.println("Speed  : " + getSpeed());
        System.out.println("Attack : " + getAttack());
        System.out.println("Defense : " + getHealth());
        System.out.println("Intelligence : " + getIntelligence());
        System.out.println("Duration : " + getSpecialEffect());
        System.out.println("Special effects : " + getType());
        
    }
}

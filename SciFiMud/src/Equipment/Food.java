/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Equipment;

/**
 *
 * @author root
 */
public class Food extends Item{
    
//foods can give temporary stat boosts to players for a short time duration
    private String name;
    private String description;
    private int health;
    private int energy;
    private int speed;
    private int attack;
    private int defense;
    private int intelligence;

   
    //some foods can make players sick or weak
    private String specialEffects;

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
     * @return the specialEffects
     */
    public String getSpecialEffects() {
        return specialEffects;
    }

    /**
     * @param specialEffects the specialEffects to set
     */
    public void setSpecialEffects(String specialEffects) {
        this.specialEffects = specialEffects;
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
    
    @Override
    public void displayProperties(){
        System.out.println(getName());
        System.out.println("Health : " + getHealth());
        System.out.println("Energy : " + getHealth());
        System.out.println("Speed  : " + getSpeed());
        System.out.println("Attack : " + getAttack());
        System.out.println("Defense : " + getHealth());
        System.out.println("Intelligence : " + getIntelligence());
        System.out.println("Special effects : " + getSpecialEffects());
        
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
}

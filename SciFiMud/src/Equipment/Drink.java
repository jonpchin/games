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
public class Drink extends Item{
    
    //drinks can give temporary stat boosts to players for a short time duration
    //or they give boost health or energy.
    private String name;
    private String description;
    private int health;
    private int energy;
    private int speed;
    private int attack;
    private int defense;
    private int intelligence;
    //duration will be in milliseconds or seconds. implementation is still being discussed
    private int duration;
    //time left before the effect of the drink wears out
    private int timeLeft;
    //some drinks can make players drunk or ill
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
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(int duration) {
        this.duration = duration;
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
    
    @Override
    public void displayProperties(){
        System.out.println(getName());
        System.out.println("Health : " + getHealth());
        System.out.println("Energy : " + getHealth());
        System.out.println("Speed  : " + getSpeed());
        System.out.println("Attack : " + getAttack());
        System.out.println("Defense : " + getHealth());
        System.out.println("Intelligence : " + getIntelligence());
        System.out.println("Duration : " + getDuration());
        System.out.println("Special effects : " + getSpecialEffects());
        
    }

    /**
     * @return the timeLeft
     */
    public int getTimeLeft() {
        return timeLeft;
    }

    /**
     * @param timeLeft the timeLeft to set
     */
    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }
    
    //when the player sips the drink the time setTimeLeft() will set the timer for the duration
    //the player will then get the stats applied to him until the timer runs out.
    public void sipDrink(){
        
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

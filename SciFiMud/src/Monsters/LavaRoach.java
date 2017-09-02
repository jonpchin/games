/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Monsters;

/**
 *
 * @author root
 */
public class LavaRoach extends Monster{
    
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
    
    public LavaRoach(){
        
    }
}

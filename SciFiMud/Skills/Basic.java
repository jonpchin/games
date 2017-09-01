/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Skills;

/**
 *
 * @author root
 */
//all basic actions a player can have such as looking, probing, talking, etc
public interface Basic {
    
    //displays room name, with its description and all objects in room and directions
    void look();
    //displays the people in the room as well as the room ID
    void here();
    //if a person is examined it will show equipment on player that is not hidden
    //if an object in the room is examined it will give a description of the object
    void examine();
    //shows players own status afflictions such as normal, sick, blind, etc
    void self();
    //displays players level;
    void level();
    //displays player stats such as name, level, class, experience, etc
    void stats();
    //displays players location such as ThePit, Factory, Sewers, etc
    void location();
    
    //used to send a private message to another player
    void tell();
    //say something in a room where only people in the room can hear it
    void say();
    //wide area message that people can hear from far away
    void yell();
    //checks what equipment you are wearing
    void equipment();
    //checks what is in your own inventory
    void inventory();
    
    //shows how many bitcoins the player is carrying
    void bitcoins();
    
    //allows a player to go east
    void east();
    //allows a player to go west
    void west();
    //allows a player to go south
    void south();
    //allows a player to go north
    void north();
    //allows a player to go up
    void up();
    //allows a player to go down
    void down();
    //removes an item from equipment
    void remove(String target);
    //drops an item from players inventory
    void drop(String target);
    //takes an item from the room and places it in inventory
    void take(String target);
    //takes an item from inventory and wears it on equipment
    void wear(String target);
    
}

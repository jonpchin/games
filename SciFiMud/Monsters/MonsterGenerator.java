/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Monsters;

import java.util.Random;

/**
 *
 * @author root
 */
public class MonsterGenerator extends Monster{
    //total number of monsters
    //every time a new monster is added to the database this number needs to change
    private final int TOTAL=8;
    //seeding random number genator
    Random randomGenerator = new Random(System.currentTimeMillis());
}

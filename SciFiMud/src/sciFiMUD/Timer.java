/*
 * Copyright (C) 2016 jonc
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package scifimud;

import Equipment.Drink;
import java.util.ArrayList;

/**
 *
 * @author jonc
 */
public class Timer extends Thread{
    static int counter = 0;
    static Timer timer;
    
    public static ArrayList<Drink> drinkDurations = new ArrayList<>();

    //create thread to print counter value
    Thread t = new Thread(new Runnable() {

        @Override
        public void run() {
            while (true) {
                try {
//                    System.out.println("Thread reading counter is: " + counter);
                    if (counter == 30) {
//                       System.out.println("A tick has occured");
                        counter = 0;
                        //24 minutes is an entire day in the game
                        ConnectPlayer.globalTicker = (ConnectPlayer.globalTicker+1)%48;
                        deductTick();
//                      timer.interrupt();//end the timer
//                      break;//end this loop
                    }
                    Thread.sleep(1000);
                    counter++;
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    });
    //goes through all list of equipment, items, monster duration and reduce ticks or
    //removes them from the list if tick is zero
    void deductTick(){

        for(Drink drink : drinkDurations){
           drink.setTimeLeft(drink.getTimeLeft() - 1); 
        }
    }
}

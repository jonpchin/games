# Mineclone
![Alt text](/mineclone/mineclone.png?raw=true "Mine sweeper screenshot")

Clone of mineclone. Numbers are color coded. Right click to add a flag and right click again to remove flag.
Once all mines are marked with a flag the user will be notified with a popup that
he has won. The numbers represent how many mines are adjacent to that square.
The goal of the game is to not click on a mine and to mark all the mines. 

Floodfill algorithm is implemented using a queue to avoid stack overflow from recursion. 
There are three levels. Easy which has 9x9 grid, medium which has 16 x 16 grid and hard which is a 16 x 30 grid. 

Game is completed and ready to play.

The link to final version of mineclone 2.0 is https://github.com/hashSort/Mineclone/releases/tag/Mineclone
Just download the jar file and run it. The game was tested with Java version 1.8. 
You need JRE(Java Runtime Environment) in order to run the jar file. 

Enjoy!

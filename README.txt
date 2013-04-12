
   Polarized Ladder Game - Incorporates the Minimax game Search Algorithm.




To run the game from the .jar file provided execute the following statement from the 
working directory where the file resides (the PolarizedLadder main directory): 

java -jar PLGame.jar

Example:

Windows: 	C:\java -jar PLGame.jar
Unix		$ java -jar PLGame.jar



To compile and run in Mac OS X and Linux from a terminal (ignore the $ it is there to show bash shell)

There is are .java files in the folder PLGame
 
$  cd <path-name-to-directory-PLGame>

run command 

javac -g *.java

then command

$ java Main


For Windows

c: cd <path-name-to-directory PLGame>
run
c:\javac -g *.java
c:\java  Main

This will launch the game


If at any time you want to recompile with a clean build run:
$ rm  *.class
This will remove all class files


To play the game.

The prompt will appear:

Please select game type:
(1) Human vs Human
(2) Human vs AI
(3) AI vs Human
(4) Quit!

next you will see this text in the console

1
7	 	 	 	 	 	 	_	 	 	 	 	 	 	
6	 	 	 	 	 	_	_	_	 	 	 	 	 	
5	 	 	 	 	_	_	_	_	_	 	 	 	 	
4	 	 	 	_	_	_	_	_	_	_	 	 	 	
3	 	 	_	_	_	_	_	_	_	_	_	 	 	
2	 	_	_	_	_	_	_	_	_	_	_	_	 	
1	_	_	_	_	_	_	_	_	_	_	_	_	_	
 	A	B	C	D	E	F	G	H	I	J	K	L	M	
Please enter your next move Player One (ex. 2A):

You are ready to play the game!!

Any wrong input returns the player to the original prompt

example:

Please enter your next move Player One (ex. A5):e1  //wrong order
Please enter your next move Player One (ex. 5A):1Z  //out of bounds
Please enter your next move Player One (ex. 2A):1e // right 




A winning game will display the winner and exit the game.


7	 	 	 	 	 	 	_	 	 	 	 	 	 	
6	 	 	 	 	 	_	_	_	 	 	 	 	 	
5	 	 	 	 	_	_	_	_	_	 	 	 	 	
4	 	 	 	_	_	_	o	_	_	_	 	 	 	
3	 	 	*	_	_	_	o	_	_	_	_	 	 	
2	 	*	*	_	_	o	o	_	_	_	_	_	 	
1	_	*	*	_	o	o	_	_	_	_	_	_	_	
 	A	B	C	D	E	F	G	H	I	J	K	L	M	
Player One Wins the Game! 
Game over!


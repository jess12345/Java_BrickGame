SMASHBRICK Prototype README


Follow the following steps to run the game:
1.	In Eclipse create a new project as a selected location. 
2.	Locate the new Eclipse project and enter the folder “src”
3.	Copy the .java files (not the folder) into the “src” folder
4.	In Eclipse find “package explorer” (normally on the left side of 
	the Eclipse interface)
5.	Right click the project name and click “refresh”
6.	Open the “package explorer” to confirm the files are present
7.	Press the green run button to start game.


All standard project requirements have been meet.


Special Design Elements:
1.	Loading screen (additional)
2.	Menu screen (additional)
3.	Setting screen (additional)
4.	Random level generation
5.	Each level increases in difficulty
6.	Setting screen allows user to set level 1 difficulty
8.	Power ups (super ball, long paddle, bomb) + implementation
9.	Bricks move down. Lose life if any brick touch ground
10.	Game overlays to display important game status



Aspects different from prototype:
1.	Start screen, end screen and all other screens work (created cardlayout)
2.	Allow user to change game difficulty in the setting screen
3.	When bricks are hit they randomly decide whether or not to drop 
	a drop down.
4.	When drop downs are caught on the paddle they perform their specified
	action.
5.	Bricks all move down at a constant rate, setting a time limit.
6.	When the bricks touch the ground they cause the player to lose a life
7.	Game overlay inform user of status
		1. lost a life
		2. new level
		3. paused
		4. caught a bomb
8.	The player is given 3 lives. Both lives and score are shown on the
	screen.
9.	Game requires the player to win 3 games to win
10.	Each level in the game increases in difficulty starting from the set
	difficulty (altered in the settings screen).
11.	Each level is randomly generated according to the set difficulty and
	current level.
12.	It is possible to exit from the exit screen if the game happens to
	take too long to load.
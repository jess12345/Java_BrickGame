
import javax.swing.*;
import java.awt.*;

/**SmashBrick.java
 * This class coordinates the overall flow of the game in sequence and passes control to:
 * - GameStartMenu.java
 * - GameController.java
 * - GameEndMenu.java
 * to implement specific functions in those sections
 * @author Jessica Hu (5975122)
 */

public class SmashBrick_Main {
	//--------------------------------------Attributes----------------------------------------------------------
	//private static SmashBrick_JFrame game_window = new SmashBrick_JFrame(); // Creates a window to display the game
	//public static JFrame game_window;
	//public static GameController game_controller;
	
	public static WindowJFrame window;
	public static GameController game_controller;
	//public static Music music;
	
	
	//-----------------------------------------Methods----------------------------------------------------------
	//***Coordinate the Game************************************************************************************
	public static void main(String[] args) 
	{
		System.out.println('\t');
		System.out.println("------------------------------------------- SMASHBRICK -------------------------------------------");
		System.out.println("-------- COMPSYS 302 Java Assignment --- Created by Jessica (Huijie) Hu --- ID:5975122 -----------");
		System.out.println('\t');
		System.out.println("@Main"+'\t'+'\t'+"Starting SMASHBRICK...");
		// Initialise SmashBrick
		window = new WindowJFrame(); //Create window
		window.addWindowListener(new gameWindowListener()); //Enable windowslistener
		
		try{ Thread.sleep(500);}catch(Exception e){System.out.println("@Main"+'\t'+'\t'+"Error. Program not delayed.");}
		/*
		music = new Music(); //Load music
		music.play("menu");// Play menu music
		*/
		window.switchCard(2); //switch to card 2: Menu Screen
		/*
		while(window.currentCard()!=1)
		{
			if(window.currentCard()==3) //If currently in card 3: GamePlay
			{
				music.stop("menu"); // Stop menu music
				music.play("game"); // Play game music
				
				music.stop("game"); //Stop game music
				window.switchCard(4); //switch to card 4: End Game Screen
				music.play("menu");	//Play menu music
			}
		}
		*/
		
	}
}

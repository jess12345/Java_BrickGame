
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

/**SmashBrick_JFrame.java
 * This class organises the different screens of the game in the form of card layout
 * @author Jessica Hu (5975122)
 */

public class WindowJFrame extends JFrame
{
	//--------------------------------------Attributes----------------------------------------------------------
	private CardLayout window= new CardLayout();
	
	// JPanels for cards 1-5
	private LoadingJPanel card_1_loading_screen; 	//card 1
	private MenuJPanel card_2_menu_screen; 			//card 2
	private GameController card_3_play_screen; 		//card 3
	private EndJPanel card_4_end_screen; 			//card 4
	private SettingJPanel card_5_setting_screen; 	//card 5
	private InstructionJPanel card_6_instruction_screen; //card 6

	
	//---------------------------------------Constructor--------------------------------------------------------
	
	//***Create a Card Layout JFrame to Navigate Game***********************************************************
	public WindowJFrame()
	{
		System.out.println("@WindowJFrame"+'\t'+ "Creating CardLayout JFrame...");
		
		// Initialise window
		setLayout(window);
		
		initialiseGameCards();//Format card appearance, and add cards to window
		
		// Format window
		setResizable(false);
		setTitle("SmashBrick - Game by Jessica Hu (5975122)");
		setSize(800,600); //Size the frame to desired window size
		setLocationRelativeTo(null); //Place the frame in the middle of the monitor
		setVisible(true);
	}
	
	//-----------------------------------------Methods----------------------------------------------------------
	public void setGameDifficulty(int d){card_3_play_screen.setGameDifficulty(d);}
	
	
	//***Format Start Screen************************************************************************************
	public void initialiseGameCards()
	{
		card_1_loading_screen = new LoadingJPanel(this);	//Card 1: Loading JPanel
		add("card1",card_1_loading_screen);
		
		card_2_menu_screen = new MenuJPanel(this);		//Card 2: Menu JPanel
		add("card2",card_2_menu_screen);
		
		card_3_play_screen = new GameController(this);	//Card 3: GamePlay JPanel
		add("card3",card_3_play_screen);
		
		card_4_end_screen = new EndJPanel(this);		//Card 4: GameEnd JPanel
		add("card4",card_4_end_screen);
		
		card_5_setting_screen = new SettingJPanel(this);	//Card 5: Setting JPanel
		add("card5",card_5_setting_screen);
		
		card_6_instruction_screen = new InstructionJPanel(this);	//Card 6: Instruction JPanel
		add("card6",card_6_instruction_screen);
	}
	
	
	//***Switching Between Cards********************************************************************************
	public void switchCard(int card_number)
	{
		switch  (card_number)
		{
			case 1: 
				window.show(card_1_loading_screen.getParent(), "card1");
				card_1_loading_screen.requestFocus();
				break;
			case 2: 
				window.show(card_2_menu_screen.getParent(), "card2");
				card_2_menu_screen.requestFocus();
				break;
			case 3: 
				window.show(card_3_play_screen.getParent(), "card3");
				card_3_play_screen.requestFocus();
				card_3_play_screen.playGame();
				break;
			case 4: 
				window.show(card_4_end_screen.getParent(), "card4");
				card_4_end_screen.setScore(card_3_play_screen.getScore());
				card_4_end_screen.gameWon(card_3_play_screen.getGameOutcome());
				card_4_end_screen.requestFocus();
				break;
			case 5: 
				window.show(card_5_setting_screen.getParent(), "card5");
				card_5_setting_screen.requestFocus();
				break;
			case 6:
				window.show(card_6_instruction_screen.getParent(), "card6");
				card_6_instruction_screen.repaint();
				card_6_instruction_screen.requestFocus();
				card_6_instruction_screen.enableKeyboardControl();
				break;
			default: 
				System.out.println("Error switching cards (@WindowJFrame)");
		}
	}
}



import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**EndJPanel.java
 * This class formats the Game End Screen
 * @author Jessica Hu (5975122)
 */

public class EndJPanel extends JPanel
{
	//--------------------------------------Attributes----------------------------------------------------------
	
	//private Label text_smashbrick;
	//Screen components
	//private String result = "YOU WIN!"; //message initialised to you win
	//private Label end_text; 
	private JButton menu = new JButton("Menu");
	private JButton replay =new JButton("Replay");
	private JLabel score = new JLabel("0");
	
	//Screen components level 1
	//private JPanel middle_jpanel;
	WindowJFrame window;
	private ImageIcon background_won;
	private ImageIcon background_lost;
	private JLabel background_won_label;
	private JLabel background_lost_label;
	
	//---------------------------------------Constructor--------------------------------------------------------
	public EndJPanel(WindowJFrame w)
	{
		System.out.println("@EndJPanel"+'\t' + "Creating Card 4: EndJPanel...");
		window = w;
		
		// Format heading
		try{ 
			//text_smashbrick.setFont(new Font ("Bradley Hand ITC",Font.BOLD, 20));	//format the loading text
			replay.setFont(new Font ("Bradley Hand ITC",Font.BOLD, 20));
			menu.setFont(new Font ("Bradley Hand ITC",Font.BOLD, 20));
			score.setFont(new Font ("Bradley Hand ITC",Font.BOLD, 120));
		}catch(Exception e){System.out.println("Error Setting Font (@MenuJPanel)");}// Continues the game even if the font cannot be set
				
		replay.setOpaque(false);
		replay.setContentAreaFilled(false);
		replay.setBorderPainted(false);
		menu.setOpaque(false);
		menu.setContentAreaFilled(false);
		menu.setBorderPainted(false);

		// Format background
		background_won = new ImageIcon(getClass().getResource("Card4_End_Win.jpg"));
		background_lost = new ImageIcon(getClass().getResource("Card4_End_Lose.jpg"));
		background_won_label = new JLabel(background_won);	//initiaised as background won
		background_lost_label = new JLabel(background_lost);	//initiaised as background won

		
		initialiseButtonAction();
		
		add(menu);
		add(replay);
		add(score);
		add(background_won_label);
		add(background_lost_label);
		

	}
	
	
	//-----------------------------------------Methods----------------------------------------------------------
	public void setScore(int s){
		score.setText(Integer.toString(s));	
	}
	
	public void gameWon(boolean won) // Change the message displayed (true=won, false=lose)
	{
		if(won){
			System.out.println("@EndJPanel"+'\t' + "Game Won...");
			setComponentZOrder(background_lost_label, getComponentCount()-1); //move lost screen to last
			//background_label= new JLabel(background_won);	//won
		}else{
			System.out.println("@EndJPanel"+'\t' + "Game Lost...");
			setComponentZOrder(background_won_label, getComponentCount()-1); //move won screen to last
			//background_label= new JLabel(background_lost);	//won
		}
	}
	
	public void initialiseButtonAction()
	{
		menu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				window.switchCard(2); //switch to card 2: menu
			}
		});
		
		replay.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				window.switchCard(6); //switch to card 6: instructions
			}
		});
	}
	
	public void paint(Graphics g)
	{
		
		super.paint(g); // paint itself
		
		// place components on the screen
		score.setLocation(window.getWidth()/2-score.getWidth()/2, window.getHeight()*350/600-score.getHeight()/2);
		replay.setLocation(window.getWidth()*650/800-replay.getWidth()/2, window.getHeight()*500/600-replay.getHeight()/2);
		menu.setLocation(window.getWidth()*150/800-menu.getWidth()/2, window.getHeight()*500/600-menu.getHeight()/2);
		background_won_label.setLocation(window.getWidth()/2-background_won_label.getWidth()/2, window.getHeight()/2-background_won_label.getHeight()/2);
		background_lost_label.setLocation(window.getWidth()/2-background_lost_label.getWidth()/2, window.getHeight()/2-background_lost_label.getHeight()/2);
	}
	
	
}

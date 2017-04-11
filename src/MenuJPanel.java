

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**Menu_JPanel.java
 * This class formats the Menu Screen
 * @author Jessica Hu (5975122)
 */

public class MenuJPanel extends JPanel {
	//--------------------------------------Attributes----------------------------------------------------------
	//private Label text_smashbrick = new Label ("SMASHBRICK", Label.CENTER); //create text
	private JButton start_game = new JButton ("Start");// Start game button
	private JButton setting= new JButton ("Setting");// Setting button
	private JButton exit_game= new JButton ("Exit");// Exit game button
	private WindowJFrame window;

	private ImageIcon background;
	private JLabel background_label;
	
	
	//---------------------------------------Constructor--------------------------------------------------------
	public MenuJPanel(WindowJFrame w)
	{
		System.out.println("@MenuJPanel"+'\t' + "Creating Card 2: MenuJPanel...");
		window = w;
		
		// Format buttons
		initialiseButtonAction();
		
		start_game.setOpaque(false);
		start_game.setContentAreaFilled(false);
		start_game.setBorderPainted(false);
		
		setting.setOpaque(false);
		setting.setContentAreaFilled(false);
		setting.setBorderPainted(false);
		
		exit_game.setOpaque(false);
		exit_game.setContentAreaFilled(false);
		exit_game.setBorderPainted(false);
		
		
		
		// Format text
		try{ 
			//text_smashbrick.setFont(new Font ("Bradley Hand ITC",Font.BOLD, 72));	//format the loading text
			start_game.setFont(new Font ("Berlin Sans FB",Font.PLAIN, 50));
			start_game.setForeground(Color.white);
			setting.setFont(new Font ("Bradley Hand ITC",Font.BOLD, 24));
			setting.setForeground(Color.white);
			exit_game.setFont(new Font ("Bradley Hand ITC",Font.BOLD, 24));
		}catch(Exception e){System.out.println("Error Setting Font (@MenuJPanel)");}// Continues the game even if the font cannot be set
		
		
		
		
		// Format background
		background = new ImageIcon(getClass().getResource("Card2_Menu.jpg"));
		background_label = new JLabel(background);
		
		
		// Place component on JPanel
		//add(text_smashbrick); //place the title at the top
		add(start_game);
		add(setting);
		add(exit_game);
		add(background_label);
		
	}
	
	
	//-----------------------------------------Methods----------------------------------------------------------
	
	public void initialiseButtonAction()
	{
		start_game.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				window.switchCard(6); //switch to card 6: instruction screen
			}
		});
		
		setting.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				window.switchCard(5); //switch to card 5: setting
			}
		});
		
		exit_game.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0); //exit game
			}
		});
	}
	
	//***Graphics: Game Border**********************************************************************************
	public void paint(Graphics g)
	{
		
		super.paint(g); // paint itself
		 
		// place components on the screen
		
		//text_smashbrick.setLocation(window.getWidth()/2-text_smashbrick.getWidth()/2, window.getHeight()/2-text_smashbrick.getHeight()/2-100);
		start_game.setLocation(window.getWidth()*200/800-start_game.getWidth()/2, window.getHeight()*450/600-start_game.getHeight()/2);
		setting.setLocation(window.getWidth()*650/800-setting.getWidth()/2, window.getHeight()*500/600-setting.getHeight()/2);
		exit_game.setLocation(0, window.getHeight()-80);
		background_label.setLocation(window.getWidth()/2-background_label.getWidth()/2, window.getHeight()/2-background_label.getHeight()/2);
		

	}
	
}

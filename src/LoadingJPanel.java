
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**Loading_JPanel.java
 * This class formats the Loading Screen
 * @author Jessica Hu (5975122)
 */

public class LoadingJPanel extends JPanel
{
	//--------------------------------------Attributes----------------------------------------------------------
	private WindowJFrame window;
	private JLabel text_loading =new JLabel ("Loading");//create text
	private JLabel dot1 = new JLabel (".");
	private JLabel dot2 = new JLabel (".");
	private JLabel dot3 = new JLabel (".");
	private ImageIcon background;
	private JLabel background_label;
	private JButton exit_game= new JButton ("Exit");// Exit game button
	private int loading_rectangles;
	
	//---------------------------------------Constructor--------------------------------------------------------
	public LoadingJPanel(WindowJFrame w)
	{
		System.out.println("@LoadingJPanel"+'\t' + "Creating Card 1: Loading Screen...");
		window=w;
		loading_rectangles =0;
		// Create and format text and button
		text_loading.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		exit_game.setOpaque(false);
		exit_game.setContentAreaFilled(false);
		exit_game.setBorderPainted(false);
		
		try{ 
			text_loading.setFont(new Font ("Bradley Hand ITC",Font.BOLD, 72));	//format the loading text
			dot1.setFont(new Font ("Bradley Hand ITC",Font.BOLD, 72));	//format the loading text
			dot2.setFont(new Font ("Bradley Hand ITC",Font.BOLD, 72));	//format the loading text
			dot3.setFont(new Font ("Bradley Hand ITC",Font.BOLD, 72));	//format the loading text
			exit_game.setFont(new Font ("Bradley Hand ITC",Font.BOLD, 24));
		}catch(Exception e){System.out.println("Error Setting Font (@LoadingJPanel)");}// Continues the game even if the font cannot be set
		
		
		exit_game.addActionListener(new ActionListener(){//action when exit is pressed
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0); //exit game
			}
		});
		
		
		
		
		// Format background
		background = new ImageIcon(getClass().getResource("Card1_Loading.jpg"));
		background_label = new JLabel(background);
				
		
		
		add(text_loading); //place the "loading..." text at the center
		add(dot1);
		add(dot2);
		add(dot3);
		add(exit_game);
		add(background_label);
		setBackground(Color.white);
	}
	
	//-----------------------------------------Methods----------------------------------------------------------
	public void paint(Graphics g)
	{
		
		super.paint(g); // paint itself
		
		// place components on the screen
		exit_game.setLocation(0, window.getHeight()-80);
		background_label.setLocation(window.getWidth()/2-background_label.getWidth()/2, window.getHeight()/2-background_label.getHeight()/2);
		text_loading.setLocation(window.getWidth()*400/800-text_loading.getWidth()/2, window.getHeight()*380/600);
		dot1.setLocation(window.getWidth()*475/800, window.getHeight()*450/600);
		dot2.setLocation(window.getWidth()*500/800, window.getHeight()*450/600);
		dot3.setLocation(window.getWidth()*525/800, window.getHeight()*450/600);
		
		//g.fillRect(window.getWidth()*450/800, window.getHeight()*500/600, 10, 10);
		//g.fillRect(window.getWidth()*475/800, window.getHeight()*500/600, 10, 10);
		//g.fillRect(window.getWidth()*500/800, window.getHeight()*500/600, 10, 10);
		
		
		
		//g.drawRect(window.getWidth()*400/800, window.getHeight()*540/600, 20, 10);
		//g.drawRect(window.getWidth()*450/800, window.getHeight()*540/600, 20, 10);
		//g.drawRect(window.getWidth()*500/800, window.getHeight()*540/600, 20, 10);
		
		/*switch (loading_rectangles){
			case 0:
				loading_rectangles =1;
			case 1: 
				g.fillRect(window.getWidth()*400/800, window.getHeight()*540/600, 20, 10);
				loading_rectangles =2;
			case 2:
				g.fillRect(window.getWidth()*400/800, window.getHeight()*540/600, 20, 10);
				g.fillRect(window.getWidth()*450/800, window.getHeight()*540/600, 20, 10);
				loading_rectangles =3;
			case 3:
				g.fillRect(window.getWidth()*400/800, window.getHeight()*540/600, 20, 10);
				g.fillRect(window.getWidth()*450/800, window.getHeight()*540/600, 20, 10);
				g.fillRect(window.getWidth()*500/800, window.getHeight()*540/600, 20, 10);
				loading_rectangles =0;
			default:
				loading_rectangles =0;
		}*/
		

	}
}

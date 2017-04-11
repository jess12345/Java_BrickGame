
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**SettingJPanel.java
 * This class formats the Setting Screen
 * @author Jessica Hu (5975122)
 */

public class SettingJPanel extends JPanel
{
	//--------------------------------------Attributes----------------------------------------------------------
	//private Label text_smashbrick = new Label ("SMASHBRICK", Label.CENTER); //create text
	private int game_difficulty = 3;
	private JButton save_changes = new JButton ("Save");
	private JButton return_menu = new JButton ("Menu");
	//private JTextField difficulty_entry_box = new JTextField();
	
	private JButton difficulty1 = new JButton();
	private JButton difficulty2 = new JButton();
	private JButton difficulty3 = new JButton();
	private JButton difficulty4 = new JButton();
	private JButton difficulty5 = new JButton();
	
	//private Point difficulty_pointer_location = new Point();
	private Dimension difficulty_pointer_dimension;
	//Point mouse_location; // location where the player clicked hthe difficulty_pointer button
	
	//private JPanel changes_panel = new JPanel();
	private WindowJFrame window;
	private ImageIcon background;
	private JLabel background_label;
	
	
	//---------------------------------------Constructor--------------------------------------------------------
	public SettingJPanel(WindowJFrame w)
	{
		System.out.println("@SettingJPanel"+'\t' + "Creating Card 5: SettingJPanel...");
		window=w;
		
		// Format heading
		try{ 
			//text_smashbrick.setFont(new Font ("Bradley Hand ITC",Font.BOLD, 20));	//format the loading text
			save_changes.setFont(new Font ("Bradley Hand ITC",Font.BOLD, 20));
			return_menu.setFont(new Font ("Bradley Hand ITC",Font.BOLD, 20));
		}catch(Exception e){System.out.println("Error Setting Font (@MenuJPanel)");}// Continues the game even if the font cannot be set
				
		save_changes.setOpaque(false);
		save_changes.setContentAreaFilled(false);
		save_changes.setBorderPainted(false);
		return_menu.setOpaque(false);
		return_menu.setContentAreaFilled(false);
		return_menu.setBorderPainted(false);
		
		difficulty_pointer_dimension = new Dimension(60,60);
		difficulty1.setBackground(Color.black);
		difficulty2.setBackground(Color.black);
		difficulty3.setBackground(Color.black);
		difficulty4.setBackground(Color.black);
		difficulty5.setBackground(Color.black);
		
		difficulty1.setBorderPainted(false);
		difficulty2.setBorderPainted(false);
		difficulty3.setBorderPainted(false);
		difficulty4.setBorderPainted(false);
		difficulty5.setBorderPainted(false);
		
		difficulty1.setOpaque(false);
		difficulty2.setOpaque(false);
		difficulty3.setOpaque(true); //game initialised at level 3
		difficulty4.setOpaque(false);
		difficulty5.setOpaque(false);
		
		// Format components
		//difficulty_entry_box.setSize(20, 5);
		initialiseButtonAction();
		
		// Format background
		background = new ImageIcon(getClass().getResource("Card5_Setting.jpg"));
		background_label = new JLabel(background);
				
		
		
		// Place component on JPanel
		//add(difficulty_entry_box);
		add(save_changes);
		add(return_menu);
		add(difficulty1);
		add(difficulty2);
		add(difficulty3);
		add(difficulty4);
		add(difficulty5);
		
		add(background_label);
		

	}
	
	
	//-----------------------------------------Methods----------------------------------------------------------
		
	public void changeDifficulty(int difficulty)
	{
		if (difficulty<6&&difficulty>0)
		{
			game_difficulty = difficulty;
		}else{
			System.out.println("Error Wrong data entered by the user (@SettingJPanel)");
		}
	}
	
	public void initialiseButtonAction()
	{
		save_changes.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				window.switchCard(2); //switch to card 2: menu
				window.setGameDifficulty(game_difficulty);
			}
		});
		
		return_menu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				window.switchCard(2); //switch to card 2: menu
			}
		});
		
		difficulty1.addMouseListener(new MouseAdapter(){public void mouseClicked(MouseEvent ev){
			turnOffDifficultyButton(game_difficulty);
			turnOnDifficultyButton(1);
			game_difficulty=1;
			repaint();}});
		difficulty2.addMouseListener(new MouseAdapter(){public void mouseClicked(MouseEvent ev){
			turnOffDifficultyButton(game_difficulty);
			turnOnDifficultyButton(2);
			game_difficulty=2;
			repaint();}});
		difficulty3.addMouseListener(new MouseAdapter(){public void mouseClicked(MouseEvent ev){
			turnOffDifficultyButton(game_difficulty);
			turnOnDifficultyButton(3);
			game_difficulty=3;
			repaint();}});
		difficulty4.addMouseListener(new MouseAdapter(){public void mouseClicked(MouseEvent ev){
			turnOffDifficultyButton(game_difficulty);
			turnOnDifficultyButton(4);
			game_difficulty=4;
			repaint();}});
		difficulty5.addMouseListener(new MouseAdapter(){public void mouseClicked(MouseEvent ev){
			turnOffDifficultyButton(game_difficulty);
			turnOnDifficultyButton(5);
			game_difficulty=5;
			repaint();}});
		
	}
	
	public void turnOffDifficultyButton(int i){
		switch (i){
		case 1: difficulty1.setOpaque(false);break;
		case 2: difficulty2.setOpaque(false);break;
		case 3: difficulty3.setOpaque(false);break;
		case 4: difficulty4.setOpaque(false);break;
		case 5: difficulty5.setOpaque(false);break;
		default:
		}
	}
	
	public void turnOnDifficultyButton(int i){
		switch (i){
		case 1: difficulty1.setOpaque(true);break;
		case 2: difficulty2.setOpaque(true);break;
		case 3: difficulty3.setOpaque(true);break;
		case 4: difficulty4.setOpaque(true);break;
		case 5: difficulty5.setOpaque(true);break;
		default:
		}
	}
	
	//***play the game******************************************************************************************
	
	//***mouse listener*****************************************************************************************
	public void moveSetting(){
		this.addMouseListener(new MouseAdapter(){
			public void mouseDragged(MouseEvent e){
				System.out.println("@GameController"+'\t' + "change mouse position...");
				//paddle.setXPosition(MouseInfo.getPointerInfo().getLocation().x); //get the x position of the mouse
	        }
	
	    });
	}
	
	public void setDifficultySquares()
	{
		difficulty1.setSize(difficulty_pointer_dimension);
		difficulty2.setSize(difficulty_pointer_dimension);
		difficulty3.setSize(difficulty_pointer_dimension);
		difficulty4.setSize(difficulty_pointer_dimension);
		difficulty5.setSize(difficulty_pointer_dimension);
	}
	
	
	public void paint(Graphics g)
	{
		
		super.paint(g); // paint itself
		
		// place components on the screen
		//difficulty_entry_box.setLocation(window.getWidth()/2-difficulty_entry_box.getWidth()/2, window.getHeight()/2-difficulty_entry_box.getHeight()/2);
		save_changes.setLocation(window.getWidth()*650/800-save_changes.getWidth()/2, window.getHeight()*500/600-save_changes.getHeight()/2);
		return_menu.setLocation(window.getWidth()*150/800-return_menu.getWidth()/2, window.getHeight()*500/600-return_menu.getHeight()/2);
		
		setDifficultySquares();
		difficulty1.setLocation(window.getWidth()*160/800-difficulty1.getHeight()/2, window.getHeight()*345/600-difficulty1.getHeight()/2);
		difficulty2.setLocation(window.getWidth()*280/800-difficulty2.getHeight()/2, window.getHeight()*345/600-difficulty2.getHeight()/2);
		difficulty3.setLocation(window.getWidth()/2-difficulty3.getHeight()/2, window.getHeight()*345/600-difficulty3.getHeight()/2);
		difficulty4.setLocation(window.getWidth()*520/800-difficulty4.getHeight()/2, window.getHeight()*345/600-difficulty4.getHeight()/2);
		difficulty5.setLocation(window.getWidth()*640/800-difficulty5.getHeight()/2, window.getHeight()*345/600-difficulty5.getHeight()/2);
		
		/*
		switch (game_difficulty){
			case 1:difficulty1.setOpaque(true);break;
			case 2:difficulty2.setOpaque(true);break;
			case 3:difficulty3.setOpaque(true);break;
			case 4:difficulty4.setOpaque(true);break;
			case 5:difficulty5.setOpaque(true);break;
			default:game_difficulty=3;difficulty3.setOpaque(true);break; 
		}*/
		
		
		
		background_label.setLocation(window.getWidth()/2-background_label.getWidth()/2, window.getHeight()/2-background_label.getHeight()/2);
	}
	
}

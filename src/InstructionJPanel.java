

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**EndJPanel.java
 * This class formats the Game End Screen
 * @author Jessica Hu (5975122)
 */
public class InstructionJPanel extends JPanel
{
	//--------------------------------------Attributes----------------------------------------------------------
	private ImageIcon instruction; //background
	private JLabel instruction_label; //background container
	private WindowJFrame window;
	boolean enable_keyboard=true;
	
	//---------------------------------------Constructor--------------------------------------------------------
	public InstructionJPanel(WindowJFrame w)
	{
		System.out.println("@InstJPanel"+'\t' + "Creating Card 6: Instruction...");
		window=w;
		instruction = new ImageIcon(getClass().getResource("Card6_Instruction.jpg"));
		instruction_label = new JLabel(instruction);
		add(instruction_label);
	}
	
	public void enableKeyboardControl()
	{
		enable_keyboard=true;
		this.requestFocus();
		this.addKeyListener(new KeyAdapter()//can also use keyListener
		{
			public void keyPressed(KeyEvent e)
			{
				if(enable_keyboard)	{
					window.switchCard(3);//start game	
					enable_keyboard=false;
				}
			}
		});
		this.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent ev){
				
				if(enable_keyboard)	window.switchCard(3);//start game	
				enable_keyboard=false;
            }

        });
		
	}
	
		
	//-----------------------------------------Methods----------------------------------------------------------
	public void paint(Graphics g)
	{
		
		super.paint(g); // paint itself
		instruction_label.setLocation(window.getWidth()-instruction_label.getWidth(), window.getHeight()/2-instruction_label.getHeight()/2); // move the button

	}
}




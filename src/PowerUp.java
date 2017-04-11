

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JPanel;


/**DropDown.java
 * This class computes and runs the functions of different dropdowns
 * @author Jessica Hu (5975122)
 */

public class PowerUp extends JPanel
{
	//--------------------------------------Attributes----------------------------------------------------------
	private int power_type=0; 	//type of power up:	1=long paddle
								//					2=super ball
								//					3=bomb
	private GameController game_controller;
	private Rectangle power_up = new Rectangle(0,0,30,30);
	private Color colour;
	private boolean visible;

	private int speed;

	
	
	//---------------------------------------Constructor-------------------------------------------------------
	public PowerUp(GameController gc)
	{
			game_controller =gc;
			initialisePowerUp();
	}
	
	//---------------------------------------Methods------------------------------------------------------------
	
	public void initialisePowerUp(){
		speed = 15; //move the power up 10 pixels per loop
		visible=false; //power ups are initially not shown, they only show up when they are called.
	}
	
	
	//***Drop the Power Up**************************************************************************************
	public void dropPowerUp(Rectangle b) // all exterior classes call this function to drop a power up.
	{
		if(!visible){
			if(initialiseRandomType()) ////determine what type of power up this is and returns true if a drop down is required
			{
				game_controller.setDropDownBoolean(true); //tell the game controller that there is a drop down
				initialisePowerUp(b); //format position and 
				visible=true;// make the drop down visible
			}
		} 
		
	}
	
	public boolean initialiseRandomType()
	{
		// Decide whether drop down will occur
		int random_num = (int)(Math.random() * 2); // one in five change of getting a drop down {0,1,2,3,4}														***
		
		if(random_num==0) //if a powerup will be dropped
		{
			power_type = 1+(int)(Math.random() * 3); // choose one of the 4 powerups {1,2,3}
			return true;
		}else{return false;}
		
	}
	
	public void initialisePowerUp(Rectangle brick)
	{
		// initialse the size of the power_up
		power_up.width = 30;
		power_up.height = 30;
		
		power_up.x = brick.x+brick.width/2-power_up.width/2; //set the starting position of the power up
		power_up.y = brick.y+brick.height;
		
		switch(power_type){ // set the color of the power up
			case 1: colour=Color.black;break; //long paddle
			case 2: colour=Color.green;break; //super ball
			case 3: colour=Color.red;break; //bomb
			default: 
		}
		visible = true;
	}
	
	//***Movement logic*****************************************************************************************
	public void movementLogic() //this method is called by external classes to move the drop down
	{
		power_up.y+=speed; //move the power up
		
		if (power_up.y>600){
			visible=false;
			game_controller.setDropDownBoolean(false);
		}
		
		if (game_controller.getPaddle() !=null){// if paddle exists
			if(game_controller.getPaddle().collidesWith(power_up)){ // if paddle collides with power up
				visible=false;
				game_controller.setDropDownBoolean(false);	
				switch(power_type){
					case 1: implementLongPaddle();break; 
					case 2: implementSuper();break;
					case 3: implementBomb();break;
				default: 
				}
			}
		}
	}
	
	
	public void implementLongPaddle()
	{
		System.out.println("@PowerUp"+'\t'+"Implementing long paddle logic...");
		game_controller.implementLongPaddle();
	}
	
	public void implementSuper()
	{
		System.out.println("@PowerUp"+'\t'+"Implementing sticky ball logic...");
		game_controller.implementSuper();
	}
	
	public void implementBomb()
	{
		System.out.println("@PowerUp"+'\t'+"Implementing bomb logic...");
		game_controller.implementBomb();
		
	}
	
	
	

	//-----------------------------------------Graphics---------------------------------------------------------

	public void render (Graphics g)
	{
		super.paint(g);
		
		if (visible)
		{
			g.setColor(colour);
			g.fillRect(power_up.x, power_up.y, power_up.width, power_up.height);
			g.setColor(Color.white);
			g.drawRect(power_up.x, power_up.y, power_up.width, power_up.height);
		}
	}


	
}

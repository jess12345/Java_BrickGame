
import java.awt.*;

/**Paddle.java
 * This class computes and runs the functions of the paddle
 * @author Jessica Hu (5975122)
 */

public class Paddle 
{
	//--------------------------------------Attributes----------------------------------------------------------
	private GameController game_controller;
	
	//Paddle attributes
	private Rectangle rectangle = new Rectangle(0,0,80,20); //size and position of paddle
	private Color colour; //colour of paddle
	private int speed;
	
	//---------------------------------------Constructor-------------------------------------------------------
	public Paddle(GameController gc) {//Construct the player
		System.out.println("@Paddle" + '\t'+'\t' + "Creating Paddle...");		
		game_controller = gc;
		speed=30;
		initialisePaddleAttributes();
	}
	
	//---------------------------------------Methods------------------------------------------------------------
	public void setPosition(int px, int py){rectangle.x =px; rectangle.y=py;}
	public void setXPosition(int x){rectangle.x =x;} //overloading
	
	public int getXPosion(){return rectangle.x;}
	
	public int getWidth(){return rectangle.width;}//Get the width of the paddle
	public int getHeight(){return rectangle.height;}
	
	public void makeLong(){rectangle.width=160;}
	public void makeNormal(){rectangle.width=80;}
	
	
	//***initialise ball attributes******************************************************************************
	public void initialisePaddleAttributes()
	{
		rectangle.width=80;
		rectangle.height=20;
		rectangle.x=game_controller.getGameFieldWidth()/2-rectangle.width/2; //center paddle on screen
		rectangle.y=game_controller.getGameFieldHeight()-30; //paddle 20pixels above bottom
		colour = Color.black;
		makeNormal();
	}
	
	//***Move the Paddle****************************************************************************************
	public void moveXDirection(boolean b) {//Move in the x direction
		if(b){rectangle.x +=speed;}else{rectangle.x -=speed;}
		
		if (rectangle.x<0) rectangle.x=0; // define location as zero if the paddle is at location zero
		if (rectangle.x> game_controller.getGameFieldWidth()-rectangle.width) rectangle.x = game_controller.getGameFieldWidth()-rectangle.width;// define location as a paddle-width from the end if the paddle is at the end.
	}
	
	//***Paddle Ball Collision Detector*************************************************************************
	public boolean collidesWith(Rectangle object){return rectangle.intersects(object);}
	
	
	//-----------------------------------------Graphics---------------------------------------------------------
	public void render(Graphics g){//Set colour of the paddle
		if(game_controller.getLives()>0){
			g.setColor(colour);
			g.fillRoundRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height, 10, 10);// Blue paddle
		}else{
			g.setColor(Color.black);g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		} 
	}
}

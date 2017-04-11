
import java.awt.*;

/**Brick.java
 * This class makes a brick
 * @author Jessica Hu (5975122)
 */


public class Brick 
{
	//--------------------------------------Attributes----------------------------------------------------------

	//Brick attributes
	private Rectangle rectangle; //size and position of paddle
	//private Color colour; //colour of paddle
	private boolean brick_exist =true; // whether brick exists
	private Color colour;
	
	//---------------------------------------Constructor-------------------------------------------------------
	public Brick(int x, int y, int width, int height)
	{
		rectangle = new Rectangle (x,y,width,height);
		colour=new Color(255,0,0,200);
	}
	
	
	//---------------------------------------Methods------------------------------------------------------------
	
	//***Set-a Get-a Methods************************************************************************************
	public int getXPosition(){return rectangle.x;}
	public int getYPosition(){return rectangle.y;}
	public int getHeight(){return rectangle.height;}
	public void setYMovement(int p){rectangle.y+=p;} //move the rectangle vertically
	public void resetPosition(int x, int y){rectangle.x=x;rectangle.y=y;}
	public void setBrickExistance(boolean be){brick_exist = be;}
	public boolean getBrickExistance(){return brick_exist;}
	public Rectangle getRectangle(){return rectangle;} // return the position and size of the brick
	
	
	//***Brick Ball Collision Detector***************************************************************************
	
	public boolean collideThisBrick(Rectangle object) {
		if(brick_exist){return rectangle.intersects(object);}else {return false;}
	}
	
	//-----------------------------------------Graphics---------------------------------------------------------
	public void render (Graphics g)
	{
		if(brick_exist){
			g.setColor(colour); 
			g.fillRoundRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height, 10, 10);
			
			//g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
			g.setColor(Color.white);
			g.drawRoundRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height, 10, 10);
		}
	}
	
}


import java.awt.*;

/**Ball.java
 * This class computes and runs the functions of the ball
 * @author Jessica Hu (5975122)
 */

public class Ball{
	//--------------------------------------Attributes----------------------------------------------------------
	private GameController game_controller;
	private Dimension movement = new Dimension(-5,-10); // movement of the ball (initial)
	
	//ball attributes
	private int radius; //radius of ball
	private Point current_position = new Point(0,0); //position of ball
	private Color colour; //colour of ball
	
	private boolean super_ball; //show when the ball is sticky
	
	//---------------------------------------Constructor-------------------------------------------------------
	public Ball(GameController gc)
	{
		System.out.println("@Ball" + '\t' + '\t' + "Creating Ball...");
		game_controller=gc;
		initialiseBallAttributes();
	}
	
	
	//---------------------------------------Methods------------------------------------------------------------
	//***Set-a Get-a Methods************************************************************************************
	public void setPosition(int xp, int yp){current_position.x=xp;current_position.y=yp;}
	public void setMovement(int xm,int ym){movement.width=xm;movement.height=ym;}
	public int getBallRadius(){return radius;}
	
	public void makeSuper(){super_ball=true;}
	public void makeNormal(){super_ball=false;}
	
	//***initialise ball attributes******************************************************************************************
	public void initialiseBallAttributes()
	{
		radius=10;
		current_position.x=game_controller.getGameFieldWidth()/2-radius; //center ball on screen
		current_position.y=game_controller.getGameFieldHeight()-100; //ball 20pixels above bottom
		colour = new Color(0,0,0);
	}
	
	
	
	//***Move the Ball******************************************************************************************
	public void movementLogic()
	{
		
			
			/*LeftWall*/if (current_position.x-radius <=0 && movement.width<0)movement.width=-movement.width;
			/*RightWall*/int ball_end_x =game_controller.getGameFieldWidth()-radius; //last current_position of ball
			/*RightWall*/if ((current_position.x+radius >= ball_end_x) && (movement.width>0))movement.width=-movement.width;
			/*Ceiling*/if (current_position.y-radius <=30 && movement.height<0)movement.height=-movement.height;
			/*floor*/if (current_position.y+radius >= (game_controller.getGameFieldHeight()-radius) && movement.height>0){game_controller.groundBall();}
			
			if(game_controller.getPaddle() !=null){// if paddle exists
				if(game_controller.getPaddle().collidesWith(new Rectangle(current_position.x-radius+movement.width, current_position.y+radius-18+movement.height, radius*2, radius*2))){
					movement.height=-movement.height;
				}
			}
			
			
			if(game_controller.getBrickGroup() != null){// if a brick exists
				if(game_controller.getBrickGroup().collidesWith(new Rectangle(current_position.x-radius+movement.width, current_position.y+radius-18+movement.height, radius*2, radius*2))){
					if(!super_ball)movement.height=-movement.height;
				}
			}	
			
			current_position.move(current_position.x+movement.width, current_position.y+movement.height);
		
	}
	
	public void manualMove() //allow other classes to move the ball
	{
		current_position.move(current_position.x+movement.width, current_position.y+movement.height);
	}
	
	//-----------------------------------------Graphics---------------------------------------------------------
	public void render(Graphics g){
		if(game_controller.getLives()>0){
			g.setColor(colour);
			g.fillOval(current_position.x-radius, current_position.y-radius, radius*2, radius*2);
		}else{
			g.setColor(Color.black);
			g.drawOval(current_position.x-radius, current_position.y-radius, radius*2, radius*2);
		} 
	}
}


import java.awt.*;
import java.util.Arrays;

/**BrickGroup.java
 * This class computes and displays the entire block of bricks with their random arrangements.
 * @author Jessica Hu (5975122)
 */

public class BrickGroup {
	//--------------------------------------Attributes----------------------------------------------------------
	private GameController game_controller;
	private boolean brick_collides;
	
	//Brick Group attributes
	private Brick[][] brick_layout = new Brick[8][7];
	private int max_potential_bricks=8*7;
	private int brick_count;
	private int individual_brick_width;
	private int bricks_to_generate=18;//number of bricks that will be randomly generated
	
	//the speed at which the bricks will move down the screen (50 mean it will move down every 50 cycles)
	private int move_down_speed=25; //between 0 and 50 (50 is the slowest, 0 is the fastest)
	private int movement_counter=0;
	
	//---------------------------------------Constructor-------------------------------------------------------
	public BrickGroup(GameController gc)
	{
		System.out.println("@BrickGroup"+'\t'+"Creating Bricks...");
		game_controller=gc;
		move_down_speed=25; //between 0 and 50 (50 is the slowest, 0 is the fastest)
		initialiseBrickGroupAttributes(); //initialise attributes
	}
	
	//---------------------------------------Methods------------------------------------------------------------
	
	//***Set-a Get-a Methods************************************************************************************
	public int getBrickGroupX(){return brick_layout.length;}
	public int getBrickCount(){return brick_count;}
	public void setBricksToGenerate(int n){bricks_to_generate=n;}
	
	public void setGameDifficulty(int difficulty){
		switch(difficulty){
			case 1: bricks_to_generate=6; move_down_speed=1;break;
			case 2: bricks_to_generate=12; move_down_speed=22;break;
			case 3: bricks_to_generate=18; move_down_speed=18;break;
			case 4: bricks_to_generate=24; move_down_speed=14;break;
			case 5: bricks_to_generate=30; move_down_speed=10;break;
			case 6: bricks_to_generate=36; move_down_speed=9;break;
			case 7: bricks_to_generate=42; move_down_speed=8;break;
			case 8: bricks_to_generate=48; move_down_speed=7;break;
			case 9: bricks_to_generate=54; move_down_speed=6;break;
			case 10: bricks_to_generate=max_potential_bricks;break;
			default: System.out.println("@BrickGroup"+'\t'+"Error. Invalid game difficulty...");
		}
	}

	
	//***First Run Initialisation*****************************************************************************
	public void initialiseBrickGroupAttributes()
	{	
		// Establish constants
		brick_count=0;
		max_potential_bricks=brick_layout.length*brick_layout[0].length; //#bricks= horizontal*vertical
		individual_brick_width=(int)(game_controller.getGameFieldWidth()/brick_layout.length); //width of 1 brick	
		
		//Create bricks at each position
		for (int x=0; x<brick_layout.length; x++){
			for (int y=0; y<brick_layout[0].length; y++){
				brick_layout[x][y]=new Brick(x*game_controller.getGameFieldWidth()/brick_layout.length+3, y*30+30, individual_brick_width, 30); //to get the position of the bricks
			}
		}	
		
	}
	
	//***Initialising Brick Arrangement************************************************************************
	public void generateBrickArrangement()
	{
		System.out.println("@BrickGroup"+'\t'+"Generating Random Bricks...");
		int i=0; //count number of random numbers generate
		int[] random_bricks = new int[bricks_to_generate/2]; //store brick ID of chosen bricks
		int random_num;
		brick_count=0;
		resetBrick();
		
		while(i<bricks_to_generate/2)
		{
			random_num= 1 + (int)(Math.random() * max_potential_bricks/2);

			for(int j=0;j<random_bricks.length;j++)
			{
				if(random_bricks[j]==random_num)
				{
					break; //break out of for loop
				}else{
					if (j==random_bricks.length-1){random_bricks[i] = random_num;i++;}
				}
			}
		}
		
		Arrays.sort(random_bricks); //sorts the random numbers in ascending order
		
		int k=0; //point to current random number of interest in random_bricks
		int l=0; //count the number of loops that have gone by
		
		for (int y=0; y<brick_layout[0].length; y++){	
			for (int x=0; x<brick_layout.length/2; x++){
				if(k<random_bricks.length){
				
					if(random_bricks[k]==l)
					{
						brick_layout[brick_layout.length-1-x][y].setBrickExistance(true);
						brick_layout[x][y].setBrickExistance(true);//brick exists
						k++;
						brick_count+=2;
						
					}else{
						brick_layout[brick_layout.length-1-x][y].setBrickExistance(false);
						brick_layout[x][y].setBrickExistance(false);//brick does not exist
					}
					l++;
				}else{
					brick_layout[brick_layout.length-1-x][y].setBrickExistance(false);
					brick_layout[x][y].setBrickExistance(false);//brick does not exist
				}
				
			}
		}
	}
	
	//***Move Bricks Down************************************************************************
	public void movementLogic()
	{
		if(movement_counter!=move_down_speed-1){//this if statement slows down the counter
			movement_counter++;
			System.out.println("@BrickGroup"+'\t'+"Waiting.................................");
		}else{
			System.out.println("@BrickGroup"+'\t'+"Moving...................................");
			movement_counter=0;
			for (int x=0; x<brick_layout.length; x++){//loops through and move all the bricks down
				for (int y=0; y<brick_layout[0].length; y++){
					brick_layout[x][y].setYMovement(1);
				}
			}
		}
		
		//check of whether the bottom of the bricks group is below paddle position
		if(brick_layout[0][6].getYPosition()+brick_layout[0][6].getHeight()>550){
			if(brick_layout[0][5].getYPosition()+brick_layout[0][6].getHeight()>550){
				if(brick_layout[0][4].getYPosition()+brick_layout[0][6].getHeight()>550){
					if(brick_layout[0][3].getYPosition()+brick_layout[0][6].getHeight()>550){
						if(brick_layout[0][2].getYPosition()+brick_layout[0][6].getHeight()>550){
							if(brick_layout[0][1].getYPosition()+brick_layout[0][6].getHeight()>550){
								if(brick_layout[0][0].getYPosition()+brick_layout[0][6].getHeight()>550){
									if(loopRowForCollision(0))game_controller.brickTouchGround();
									return;
								}else{
									if(loopRowForCollision(1))game_controller.brickTouchGround();
									return;
								}
							}else{
								if(loopRowForCollision(2))game_controller.brickTouchGround();
								return;
							}
						}else{
							if(loopRowForCollision(3))game_controller.brickTouchGround();
							return;
						}
					}else{
						if(loopRowForCollision(4))game_controller.brickTouchGround();
						return;
					}
				}else{
					if(loopRowForCollision(5))game_controller.brickTouchGround();
					return;
				}
			}else{
				if(loopRowForCollision(6))game_controller.brickTouchGround();
				return;
			}
		}else{
			return;
		}
	}
	
	public boolean loopRowForCollision(int y){
		for(int x=0;x<brick_layout.length;x++){
			if(game_controller.getBrickGroup().collidesWith(brick_layout[x][y].getRectangle())){
				game_controller.brickTouchGround();
				return true;
			}else if (brick_layout[x][y].getYPosition()+brick_layout[x][y].getHeight()>570){
				game_controller.brickTouchGround();
				return true;
			}
		}
		return false;
		
	}
	
	//***Reset Brick Position************************************************************************
	public void resetBrick()
	{
		//reset brick positions
		for (int x=0; x<brick_layout.length; x++){//loops through and move all the bricks down
			for (int y=0; y<brick_layout[0].length; y++){
				brick_layout[x][y].resetPosition(x*game_controller.getGameFieldWidth()/brick_layout.length+3, y*30+30);
			}
		}
		movement_counter=0;
	}
	
		
	//***Brick Ball Collision Detector***************************************************************************
	
	//public boolean collidesWith(Rectangle object){return brick.intersects(object);}
	public boolean collidesWith(Rectangle ob){
		Rectangle object = ob;
		brick_collides = false;
		for (int x=0; x<brick_layout.length; x++){//loops through and displays all the bricks
			for (int y=0; y<brick_layout[0].length; y++){
				brick_collides =brick_layout[x][y].collideThisBrick(new Rectangle(object.x,object.y,object.width,object.height));
				if(brick_collides){
					brick_layout[x][y].setBrickExistance(false);//brick no longer exists
					game_controller.incrementScore(10);//increase score by 10
					game_controller.createPowerUp(brick_layout[x][y].getRectangle()); //determine whether there is a random drop down and what it is.
					brick_count--;
					if(brick_count<=0){game_controller.finishedLevel();}
					return true;
				}
			}
		}
		return false;
	}
	

	
	//-----------------------------------------Graphics---------------------------------------------------------
	public void render (Graphics g){
		for (int x=0; x<brick_layout.length; x++){//loops through and displays all the bricks
			for (int y=0; y<brick_layout[0].length; y++){
				brick_layout[x][y].render(g); //if the brick does not exist it will not print anything
			}
		}	
	}
}
